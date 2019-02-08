/**
 * Copyright (c) 2017-2019 DRRP-Team
 * Copyright (c) PROPHESSOR 
 *
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */

// uniform float distortion    = 5;
// uniform float desaturation  = 0.5;

void main() {
    // Default values
    float _distortion = distortion;
    float _desaturation = desaturation;
    if (_distortion == 0.0) _distortion = 5.0;
    if (_desaturation == 0.0) _desaturation = 0.5;

    vec2 disp       = vec2(_distortion / 1000);

    vec4 color1     = texture(InputTexture, TexCoord - disp);
    vec4 color2     = texture(InputTexture, TexCoord);
    vec4 color3     = texture(InputTexture, TexCoord + disp);

    vec3 color      = (color1.rgb + color2.rgb + color3.rgb) / 3;
    float grayscale = (color.r + color.g + color.b) / 3;

    FragColor       = vec4(mix(color, vec3(grayscale), _desaturation), 1.);
}
