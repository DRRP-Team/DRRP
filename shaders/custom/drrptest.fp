void main() {
vec4 c = texture(InputTexture, TexCoord);
vec2 uv = TexCoord;
uv *=  1.0 - uv.yx;
float vig = uv.x*uv.y * 15.0;
vig = pow(vig, 0.25);
FragColor = c * vig;
}