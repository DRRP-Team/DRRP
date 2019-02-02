/**
 * Lut tonemap shader
 * 
 * Edited by PROPHESSOR (2019) special for Doom RPG Remake Project
 *
 * Used before in Doom Slayer Chronicles mod
 *
 * Credits to kingeric1992 http://enbseries.enbdev.com/forum/viewtopic.php?f=7&t=4394
 */

#define MIX_FACTOR  1

const vec2 Lut_Size = vec2(256., 16.);

vec3 LutFunc(vec3 colorIN) {
    vec2 Lut_pSize = vec2(1. / Lut_Size);
    vec4 Lut_UV;

    colorIN    = clamp(colorIN, 0, 1.) * (Lut_Size.y - 1.);

    Lut_UV.w   = floor(colorIN.b);
    Lut_UV.xy  = (colorIN.rg + 0.5) * Lut_pSize;
    Lut_UV.x  += Lut_UV.w * Lut_pSize.y;
    Lut_UV.z   = Lut_UV.x + Lut_pSize.y;

    return mix(textureLod(LutMap, Lut_UV.xy, Lut_UV.z).rgb,
               textureLod(LutMap, Lut_UV.zy, Lut_UV.z).rgb,
               colorIN.b - Lut_UV.w);
}

void main() {
    vec3 color = texture(InputTexture, TexCoord).rgb;
    vec3 Lutcolor = LutFunc(color);

    FragColor = vec4(mix(color.rgb, Lutcolor.rgb, MIX_FACTOR), 1.0);
}
