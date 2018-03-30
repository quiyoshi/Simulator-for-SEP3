package sep3.model.cycle;

import sep3.Model;
import sep3.model.CPU;
import sep3.model.operation.InstructionSet;

public class FromState2 extends State{

	FromState2(StateFactory sf, int s) { super(sf, s); }

	@Override
	public State clockstep(Model model) {
		//System.out.println("%% FF2 %%");
		CPU cpu = model.getCPU();
		super.prolog(cpu);

		// Bus(A,B,S) Reset
		cpu.getABus().setValue(0x0000);
		cpu.getBBus().setValue(0x0000);
		cpu.getSBus().setValue(0x0000);

		// MDR -> B0	// 命令読み出しデータをB0へ
		cpu.getABusSelector().selectFrom(CPU.REG_MDR);
		cpu.getALU().operate(InstructionSet.OP_THRA);
		cpu.getSBusSelector().selectTo(CPU.REG_B0);

		return getStateFactory().getState(StateFactory.SC_TF0);
	}
}
