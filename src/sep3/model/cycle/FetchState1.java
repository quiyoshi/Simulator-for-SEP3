package sep3.model.cycle;

import sep3.Model;
import sep3.model.CPU;
import sep3.model.Memory;
import sep3.model.operation.InstructionSet;

public class FetchState1 extends State {

	FetchState1(StateFactory sf, int s) { super(sf, s); }

	@Override
	public State clockstep(Model model) {
		//System.out.println("%% IF1 %%");
		CPU cpu = model.getCPU();
		super.prolog(cpu);

		// Bus(A,B,S) Reset
		cpu.getABus().setValue(0x0000);
		cpu.getBBus().setValue(0x0000);
		cpu.getSBus().setValue(0x0000);

		// MAR -> メモリ読み出し -> ISR	// 命令読み出し
		model.getAddrBusSelector().selectFrom(CPU.REG_MAR);
		model.getMemory().access(Memory.MEM_RD);
		model.getDataBusSelector().selectTo(CPU.REG_ISR);

		// PC のカウントアップ
		cpu.getABusSelector().selectFrom(CPU.REG_PC);
		cpu.getALU().operate(InstructionSet.OP_INC);
		cpu.getSBusSelector().selectTo(CPU.REG_PC);

		return getStateFactory().getState(StateFactory.SC_FF0);
	}

}