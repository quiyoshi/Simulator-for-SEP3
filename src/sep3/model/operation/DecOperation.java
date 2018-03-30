package sep3.model.operation;
import sep3.model.CPU;

// プレデクリメント・レジスタ間接のとき使う
public class DecOperation extends Operation {
	private CPU cpu;
	DecOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		cpu.getSBus().setValue((cpu.getABus().getValue() - 1) & 0xFFFF);
	}
}
