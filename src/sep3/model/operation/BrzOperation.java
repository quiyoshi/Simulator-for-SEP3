package sep3.model.operation;

import sep3.model.CPU;

public class BrzOperation extends Operation {
	private CPU cpu;
	BrzOperation(CPU cpu) { this.cpu = cpu; }

	@Override
	public void operate() {
		cpu.getSBus().setValue(cpu.getBBus().getValue());
	}
}
