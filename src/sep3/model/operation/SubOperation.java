package sep3.model.operation;
import sep3.model.CPU;

public class SubOperation  extends Operation {
	private CPU cpu;
	SubOperation(CPU cpu) { this.cpu = cpu; }
	public void operate(){
		int i = cpu.getABus().getValue();
		int j = cpu.getBBus().getValue();
		int o = i - j;
		int p = psw_NZ(o);
		boolean sameMSBin  = (i & 0x8000) == (j & 0x8000);
		boolean sameMSBout = (i & 0x8000) == (o & 0x8000);
		if (!(sameMSBin || sameMSBout))			{ p |= CPU.PSW_V; }
		if (bit(o, 0x10000))					{ p |= CPU.PSW_C; }
		cpu.getRegister(CPU.REG_PSW).setPreValue(p);
		cpu.getSBus().setValue(o & 0xFFFF);
	}
}
