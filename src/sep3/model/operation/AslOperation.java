package sep3.model.operation;

import sep3.model.CPU;

public class AslOperation extends Operation {
	private CPU cpu;
	AslOperation(CPU cpu) { this.cpu = cpu; }

	@Override
	public void operate() {
		int i = cpu.getABus().getValue();  //T15.T14.T13.T12  T11.T10.…
		int j = i & 0x8000;  //T15.0.0.0  0.0.…
		int k = i << 1;  //….0.T15  T14.T13.T12.T11  T10.T9.T8.…
		int l = k & 0x8000;  //T14.0.0.0  0.0.…
		int o = k & 0x7FFF | j;  //T15.T13.T12.T11  T10.T9.…

		int p = psw_NZ(o);
		if(l == 0x8000){p |= CPU.PSW_C;} //[T14 == 1] C = 1
		if(i * 2 != o){p |= CPU.PSW_V;} //[i <<< 1 != o] V = 1

		cpu.getRegister(CPU.REG_PSW).setPreValue(p);
		cpu.getSBus().setValue(o);
	}
}
