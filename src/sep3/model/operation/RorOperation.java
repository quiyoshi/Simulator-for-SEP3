package sep3.model.operation;

import sep3.model.CPU;

public class RorOperation extends Operation {
	private CPU cpu;
	RorOperation(CPU cpu) { this.cpu = cpu; }

	@Override
	public void operate() {
		int i = cpu.getABus().getValue();  //T15.T14.T13.T12  T11.T10.…
		int j = i & 0x0001;  //0.0.0.T0
		int k = i >>> 1;  //0.T15.T14.T13  T12.T11.T10.…
		int l = j << 15;  //T0.0.0.0
		int o = k | l;  //T14.T13.T12.T11  T10.T9.….T1.T0.T15

		int p = psw_NZ(o);
		if(j == 0x0001){p |= CPU.PSW_C;} //[T0 == 1] C = 1
		p &= 0x111D; // D = 1101 [V = 0]

		cpu.getRegister(CPU.REG_PSW).setPreValue(p);
		cpu.getSBus().setValue(o);
	}
}
