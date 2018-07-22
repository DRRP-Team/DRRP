namespace = "ZDoom";

conversation {
    actor = "DRRPShop"; // Not a number!
    page {
		 goodbye = "Close";
         name = "Shop";
         dialog = "What do you want to buy?";
         choice {
             text = "Small medikit [8 credits]";
             giveitem = "SmallMedikit";
             nextpage = 1;
             nomessage = "Not enough credits!";
             cost {
                 item = "Credit";
                 amount = 8;
             }
         }
         choice {
             text = "Large medikit [35 credits]";
             giveitem = "LargeMedikit";
             nextpage = 1;
             nomessage = "Not enough credits!";
             cost {
                 item = "Credit";
                 amount = 35;
             }
         }
         choice {
             text = "Next (Ammo)";
             nextpage = 2;
         }
    }

    page {
		 goodbye = "Close";
         name = "Ammo";
         dialog = "What do you want to buy?";
         choice {
             text = "10x Hal. Cans [5 credits]";
             giveitem = "Balloon";
             nomessage = "Not enough credits!";
             nextpage = 2;
             cost {
                 item = "Credit";
                 amount = 5;
             }
         }
         choice {
             text = "10x Bullets [5 credits]";
             giveitem = "ClipBox";
             nomessage = "Not enough credits!";
             nextpage = 2;
             cost {
                 item = "Credit";
                 amount = 5;
             }
         }
         choice {
             text = "10x Shells [10 credits]";
             giveitem = "LargeMedikit";
             nomessage = "Not enough credits!";
             nextpage = 2;
             cost {
                 item = "Credit";
                 amount = 10;
             }
         }
         choice {
             text = "3x Rockets [10 credits]";
             giveitem = "RocketBox";
             nomessage = "Not enough credits!";
             nextpage = 2;
             cost {
                 item = "Credit";
                 amount = 10;
             }
         }
         choice {
             text = "10x Cells [15 credits]";
             giveitem = "CellPack";
             nomessage = "Not enough credits!";
             nextpage = 2;
             cost {
                 item = "Credit";
                 amount = 15;
             }
         }
         choice {
             text = "Next (Stats)";
             nextpage = 3;
         }
    }

    page {
		 goodbye = "Close";
         name = "Stats";
         dialog = "What do you want to buy?";
         choice {
             text = "+1 Accuracy [15 credits]";
             giveitem = "HealthPowerUp";
             nomessage = "Not enough credits!";
             nextpage = 3;
             cost {
                 item = "Credit";
                 amount = 15;
             }
         }
         choice {
             text = "+1 Agility [15 credits]";
             giveitem = "HealthPowerUp";
             nomessage = "Not enough credits!";
             nextpage = 3;
             cost {
                 item = "Credit";
                 amount = 15;
             }
         }
         choice {
             text = "+1 Strength [20 credits]";
             giveitem = "HealthPowerUp";
             nomessage = "Not enough credits!";
             nextpage = 3;
             cost {
                 item = "Credit";
                 amount = 20;
             }
         }
         choice {
             text = "+1 Defense [20 credits]";
             giveitem = "HealthPowerUp";
             nomessage = "Not enough credits!";
             nextpage = 3;
             cost {
                 item = "Credit";
                 amount = 20;
             }
         }
         choice {
             text = "Back";
             nextpage = 1;
         }
    }
}
