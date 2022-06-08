/**
 * Heat Haze
 * by Marty McFly
 *
 * Used in Doom Slayer Chronicles mod
 *
 * Edited by PROPHESSOR for Doom RPG Remake Project
 */

// uniform lowp  float Temperature;

void main() {
    const float Speed        = 1.8;
    const float TextureScale = 1.0;
    const vec3 heatColor = vec3(0.666, 0.333, 0.0); // AA5500
    float Offset       = 4.4 * Temperature;

    vec3 heatnormal = texture(HazeBump, vec2(TexCoord.xy * TextureScale + vec2(0, timer * .01 * Speed))).rgb - 0.5;
    vec2 heatoffset = normalize(heatnormal.xy) * pow(length(heatnormal.xy), .5);

    FragColor.g = texture(InputTexture, (TexCoord.xy - vec2(.001)) + heatoffset.xy * .001 * Offset).g;
    FragColor.r = texture(InputTexture, (TexCoord.xy - vec2(.001)) + heatoffset.xy * .001 * Offset * (1. + Temperature)).r;
    FragColor.b = texture(InputTexture, (TexCoord.xy - vec2(.001)) + heatoffset.xy * .001 * Offset * (1. - Temperature)).b;

    FragColor.rgb = mix(FragColor.rgb, heatColor, min(Temperature, 0.75));
}
