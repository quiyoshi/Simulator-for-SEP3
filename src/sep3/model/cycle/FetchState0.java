package sep3.model.cycle;

import sep3.Model;
import sep3.model.CPU;
import sep3.model.operation.InstructionSet;

public class FetchState0 extends State {

	FetchState0(StateFactory sf, int s) { super(sf, s); }

	@Override
	public State clockstep(Model model) {
		//System.out.println("%% IF0 %%");
		CPU cpu = model.getCPU();
		super.prolog(cpu);

		// Bus(A,B,S) Reset
		cpu.getABus().setValue(0x0000);
		cpu.getBBus().setValue(0x0000);
		cpu.getSBus().setValue(0x0000);

		// PC -> MAR, MDR	// 命令読み出し番地をMAR, MDRへ
		cpu.getABusSelector().selectFrom(CPU.REG_PC);
		cpu.getALU().operate(InstructionSet.OP_THRA);
		cpu.getSBusSelector().selectTo(CPU.REG_MAR, CPU.REG_MDR);

		return getStateFactory().getState(StateFactory.SC_IF1);
	}

}
