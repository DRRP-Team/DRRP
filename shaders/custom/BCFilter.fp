/**
 * Copyright (c) 2018 DRRP-Team
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

/**
 * [float] brightness - Яркость ±255; Стандарт - 0
 * [float] contrast   - Контрасность 0+;  Стандарт - 1.0
 */

uniform float brightness = 0;
uniform float contrast = 1.0;

void main() {
    vec4 color = texture(InputTexture, TexCoord);
    FragColor = vec4(color.rgb * contrast + brightness / 255, 1);
}