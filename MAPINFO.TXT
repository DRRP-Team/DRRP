gameinfo {
    PlayerClasses = "DoomRPGPlayer"
}

clearepisodes
Episode INTRO
{
    Name = "DOOM RPG"
    NoSkillMenu
}

map TITLEMAP "Introduction"
{
 music = "D_DEAD"
 SkyBox = DARK0305
}


Map INTRO "Intro"
{
    LevelNum = 1
    Cluster = 1
    SkyBox = SKY2
    Music = D_DEAD
}

cluster 1 {
    hub
}

map HUB "Junction" {
    levelnum = 2
    cluster = 1
    skybox = sky2
    music = D_DEAD
}

map SEC1 "Sector 1" {
    levelnum = 3
    cluster = 1
    skybox = sky2
    music = D_DOOM
	EventHandlers = "DoorCodeInputHandler" // PROPHESSOR, there aren't any errors even on zandronum
}

map SEC2 "Sector 2" {
    levelnum = 4
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map SEC3 "Sector 3" {
    levelnum = 5
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map SEC4 "Sector 4" {
    levelnum = 6
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map SEC5 "Sector 5" {
    levelnum = 7
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map SEC6 "Sector 6" {
    levelnum = 8
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map SEC7 "Sector 7" {
    levelnum = 9
    cluster = 1
    skybox = sky2
    music = D_DOOM
}

map XHUB "Junction" {
    levelnum = 10
    cluster = 1
    skybox = sky2
    music = D_DEAD
}

map REAC "Reactor" {
    levelnum = 11
    cluster = 1
    skybox = sky2
    music = D_DOOM
}