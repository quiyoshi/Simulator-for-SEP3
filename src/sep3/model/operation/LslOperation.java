package sep3.model.operation;

import sep3.model.CPU;

public class LslOperation extends Operation {
	private CPU cpu;
	LslOperation(CPU cpu) { this.cpu = cpu; }

	@Override
	public void operate() {
		int i = cpu.getABus().getValue();  //T15.T14.T13.T12  T11.T10.…
		int j = i & 0x8000;  //T15.0.0.0  0.0.…
		int k = i << 1;  //….0.T15  T14.T13.T12.T11  T10.T9.T8.…
		int o = k & 0xFFFF;  //T14.T13.T12.T11  T10.T9.…

		int p = psw_NZ(o);
		if(j == 0x8000){p |= CPU.PSW_C;} //[T15 == 1] C = 1
		p &= 0x111D; // D = 1101 [V = 0]

		cpu.getRegister(CPU.REG_PSW).setPreValue(p);
		cpu.getSBus().setValue(o);
	}
}
