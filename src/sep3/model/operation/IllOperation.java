package sep3.model.operation;
import sep3.model.CPU;

public class IllOperation extends Operation {
	private CPU cpu;
	IllOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getIllLamp().on();
	}
}
