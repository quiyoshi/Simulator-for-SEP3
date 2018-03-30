package sep3.model.operation;
import sep3.model.CPU;

public class HltOperation extends Operation {
	private CPU cpu;
	HltOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getHaltLamp().on();
	}
}
