package sep3.model.cycle;

import sep3.Model;
import sep3.model.CPU;
import sep3.model.Memory;
import sep3.model.operation.InstructionSet;

public class FromState1 extends State{

	FromState1(StateFactory sf, int s) { super(sf, s); }

	@Override
	public State clockstep(Model model) {
		//System.out.println("%% FF1 %%");
		CPU cpu = model.getCPU();
		super.prolog(cpu);

		// Bus(A,B,S) Reset
		cpu.getABus().setValue(0x0000);
		cpu.getBBus().setValue(0x0000);
		cpu.getSBus().setValue(0x0000);

		// MAR -> メモリ読み出し -> MDR	// 命令読み出し
		model.getAddrBusSelector().selectFrom(CPU.REG_MAR);
		model.getMemory().access(Memory.MEM_RD);
		model.getDataBusSelector().selectTo(CPU.REG_MDR);

		// [IP:]getFromRegister のカウントアップ
		if(cpu.getDecoder().getFromMode() == sep3.model.Decoder.MODE_IP){
			cpu.getABusSelector().selectFrom(cpu.getDecoder().getFromRegister());
			cpu.getALU().operate(InstructionSet.OP_INC);
			cpu.getSBusSelector().selectTo(cpu.getDecoder().getFromRegister());
		}

		return getStateFactory().getState(StateFactory.SC_FF2);
	}
}
