
mat3 cotangent_frame(vec3 n, vec3 p, vec2 uv)
{
	// get edge vectors of the pixel triangle
	vec3 dp1 = dFdx(p);
	vec3 dp2 = dFdy(p);
	vec2 duv1 = dFdx(uv);
	vec2 duv2 = dFdy(uv);

	// solve the linear system
	vec3 dp2perp = cross(n, dp2); // cross(dp2, n);
	vec3 dp1perp = cross(dp1, n); // cross(n, dp1);
	vec3 t = dp2perp * duv1.x + dp1perp * duv2.x;
	vec3 b = dp2perp * duv1.y + dp1perp * duv2.y;

	// construct a scale-invariant frame
	float invmax = inversesqrt(max(dot(t,t), dot(b,b)));
	return mat3(t * invmax, b * invmax, n);
}

vec3 ApplyNormMap()
{
	#define WITH_NORMALMAP_UNSIGNED
	#define WITH_NORMALMAP_GREEN_UP
	//#define WITH_NORMALMAP_2CHANNEL

	vec3 interpolatedNormal = normalize(vWorldNormal.xyz);

	vec3 map = texture(texture3, vTexCoord.st).xyz;
	#if defined(WITH_NORMALMAP_UNSIGNED)
	map = map * 255./127. - 128./127.; // Math so "odd" because 0.5 cannot be precisely described in an unsigned format
	#endif
	#if defined(WITH_NORMALMAP_2CHANNEL)
	map.z = sqrt(1 - dot(map.xy, map.xy));
	#endif
	#if defined(WITH_NORMALMAP_GREEN_UP)
	map.y = -map.y;
	#endif

	mat3 tbn = cotangent_frame(interpolatedNormal, pixelpos.xyz, vTexCoord.st);
	vec3 bumpedNormal = normalize(tbn * map);
	return bumpedNormal;
}

vec2 lightAttenuation(int i, vec3 normal, vec3 viewdir, float lightcolorA)
{
	vec4 lightpos = lights[i];
	vec4 lightspot1 = lights[i+2];
	vec4 lightspot2 = lights[i+3];

	float lightdistance = distance(lightpos.xyz, pixelpos.xyz);
	if (lightpos.w < lightdistance)
		return vec2(0.0); // Early out lights touching surface but not this fragment
	
	float attenuation = clamp((lightpos.w - lightdistance) / lightpos.w, 0.0, 1.0);

	if (lightspot1.w == 1.0)
		attenuation *= spotLightAttenuation(lightpos, lightspot1.xyz, lightspot2.x, lightspot2.y);

	vec3 lightdir = normalize(lightpos.xyz - pixelpos.xyz);

	if (lightcolorA < 0.0) // Sign bit is the attenuated light flag
		attenuation *= clamp(dot(normal, lightdir), 0.0, 1.0);

	if (attenuation > 0.0) // Skip shadow map test if possible
		attenuation *= shadowAttenuation(lightpos, lightcolorA);

	if (attenuation <= 0.0)
		return vec2(0.0);

	float glossiness = uSpecularMaterial.x;
	float specularLevel = uSpecularMaterial.y;

	vec3 halfdir = normalize(viewdir + lightdir);
	float specAngle = clamp(dot(halfdir, normal), 0.0f, 1.0f);
	float phExp = glossiness * 4.0f;
	return vec2(attenuation, attenuation * specularLevel * pow(specAngle, phExp));
}

vec3 ProcessMaterial(vec3 material, vec3 color)
{
	vec4 dynlight = uDynLightColor;
	vec4 specular = vec4(0.0, 0.0, 0.0, 1.0);

	vec3 normal = ApplyNormMap();
	vec3 viewdir = normalize(uCameraPos.xyz - pixelpos.xyz);

	if (uLightIndex >= 0)
	{
		ivec4 lightRange = ivec4(lights[uLightIndex]) + ivec4(uLightIndex + 1);
		if (lightRange.z > lightRange.x)
		{
			// modulated lights
			for(int i=lightRange.x; i<lightRange.y; i+=4)
			{
				vec4 lightcolor = lights[i+1];
				vec2 attenuation = lightAttenuation(i, normal, viewdir, lightcolor.a);
				dynlight.rgb += lightcolor.rgb * attenuation.x;
				specular.rgb += lightcolor.rgb * attenuation.y;
			}

			// subtractive lights
			for(int i=lightRange.y; i<lightRange.z; i+=4)
			{
				vec4 lightcolor = lights[i+1];
				vec2 attenuation = lightAttenuation(i, normal, viewdir, lightcolor.a);
				dynlight.rgb -= lightcolor.rgb * attenuation.x;
				specular.rgb -= lightcolor.rgb * attenuation.y;
			}
		}
	}

	dynlight.rgb = clamp(color + desaturate(dynlight).rgb, 0.0, 1.4);
	specular.rgb = clamp(desaturate(specular).rgb, 0.0, 1.4);

	vec4 materialSpec = texture(texture4, vTexCoord.st);
	vec3 frag = material * dynlight.rgb + materialSpec.rgb * specular.rgb;

	if (uLightIndex >= 0)
	{
		ivec4 lightRange = ivec4(lights[uLightIndex]) + ivec4(uLightIndex + 1);
		if (lightRange.w > lightRange.z)
		{
			vec4 addlight = vec4(0.0,0.0,0.0,0.0);

			// additive lights
			for(int i=lightRange.z; i<lightRange.w; i+=4)
			{
				vec4 lightcolor = lights[i+1];
				vec2 attenuation = lightAttenuation(i, normal, viewdir, lightcolor.a);
				addlight.rgb += lightcolor.rgb * attenuation.x;
			}

			frag = clamp(frag + desaturate(addlight).rgb, 0.0, 1.0);
		}
	}

	return frag;
}
