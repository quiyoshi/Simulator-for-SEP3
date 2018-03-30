package sep3.model.operation;
import sep3.model.CPU;

public class JmpOperation extends Operation {
	private CPU cpu;
	JmpOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getSBus().setValue(cpu.getBBus().getValue());
	}
}
