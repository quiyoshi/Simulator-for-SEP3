package sep3.model.operation;
import sep3.model.CPU;

public class BitOperation extends Operation {
	private CPU cpu;
	BitOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		int i = cpu.getABus().getValue();
		int j = cpu.getBBus().getValue();
		int o = i & j;
		int p = psw_NZ(o);
		p &= ~CPU.PSW_V;
		cpu.getRegister(CPU.REG_PSW).setPreValue(p);
		cpu.getSBus().setValue(o);
	}
}
