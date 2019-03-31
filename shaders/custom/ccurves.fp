/**
 * Copyright (c) 2017-2019 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

void main() {
    vec3 color = texture(InputTexture, TexCoord).rgb;
	
	const float M_E = 2.71828;

	// color = log(pow(color, 1.0 / (1.0 - sqrt(0.1) * vec3(/*0.3086*/ 0.4096, 0.6094, 0.0820) * 1.106)) * 1.71828 + 1.0);
	color = log((M_E - 1.0) * pow(color, vec3(1.16721, 1.27087, 1.02953)) + 1.0);

    FragColor = vec4(color, 1.);
}