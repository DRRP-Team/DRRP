package doomrpg.scriptdecompiler;

import java.util.ArrayList;

public class CoordList {
	public ArrayList<Vector2> l = new ArrayList<>();
	
	public int getPos(int x, int y) {
		if(!l.contains(new Vector2(x, y))) {
			l.add(new Vector2(x, y));
		}
		return l.indexOf(new Vector2(x, y));
	}
}
