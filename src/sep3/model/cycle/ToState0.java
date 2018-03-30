package sep3.model.cycle;

import sep3.Model;
import sep3.model.CPU;
import sep3.model.operation.InstructionSet;

public class ToState0 extends State{

	ToState0(StateFactory sf, int s) { super(sf, s); }

	@Override
	public State clockstep(Model model) {
		//System.out.println("%% TF0 %%");
		CPU cpu = model.getCPU();
		super.prolog(cpu);

		// Bus(A,B,S) Reset
		cpu.getABus().setValue(0x0000);
		cpu.getBBus().setValue(0x0000);
		cpu.getSBus().setValue(0x0000);

		// MDR -> MAR, MDR	// 読み出しデータをMAR,MDRへ
		cpu.getABusSelector().selectFrom(cpu.getDecoder().getToRegister());
		cpu.getALU().operate(InstructionSet.OP_THRA);
		cpu.getSBusSelector().selectTo(CPU.REG_MAR, CPU.REG_MDR);

		if(cpu.getDecoder().getToMode() == sep3.model.Decoder.MODE_D){
		  return getStateFactory().getState(StateFactory.SC_EX0);
		} else {
		  return getStateFactory().getState(StateFactory.SC_TF1);
		}
	}
}
