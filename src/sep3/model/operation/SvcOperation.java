package sep3.model.operation;
import sep3.model.CPU;

public class SvcOperation extends Operation {
	private CPU cpu;
	SvcOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getSBus().setValue(cpu.getABus().getValue());
	}
}
