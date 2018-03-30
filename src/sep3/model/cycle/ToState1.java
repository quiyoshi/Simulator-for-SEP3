package sep3.model.cycle;

import sep3.Model;
import sep3.model.CPU;
import sep3.model.Memory;
import sep3.model.operation.InstructionSet;

public class ToState1 extends State{

	ToState1(StateFactory sf, int s) { super(sf, s); }

	@Override
	public State clockstep(Model model) {
		//System.out.println("%% TF1 %%");
		CPU cpu = model.getCPU();
		super.prolog(cpu);

		// Bus(A,B,S) Reset
		cpu.getABus().setValue(0x0000);
		cpu.getBBus().setValue(0x0000);
		cpu.getSBus().setValue(0x0000);

		// MAR -> メモリ読み出し -> MDR	// データ読み出し
		model.getAddrBusSelector().selectFrom(CPU.REG_MAR);
		switch(cpu.getDecoder().getOpCode()){
		case InstructionSet.OP_JSR:
		case InstructionSet.OP_SVC:
		case InstructionSet.OP_RJS:
			cpu.getABusSelector().selectFrom(CPU.REG_R7);
			cpu.getALU().operate(InstructionSet.OP_THRA);
			cpu.getSBusSelector().selectTo(CPU.REG_MDR);
			break;
		case InstructionSet.OP_MOV:
			model.getMemory().movToAccess();
			model.getDataBusSelector().selectTo(CPU.REG_MDR);
			break;
		default:
			model.getMemory().access(Memory.MEM_RD);
			model.getDataBusSelector().selectTo(CPU.REG_MDR);
			break;
		}

		// [:IP] Toオペランドのカウントアップ
		if(cpu.getDecoder().getToMode() == sep3.model.Decoder.MODE_IP){
			cpu.getABusSelector().selectFrom(cpu.getDecoder().getToRegister());
			cpu.getALU().operate(InstructionSet.OP_INC);
			cpu.getSBusSelector().selectTo(cpu.getDecoder().getToRegister());
		}

		return getStateFactory().getState(StateFactory.SC_EX0);
	}
}
