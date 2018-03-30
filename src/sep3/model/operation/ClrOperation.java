package sep3.model.operation;

import sep3.model.CPU;

public class ClrOperation extends Operation{
	private CPU cpu;
	ClrOperation(CPU cpu) { this.cpu = cpu; }

	public void operate(){
		cpu.getRegister(CPU.REG_PSW).setPreValue(0x0004);
		cpu.getSBus().setValue(0x0000 & 0xFFFF);
	}
}
