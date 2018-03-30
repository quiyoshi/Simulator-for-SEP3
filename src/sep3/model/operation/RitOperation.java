package sep3.model.operation;

import sep3.model.CPU;

public class RitOperation extends Operation {
	private CPU cpu;
	RitOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getSBus().setValue(cpu.getBBus().getValue());
	}
}
