package sep3.model.operation;
import sep3.model.CPU;

// ポストインクリメント・レジスタ間接のとき、命令フェッチのときに使う
public class IncOperation extends Operation {
	private CPU cpu;
	IncOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getSBus().setValue((cpu.getABus().getValue() + 1) & 0xFFFF);
	}
}
