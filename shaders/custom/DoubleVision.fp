/**
 * Copyright (c) 2017-2019 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

uniform float coeff = 5;

void main() {
    vec4 color1 = texture(InputTexture, vec2(TexCoord.x - coeff / 1000, TexCoord.y - coeff / 1000));
    vec4 color2 = texture(InputTexture, vec2(TexCoord.x, TexCoord.y));
    vec4 color3 = texture(InputTexture, vec2(TexCoord.x + coeff / 1000, TexCoord.y + coeff / 1000));

    FragColor = vec4((color1.rgb + color2.rgb + color3.rgb) / vec3(3), 1);
}
