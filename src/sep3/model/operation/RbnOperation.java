package sep3.model.operation;
import sep3.model.CPU;

public class RbnOperation extends Operation {
	private CPU cpu;
	RbnOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		int i = cpu.getABus().getValue();
		int j = cpu.getBBus().getValue();
		int o = i + j;
		cpu.getSBus().setValue(o & 0xFFFF);
	}
}
