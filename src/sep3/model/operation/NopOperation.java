package sep3.model.operation;
import sep3.model.CPU;

public class NopOperation extends Operation {
	private CPU cpu;
	NopOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		// do nothing
	}
}
