/**
 * Filmic Grain shader
 * Copyright (c) PROPHESSOR 2019
 * 
 * Special for Doom RPG Remake Project
 */

void main() {
    vec3 color = texture(InputTexture, TexCoord).rgb;

    color += ((fract(sin(dot(vec3(TexCoord, timer * .5), vec3(12.9898, 78.233, 1.))) * 43758.5453)) * 2. - 1.) * .1;

    FragColor = vec4(color, 1.);
}
