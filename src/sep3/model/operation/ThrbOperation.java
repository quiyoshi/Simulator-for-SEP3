package sep3.model.operation;
import sep3.model.CPU;

// Bバスの値をSバスに素通しする
public class ThrbOperation extends Operation {
	private CPU cpu;
	ThrbOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getSBus().setValue(cpu.getBBus().getValue());
	}
}
