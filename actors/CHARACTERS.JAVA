//10500-10555

//Eggheaders

actor Scientist 10500 {
//$Category Characters
	Radius 20
	Height 56
	+SOLID
	+USESPECIAL
	states {
		Spawn:
			SCIE A 1
		Loop
	}
}

actor ShootableScientist : Scientist 10503 {
	
//$Category Characters
	+SHOOTABLE
	Health 20
	states {
		Death:
    POSS H 5
    POSS I 5 A_Scream
    POSS J 5 A_NoBlocking
    POSS K 5
    POSS L -1
    Stop
  XDeath:
    POSS M 5
    POSS N 5 A_XScream
    POSS O 5 A_NoBlocking
    POSS PQRST 5
    POSS U -1
    Stop
	}
}

actor Scientist1 : Scientist 10501 {//black
//$Category Characters
Translation "16:79=64:79"
}

actor DrGuerard : Scientist 10502 {//gerard
//$Category Characters
Translation "80:106=[132,174,165]:[41,73,66]", "192:192=[132,174,165]:[132,174,165]"
}

//Guys

actor Civilan0 10510 {
//$Category Characters
	Radius 20
	Height 56
	+SOLID
	states {
		Spawn:
			CIV0 A 1
		Loop
	}
}

actor Civilan1 : Civilan0 10511 {//purple
//$Category Characters
Translation "160:167=250:254"
}

actor Civilan2 : Civilan0 10512 {//green
//$Category Characters
Translation "160:167=112:127"
}



//Marines
actor Marine0 10520 {
//$Category Characters
	Radius 20
	Height 56
	+SOLID
	states {
		Spawn:
			DPR0 A 1
		Loop
	}
}

actor Marine1 : Marine0 10521 {//blue
//$Category Characters
Translation "122:127=240:247", "117:122=204:207"
}
