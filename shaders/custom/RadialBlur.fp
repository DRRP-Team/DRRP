/**
 * Copyright (c) 2017-2022 DRRP-Team
 * 
 * https://stackoverflow.com/questions/4579020/how-do-i-use-a-glsl-shader-to-apply-a-radial-blur-to-an-entire-scene
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

const float sampleDist = 1.0;
const float sampleStrength = 2.2;

void main() {
    float samples[10];
    samples[0] = -0.08;
    samples[1] = -0.05;
    samples[2] = -0.03;
    samples[3] = -0.02;
    samples[4] = -0.01;
    samples[5] =  0.01;
    samples[6] =  0.02;
    samples[7] =  0.03;
    samples[8] =  0.05;
    samples[9] =  0.08;


    vec2 dir    = 0.5 - TexCoord; 
    float dist  = sqrt((dir.x * dir.x) + (dir.y * dir.y)); 
    dir         = dir / dist; 

    vec4 color  = texture(InputTexture, TexCoord);
    vec4 sum    = color;

    for (int i = 0; i < 10; i++) {
        sum += texture(InputTexture, TexCoord + dir * samples[i] * sampleDist);
    }

    sum *= 1.0 / 11.0;

    float t = clamp(dist * sampleStrength, 0.0, 1.0);

    FragColor = mix(color, sum, t);
}
