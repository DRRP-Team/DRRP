/**
 * Grain shader
 * Copyright (c) PROPHESSOR 2019
 * 
 * Special for Doom RPG Remake Project (map XHUB)
 */

void main() {
    vec3 color = texture(InputTexture, TexCoord).rgb;

    color += ((fract(sin(dot(vec3(TexCoord, timer * .5), vec3(2.9898, 8.666, 1.))) * 3666.2279)) * 2. - 1.) * .1;

    FragColor = vec4(color, 1.);
}
