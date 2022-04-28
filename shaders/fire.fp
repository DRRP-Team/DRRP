// Based on fire shader from Murk

/* Libs */

// Hash function by Dave_Hoskins
float hash12(vec2 p) {
	uvec2 q = uvec2(ivec2(p)) * uvec2(1597334673U, 3812015801U);
	uint n = (q.x ^ q.y) * 1597334673U;
	return float(n) * (1.0 / float(0xffffffffU));
}

float perlinNoise(vec2 x) {
    vec2 i = floor(x);
    vec2 f = fract(x);

	float a = hash12(i);
    float b = hash12(i + vec2(1.0, 0.0));
    float c = hash12(i + vec2(0.0, 1.0));
    float d = hash12(i + vec2(1.0, 1.0));

    vec2 u = f * f * (3.0 - 2.0 * f);
	return mix(a, b, u.x) + (c - a) * u.y * (1.0 - u.x) + (d - b) * u.x * u.y;
}

float perlinFbm (vec2 uv, float freq) {
    uv *= freq;
    float amp = 0.5f;
    float noise = 0.0f;
    for (int i = 0; i < 8; ++i) {
        noise += amp * perlinNoise(uv);
        uv *= 2.0f;
        amp *= 0.5f;
    }
    return noise;
}

vec2 rhash(vec2 uv) {
    const mat2 myt = mat2(.12121212, .13131313, -.13131313, .12121212);
    const vec2 mys = vec2(1e4, 1e6);
    uv *= myt;
    uv *= mys;
    return fract(fract(uv / mys) * uv);
}

float voronoi2d(const in vec2 point) {
  vec2 p = floor(point);
  vec2 f = fract(point);
  float res = 0.0;
  for (int j = -1; j <= 1; j++) {
    for (int i = -1; i <= 1; i++) {
      vec2 b = vec2(i, j);
      vec2 r = vec2(b) - f + rhash(p + b);
      res += 1. / pow(dot(r, r), 8.);
    }
  }
  return pow(1. / res, 0.0625);
}

/* End of libs */

const float fireSpeed = 3.0;

const vec2 perlinNoiseDirection = vec2(0.0f, 0.5f);
const float perlinNoiseScale = 7.5f;

const vec2 voronoNoiseDirection = vec2(0.1f, 0.5f);
const float voronoNoiseScale = 2.0f;
const float voronoNoisePower = 0.4; // 1.51f;

vec2 getFireNoise(in vec2 texcoord) {
    vec2 pNoiseDirection = perlinNoiseDirection * timer * fireSpeed;
    vec2 vNoiseDirection = voronoNoiseDirection * timer * fireSpeed;
    float perlinNoise = perlinFbm(texcoord + pNoiseDirection, perlinNoiseScale);
    float voronoiNoise = voronoi2d(texcoord * voronoNoiseScale + vNoiseDirection);
    voronoiNoise = pow(voronoiNoise, voronoNoisePower);

    // from [0,1] to [-1,1]
    perlinNoise = (perlinNoise - 0.5f) * 2.0f;

    return vec2(perlinNoise * voronoiNoise, perlinNoise);
}

// 0 - only texture, 1 - only noise
const float fireNoiseCoeff = 0.3f;
// 0.1 - red, 0.5 - orange, 1 - white (look at textures/shadermaps/fire.png)
const float fireTemperature = 1.0f;
const vec3 fireColorCorrection = vec3(1.5);

vec4 getFire(in vec2 texcoord) {
    vec2 fireCoords = vec2(texcoord.x + 0.5, texcoord.y);
    vec2 maskCoords = texcoord;

    vec2 fireNoise = getFireNoise(fireCoords);
    float fireShape = fireNoise.x;
    float fireShade = fireNoise.y;

    vec2 finalFireShape = mix(maskCoords, vec2(maskCoords.x, fireShape), fireNoiseCoeff);
    vec4 baseTexture = getTexel(finalFireShape);

    // Shade central part of the fire
    baseTexture.rgb *= mix(0.7, 1., fireShade);

    float fireColorRampIndex = baseTexture.r * baseTexture.a * fireTemperature;

    vec4 colorRamp = texture(tex_colorRamp, vec2(fireColorRampIndex, 0.5f));
    
    vec4 fire = baseTexture * (colorRamp * vec4(fireColorCorrection, 1));

    // Cut top fire artifacts caused by maskCoords Y-shifting
    fire.a -= pow(1.0 - (maskCoords.y + 0.22), 2);

    return fire;
}

void SetupMaterial(inout Material material) {
    vec2 texCoord = vTexCoord.st;

    material.Base = getFire(texCoord);
    material.Normal = ApplyNormalMap(texCoord);

#if defined(SPECULAR)
    material.Specular = texture(speculartexture, texCoord).rgb;
    material.Glossiness = uSpecularMaterial.x;
    material.SpecularLevel = uSpecularMaterial.y;
#endif    
    
#ifndef NO_LAYERS
	if ((uTextureMode & TEXF_Brightmap) != 0)
		material.Bright = texture(brighttexture, texCoord.st);
		
	if ((uTextureMode & TEXF_Detailmap) != 0)
	{
		vec4 Detail = texture(detailtexture, texCoord.st * uDetailParms.xy) * uDetailParms.z;
		material.Base *= Detail;
	}
	
	if ((uTextureMode & TEXF_Glowmap) != 0)
		material.Glow = texture(glowtexture, texCoord.st);
#endif   
}