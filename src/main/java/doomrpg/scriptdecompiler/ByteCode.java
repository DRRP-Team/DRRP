package doomrpg.scriptdecompiler;

import java.nio.ByteBuffer;
import java.util.Iterator;

public class ByteCode {
	protected ByteCodeElement[] bc;

	public IByteCodeReference ref(final int pos) {
		return new IByteCodeReference() {
			public ByteCodeElement get() {
				return bc[pos];
			}
			
			@Override
			public String toString() {
				return String.valueOf(pos);
			}
		};
	}

	public void readFromByteBuffer(ByteBuffer bb) {
		int count = bb.getShort();

		bc = new ByteCodeElement[count];

		for (int i = 0; i < bc.length; i++) {
			bc[i] = new ByteCodeElement();
			bc[i].cmdid = bb.get();
			bc[i].arg1 = bb.getInt();
			bc[i].arg2 = bb.getInt();
		}

	}

	public String toIR() {
		String ir = "[COMMANDS]\n# " + bc.length + " cmds found\n";
		for (int i = 0; i < bc.length; i++) {
			ir += bc[i].toIR(i);
		}
		return ir;
	}
	
	public Iterator<ByteCodeElement> iterator(IByteCodeReference ref1, IByteCodeReference ref2) {
		int pos1 = 0, pos2 = 0;
		for(int i = 0; i < bc.length; i++) {
			if(bc[i] == ref1.get()) {
				pos1 = i;
			}
			if(bc[i] == ref2.get()) {
				pos2 = i + 1;
			}
		}
		
		final int pos1_ = pos1;
		final int pos2_ = pos2;
		
		Iterator<ByteCodeElement> it = new Iterator<ByteCodeElement>() {
			private int pos1 = pos1_;
			private int pos2 = pos2_;
			
			public boolean hasNext() {
				return pos1 + 1 <= pos2;
			}

			public ByteCodeElement next() {
				return bc[pos1++];
			}
		};
		return it;
	}
}
