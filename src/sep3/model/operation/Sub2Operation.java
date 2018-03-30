package sep3.model.operation;
import sep3.model.CPU;

public class Sub2Operation extends Operation {
	private CPU cpu;
	Sub2Operation(CPU cpu) { this.cpu = cpu; }
	public void operate(){
		int i = cpu.getABus().getValue();
		int j = cpu.getBBus().getValue();
		int o = i - j;
		cpu.getSBus().setValue(o & 0xFFFF);
	}
}
