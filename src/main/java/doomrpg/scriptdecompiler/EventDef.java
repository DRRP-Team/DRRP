package doomrpg.scriptdecompiler;

public class EventDef {
	public int x;
	public int y;
	public IByteCodeReference start;
	public IByteCodeReference end;
	public int unkn;
	public int unkn2;
	
	public String toIR() {
		return "X=" + x + " Y=" + y + " START=L_" + start + " END=L_" + (end)
		+ " UNKN=" + unkn + " UNKN2=" + unkn2;
	}
}
