package doomrpg.scriptdecompiler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App {
	public static short getUnsignedByte(ByteBuffer bb) {
		return ((short) (bb.get() & 0xff));
	}

	public static void main(String[] args) throws IOException, ParseException {
		if (args.length != 1) {
			System.out.println("Usage: <path/to/file.bsp>");
			System.exit(1);
		}
		
		FileInputStream fis = new FileInputStream(args[0]);

		FileChannel fc = fis.getChannel();
		ByteBuffer bb = fc.map(MapMode.READ_ONLY, 0, fc.size());
		bb.order(ByteOrder.LITTLE_ENDIAN);

		bb.position(bb.position() + 13);

		bb.position(bb.getShort() * 10 + bb.position());

		bb.position(bb.getShort() * 8 + bb.position());

		List<Thing> things = new ArrayList<>();
		
		{
			int count = bb.getShort();
			for(int i = 0; i < count; i++) {
				Thing thing = new Thing();
				thing.x = bb.get() & 0xff;
				thing.y = bb.get() & 0xff;
				thing.type = bb.get() & 0xff;
				thing.flags = bb.getShort();
				things.add(thing);
			}
		}
		

		List<EventDef> events = new LinkedList<>();
		ByteCode bc = new ByteCode();

		{
			int count = bb.getShort();

			for (int i = 0; i < count; i++) {
				int packedInt = bb.getInt();
				int x = (packedInt & 0x1F);
				int y = ((packedInt & 0x3E0) >> 5);
				int start = ((packedInt & 0x7FC00) >> 10);
				int length = ((packedInt & 0x1F80000) >> 19);
				int unkn = packedInt & 33030144;
				int unkn2 = packedInt & 0xFE000000;

				EventDef edef = new EventDef();
				edef.x = x;
				edef.y = y;
				edef.start = bc.ref(start);
				edef.end = bc.ref(start + length - 1);
				edef.unkn = unkn;
				edef.unkn2 = unkn2;
				events.add(edef);
			}
			
		}

		bc.readFromByteBuffer(bb);

		int sn = 1;
		
		/*Map<Integer, Integer> json = new HashMap<>();
		JSONParser parser = new JSONParser();
		Map<String, Integer> obj = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream("things.json")));*/
		
		System.out.println("#include \"zcommon.acs\"");
		
		for (EventDef event : events) {
			System.out.println("script " + sn + " (int arg0, int arg1, int arg2) { //" + event.toIR());

			Iterator<ByteCodeElement> it = bc.iterator(event.start, event.end);
			
			CoordList cl = new CoordList();
			
			String script = "";

			while (it.hasNext()) {
				ByteCodeElement bce = it.next();
				script += ("    " + bce.toACS(events, cl, things) + " // " + bce.toIR(0) + "\n");
			}
			
			for(int i = 0; i < cl.l.size(); i++) {
				System.out.println("    int sgenid" + i + " = " + ((cl.l.get(i).x << 5) | (cl.l.get(i).y)) + "; // " + cl.l.get(i).toString());
			}
			
			System.out.print(script);

			System.out.println("}");

			sn++;
		}

		fis.close();
	}
}
