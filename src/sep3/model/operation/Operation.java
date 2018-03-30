package sep3.model.operation;
import sep3.model.CPU;

// 命令に共通するメソッド
public abstract class Operation {
	public abstract void operate();
	public boolean bit(int i, int mask) { return (i & mask) != 0; }
	public int psw_NZ(int o) {
		int p = 0;
		if (bit(o, 0x8000))						{ p |= CPU.PSW_N; }	// always 0
		if (o == 0)								{ p |= CPU.PSW_Z; }
		return p;
	}
}
