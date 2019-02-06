/**
 * Copyright (c) 2017-2019 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

uniform float coeff = 5;

void main() {
    vec2 disp = vec2(coeff / 1000);

    vec4 color1 = texture(InputTexture, TexCoord - disp);
    vec4 color2 = texture(InputTexture, TexCoord);
    vec4 color3 = texture(InputTexture, TexCoord + disp);

    FragColor = vec4((color1.rgb + color2.rgb + color3.rgb) / 3, 1);
}
