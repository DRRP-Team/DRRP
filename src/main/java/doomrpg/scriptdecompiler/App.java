package doomrpg.scriptdecompiler;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class App {
	public static short getUnsignedByte(ByteBuffer bb) {
		return ((short) (bb.get() & 0xff));
	}

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream(args[0]);

		FileChannel fc = fis.getChannel();
		ByteBuffer bb = fc.map(MapMode.READ_ONLY, 0, fc.size());
		bb.order(ByteOrder.LITTLE_ENDIAN);

		bb.position(bb.position() + 13);

		bb.position(bb.getShort() * 10 + bb.position());

		bb.position(bb.getShort() * 8 + bb.position());

		bb.position(bb.getShort() * 5 + bb.position());

		{
			int count = bb.getShort();

			System.out.println("# " + count + " events found");
			System.out.println("# offset=" + (bb.position() - 2));

			System.out.println("[EVENTS]");
			for (int i = 0; i < count; i++) {
				int packedInt = bb.getInt();
				int x = (packedInt & 0x1F);
				int y = ((packedInt & 0x3E0) >> 5);
				int start = ((packedInt & 0x7FC00) >> 10);
				int length = ((packedInt & 0x1F80000) >> 19);
				int unkn = packedInt & 33030144;
				int unkn2 = packedInt & 0xFE000000;
				System.out
						.println("X=" + x + " Y=" + y + " START=L_" + start + " END=L_" + (start + length - 1)
								+ " UNKN=" + unkn + " UNKN2=" + unkn2);
			}
			System.out.println();
		}

		System.out.println("[COMMANDS]");

		int count = bb.getShort();

		System.out.println("# " + count + " cmds found");
		System.out.println("# offset=" + (bb.position() - 2));

		for (int i = 0; i < count; i++) {
			int cmdid = bb.get();
			int arg1 = bb.getInt();
			int arg2 = bb.getInt();
			String cmdname = "UNKNOWN_" + cmdid;
			String cmdparams = "ARG1=" + arg1;
			String cmdopts = "( ";
			if ((arg2 & 0x100) != 0) {
				cmdopts += "USE ";
			}
			if ((arg2 & 0xF) != 0) {
				cmdopts += "ENTER_FROM( ";
				if ((arg2 & 0x8) != 0) {
					cmdopts += "WEST ";
				}
				if ((arg2 & 0x4) != 0) {
					cmdopts += "SOUTH ";
				}
				if ((arg2 & 0x2) != 0) {
					cmdopts += "EAST ";
				}
				if ((arg2 & 0x1) != 0) {
					cmdopts += "NORTH ";
				}
				cmdopts += ") ";
			}
			if ((arg2 & 0xF0) != 0) {
				cmdopts += "LEAVE_TO( ";
				if ((arg2 & 0x80) != 0) {
					cmdopts += "EAST ";
				}
				if ((arg2 & 0x40) != 0) {
					cmdopts += "NORTH ";
				}
				if ((arg2 & 0x20) != 0) {
					cmdopts += "WEST ";
				}
				if ((arg2 & 0x10) != 0) {
					cmdopts += "SOUTH ";
				}
				cmdopts += ") ";
			}
			if ((arg2 & 0x200) != 0) {
				cmdopts += "MODIFYWORLD ";
			}
			cmdopts += "IF_VAR( " + ((arg2 & 0xFFFF0000) >> 16) + " ) ";
			cmdopts += ")";

			switch (cmdid) {
			case 1:
				cmdname = "TELEPORT";
				cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF) + " DIR=" + ((arg1 >> 20) & 0x3FF); 
				break;
			case 2:
				cmdname = "CH_LEVEL";
				cmdparams = "LEVEL_NAME=" + (arg1 & 0xFF) + " UNKN=" + ((arg1 & 2147483647) >> 8) + " COMPLETE=" + (arg1 & -2147483648);
				break;
			case 3:
				cmdname = "RUNPOS";
				cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF) + " VAL=" + ((arg1 >> 16) & 0xFFFF); 
				break;
			case 4:
				cmdname = "STBARMSG";
				cmdparams = "STR=" + arg1;
				break;
			case 5:
				cmdname = "PLAYER_DAMAGE";
				cmdparams = "DMG=" + arg1;
				break;
			case 7:
				cmdname = "SHOW_THING";
				cmdparams = "ID=" + (arg1 & 0xFF) + " STATE=" + ((arg1 >> 8) & 0xFF); 
				break;
			case 8:
				cmdname = "SHOW_MONOLOG";
				cmdparams = "STR=" + arg1;
				break;
			case 9:
				cmdname = "AUTOMAP";
				cmdparams = "";
				break;
			case 10:
				cmdname = "PASSCODE_OR_HALT";
				cmdparams = "PASS=" + (((arg1) & 0xFF) + 1) + " MSG=" + (((arg1 >> 8) & 0xFF) + 1);
				break;
			case 11:
				cmdname = "SET_VAR";
				cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF) + " VAL=" + ((arg1 >> 16) & 0xFFFF); 
				break;
			case 12:
				cmdname = "DOOR_LOCK";
				cmdparams = "LINE=" + arg1;
				break;
			case 13:
				cmdname = "DOOR_UNLOCK";
				cmdparams = "LINE=" + arg1;
				break;
			case 15:
				cmdname = "DOOR_OPEN";
				cmdparams = "LINE=" + arg1;
				break;
			case 16:
				cmdname = "DOOR_CLOSE";
				cmdparams = "LINE=" + arg1;
				break;
			case 18:
				cmdname = "HIDE_THINGS_AT";
				cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF); 
				break;
			case 19:
				cmdname = "INC_VAR";
				cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF); 
				break;
			case 20:
				cmdname = "DEC_VAR";
				cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF); 
				break;
			case 21:
				cmdname = "INC_STAT";
				cmdparams = "AMOUNT=" + ((arg1 >> 8) & 0xFF) + " STAT=" + ((arg1) & 0xFF);
				break;
			case 22:
				cmdname = "DEC_STAT";
				cmdparams = "AMOUNT=" + ((arg1 >> 8) & 0xFF) + " STAT=" + ((arg1) & 0xFF);
				break;
			case 23:
				cmdname = "CHECK_STAT_OR_SHOW_MONOLOG";
				cmdparams = "STR=" + ((arg1 >> 16) & 0xFFFF) + " AMOUNT=" + ((arg1 >> 8) & 0xFF) + " STAT=" + ((arg1) & 0xFF);
				break;
			case 24:
				cmdname = "STBARMSG_ALT";
				cmdparams = "STR=" + arg1;
				break;
			case 25:
				cmdname = "EXPLODE";
				cmdparams = "X=" + (arg1 & 0xFF) + " Y=" + ((arg1 >> 8) & 0xFF) + " TYPE=" + ((arg1 >> 24) & 0xFF); 
				break;
			case 26:
				cmdname = "SHOW_MONOLOG_WITH_SOUND";
				cmdparams = "STR=" + arg1;
				break;
			case 27:
				cmdname = "NEXT_LEVEL_START_POS";
				cmdparams = "ARG1=" + arg1 + " X=" + ((arg1 >> 8) & 0xFF) + " Y=" + ((arg1 >> 16) & 0xFF);
				break;
			case 29:
				cmdname = "EARTHQUAKE";
				cmdparams = "TIME=" + (arg1 & 0xFFFFFF) + " INTENSITY=" +  ((arg1 >> 24) & 0xFF);
				break;
			case 30:
				cmdname = "FLOORCOLOR";
				cmdparams = "COLOR=" + (arg1 & 0xFFFFFF);
				break;
			case 31:
				cmdname = "CEILCOLOR";
				cmdparams = "COLOR=" + (arg1 & 0xFFFFFF);
				break;
			case 32:
				cmdname = "TAKE_OR_RETURN_WEAPONS";
				cmdparams = "ACTION=" + arg1;
				break;
			case 33:
				cmdname = "SHOP";
				cmdparams = "ID=" + arg1;
				break;
			case 34:
				cmdname = "SET_THING_STATE";
				cmdparams += " X=" + (arg1 & 0x1F) + " Y=" + ((arg1 >> 5) & 0x1F) + " VAL=" + ((arg1 >> 16) & 0xFF); 
				break;
			case 35:
				cmdname = "PARTICLE";
				cmdparams = "COLOR=" + (arg1 & 0xFFFFFF) + " EFFECT=" +  ((arg1 >> 24) & 0xFF);
				break;
			case 36:
				cmdname = "FRAME";
				break;
			case 37:
				cmdname = "SLEEP";
				cmdparams = "MSEC=" + arg1;
				break;
			case 38:
				cmdname = "UNKNOWN_REACTOR_LIFT";
				break;
			case 39:
				cmdname = "SHOW_MONOLOG_IF_LEVEL_UNFINISHED";
				cmdparams = "STR=" + (arg1 & 0xFFFF) + " LEVEL_ID=" +  ((arg1 >> 8) & 0xFFFF);
				break;
			case 40:
				cmdname = "NOTEBOOK_ADD";
				cmdparams = "STR=" + arg1;
				break;
			case 41:
				cmdname = "REQUIRE_KEYCARD";
				cmdparams = "KEY=" + arg1;
				break;

			default:
				break;
			}
			System.out.println("L_" + i + ": " + cmdopts + " " + cmdname + " " + cmdparams);
		}
		
		fis.close();
	}
}
