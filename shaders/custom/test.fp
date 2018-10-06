/**
 * Copyright (c) 2017-2018 DRRP-Team
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
}