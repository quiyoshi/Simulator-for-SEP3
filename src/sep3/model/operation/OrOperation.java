package sep3.model.operation;

import sep3.model.CPU;

public class OrOperation extends Operation {
	private CPU cpu;
	OrOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		int i = cpu.getABus().getValue();
		int j = cpu.getBBus().getValue();
		int o = i | j;
		int p = psw_NZ(o);
		p &= 0x1101;
		cpu.getRegister(CPU.REG_PSW).setPreValue(p);
		cpu.getSBus().setValue(o & 0xFFFF);
	}
}
