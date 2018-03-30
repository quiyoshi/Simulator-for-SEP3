package sep3.model.operation;
import sep3.model.CPU;

public class BrvOperation extends Operation {
	private CPU cpu;
	BrvOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getSBus().setValue(cpu.getBBus().getValue());
	}
}
