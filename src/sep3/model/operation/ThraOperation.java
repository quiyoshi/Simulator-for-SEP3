package sep3.model.operation;
import sep3.model.CPU;

// Aバスの値をSバスに素通しする
public class ThraOperation extends Operation {
	private CPU cpu;
	ThraOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getSBus().setValue(cpu.getABus().getValue());
	}
}
