package sep3.model.operation;

import sep3.model.CPU;

public class RetOperation extends Operation {
	private CPU cpu;
	RetOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getSBus().setValue(cpu.getBBus().getValue());
	}
}
