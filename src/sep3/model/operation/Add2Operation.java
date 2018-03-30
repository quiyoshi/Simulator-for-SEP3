package sep3.model.operation;
import sep3.model.CPU;

// PSW の更新をしない ADD （相対分岐などで使う）
public class Add2Operation extends Operation {
	private CPU cpu;
	Add2Operation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		int i = cpu.getABus().getValue();
		int j = cpu.getBBus().getValue();
		int o = i + j;
		cpu.getSBus().setValue(o & 0xFFFF);
	}
}
