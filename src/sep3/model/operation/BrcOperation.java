package sep3.model.operation;

import sep3.model.CPU;

public class BrcOperation extends Operation {
	private CPU cpu;
	BrcOperation(CPU cpu) { this.cpu = cpu; }

	@Override
	public void operate() {
		cpu.getSBus().setValue(cpu.getBBus().getValue());
	}
}
