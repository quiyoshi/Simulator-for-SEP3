package sep3.model.operation;
import sep3.model.CPU;

public class BrnOperation extends Operation {
	private CPU cpu;
	BrnOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getSBus().setValue(cpu.getBBus().getValue());
	}
}
