/**
 *Copyright (c) 2018-2019 DRRP-Team
 *
 *This software is released under the MIT License.
 *https://opensource.org/licenses/MIT
 */

class ShaderControllerActor : EventHandler {
    static int SetEnabled(int player, String name, bool enabled) {
        if (player != 0) {
            Shader.SetEnabled(players[player - 1], name, enabled);
        } else {
            for(int i = 0; i < players.Size(); i++) {
                Shader.SetEnabled(players[i], name, enabled);
            }
        }

        return 1;
    }

    static int SetUniformFloat(int player, String name, String uniform, float value) {
        if (player != 0) {
            Shader.SetUniform1f(players[player - 1], name, uniform, value);
        } else {
            for(int i = 0; i < players.Size(); i++) {
                Shader.SetUniform1f(players[player - 1], name, uniform, value);
            }
        }

        return 1;
    }

    static int SetUniformInt(int player, String name, String uniform, int value) {
        if (player != 0) {
            Shader.SetUniform1i(players[player - 1], name, uniform, value);
        } else {
            for(int i = 0; i < players.Size(); i++) {
                Shader.SetUniform1i(players[player - 1], name, uniform, value);
            }
        }

        return 1;
    }
}
