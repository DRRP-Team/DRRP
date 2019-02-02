/**
 * Copyright (c) 2017-2019 DRRP-Team
 * 
 * This software is released under the MIT License.
 * https://opensource.org/licenses/MIT
 */


void main() {
    #ifdef DYNLIGHT
        FragColor = vec4(0, 255, 0, 1);
    #else
        FragColor = vec4(255, 0, 0, 1);
    #endif

    vec4 c = texture(InputTexture, TexCoord);

    vec3 color = c.rgb;
    float alpha = c.a;

    color.rgb *= vec3(0.3, 0.3, 0.3);
    //color.rgb += vec3(0.3, 0.3, 0.3);

    FragColor = vec4(color, alpha);
}
