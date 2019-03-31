/**
 * Copyright (c) 2017-2019 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

const float M_E = 2.71828;

void main() {
    vec3 color = texture(InputTexture, TexCoord).rgb;

	color = log((M_E - 1.0) * pow(color, vec3(1.16721, 1.27087, 1.02953)) + 1.0);

    FragColor = vec4(color, 1.0);
}
