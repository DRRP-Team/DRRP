/**
 *Copyright (c) 2018-2019 DRRP-Team
 *
 *This software is released under the MIT License.
 *https://opensource.org/licenses/MIT
 */

class NullHUD : BaseStatusBar {
    Class<BaseStatusBar> oldbar;

    Override
    void Init() {
        Super.Init();
        SetSize(0,320,200);
    }
}

class HUDController {
    static ui void SwitchHUD(bool is_enable) {
        if (!is_enable) {
            if (StatusBar is 'NullHUD') return;
            let n = new("NullHUD"); // set null hud

            n.oldbar = StatusBar.GetClass();
            StatusBar.Destroy();

            StatusBar = n;
            StatusBar.AttachToPlayer(players[consoleplayer]);
            n.Init();
            StatusBar.NewGame();
        } else if (StatusBar is 'NullHUD') {
            let n = BaseStatusBar(new(NullHUD(StatusBar).oldbar));

            n.Init();
            StatusBar.Destroy();
            StatusBar = n;
            StatusBar.AttachToPlayer(players[consoleplayer]);
            StatusBar.NewGame();
        }
    }
}
