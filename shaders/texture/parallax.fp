const float	parallaxScale = 0.1;

vec2 parallaxMapping(in vec3 V, in vec2 T, out float parallaxHeight)
{
   float initialHeight = texture(texture2, vTexCoord.xy).r;

   vec2 texCoordOffset = parallaxScale * V.xy / V.z * initialHeight;

   texCoordOffset = parallaxScale * V.xy * initialHeight;

   return vTexCoord.xy - texCoordOffset;
}

vec4 Process(vec4 in_color)
{
   vec3 V = normalize(vEyeNormal).xyz;

   float parallaxHeight;
   vec2 T = parallaxMapping(V, vTexCoord.xy, parallaxHeight);
   
   return texture(tex, T);
   
}