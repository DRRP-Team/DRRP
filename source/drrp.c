#include <ACS_ZDoom.h>

#include <limits.h>
#include <stddef.h>
#include <stdfix.h>
#include <stdio.h>
#include <stdbool.h>

#define MAX_LEVELS 16
#define JUNCTION_LEVELNUM 2
//#define DEBUGSCRIPT

bool exitedLevels[MAX_LEVELS] = {false};

#ifdef DEBUGSCRIPT
[[call("ScriptS"), script("Enter")]]
void CheckLevelExits() {
	ACS_BeginPrint();
	for(int i = 0; i < MAX_LEVELS; i++) {
		__nprintf("%d ", exitedLevels[i]);
	}
	ACS_EndPrint();
}
#endif

[[call("ScriptI"), address(777)]]
void ExitLevel(int spotnum) {
	exitedLevels[ACS_GetLevelInfo(LEVELINFO_LEVELNUM)] = true;
	ACS_Teleport_NewMap(JUNCTION_LEVELNUM, spotnum, 0);
}