package sep3.model.operation;
import sep3.model.CPU;

public class MovOperation extends Operation {
	private CPU cpu;
	MovOperation(CPU cpu) { this.cpu = cpu; }
	public void operate() {
		int i = cpu.getBBus().getValue();
		int p = psw_NZ(i);
		p |= cpu.getRegister(CPU.REG_PSW).getValue() & 0x0001;			// C will not change
		cpu.getSBus().setValue(i);
		cpu.getRegister(CPU.REG_PSW).setPreValue(p);
	}
}
