package sep3.model.cycle;

import sep3.Model;
import sep3.model.CPU;
import sep3.model.operation.InstructionSet;

public class FromState0 extends State{

	FromState0(StateFactory sf, int s) { super(sf, s); }

	@Override
	public State clockstep(Model model) {
		//System.out.println("%% FF0 %%");
		CPU cpu = model.getCPU();
		super.prolog(cpu);

		// Bus(A,B,S) Reset
		cpu.getABus().setValue(0x0000);
		cpu.getBBus().setValue(0x0000);
		cpu.getSBus().setValue(0x0000);

		if(cpu.getDecoder().getFromMode() == sep3.model.Decoder.MODE_MI){
			// FromRegister  ->  FromRegister, MAR, MDR	// 読み出しデータをデクリメントして FromRegister, MAR, MDRへ
			cpu.getABusSelector().selectFrom(cpu.getDecoder().getFromRegister());
			cpu.getALU().operate(InstructionSet.OP_DEC);
			cpu.getSBusSelector().selectTo(cpu.getDecoder().getFromRegister(),CPU.REG_MAR, CPU.REG_MDR);
		} else {
			// FromRegister -> MAR, MDR	// 読み出しデータをMAR, MDRへ
			cpu.getABusSelector().selectFrom(cpu.getDecoder().getFromRegister());
			cpu.getALU().operate(InstructionSet.OP_THRA);
			cpu.getSBusSelector().selectTo(CPU.REG_MAR, CPU.REG_MDR);
		}

		if(cpu.getDecoder().getFromMode() == sep3.model.Decoder.MODE_D){
            return getStateFactory().getState(StateFactory.SC_FF2);
		} else {
			return getStateFactory().getState(StateFactory.SC_FF1);
		}
	}

}
