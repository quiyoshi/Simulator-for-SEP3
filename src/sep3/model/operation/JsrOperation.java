package sep3.model.operation;
import sep3.model.CPU;

public class JsrOperation extends Operation {
	private CPU cpu;
	JsrOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getSBus().setValue(cpu.getABus().getValue());
	}
}
