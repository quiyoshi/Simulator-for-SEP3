package sep3.model.cycle;

import sep3.Model;
import sep3.model.CPU;
import sep3.model.Memory;
import sep3.model.operation.InstructionSet;

public class ExectState1 extends State {

	ExectState1(StateFactory sf, int s) { super(sf, s); }

	@Override
	public State clockstep(Model model) {
		//System.out.println("%% EX1 %%");
		CPU cpu = model.getCPU();
		super.prolog(cpu);

		// Bus(A,B,S) Reset
		cpu.getABus().setValue(0x0000);
		cpu.getBBus().setValue(0x0000);
		cpu.getSBus().setValue(0x0000);

		if(cpu.getDecoder().getOpCode() == InstructionSet.OP_CMP || cpu.getDecoder().getOpCode() == InstructionSet.OP_BIT){
			return getStateFactory().getState(StateFactory.SC_IF0);
		}

		// MAR,MDR -> メモリ書き出し // [~D] Toオペランド書き出し
		model.getAddrBusSelector().selectFrom(CPU.REG_MAR);
		model.getDataBusSelector().selectFrom(CPU.REG_MDR);
		model.getMemory().access(Memory.MEM_WR);

		// [JSR,SVC,RJS] サブルーチン処理
		switch(cpu.getDecoder().getOpCode()){
		case InstructionSet.OP_JSR: case InstructionSet.OP_SVC:
			cpu.getBBusSelector().selectFrom(CPU.REG_B0);
			cpu.getALU().operate(InstructionSet.OP_THRB);
			cpu.getSBusSelector().selectTo(CPU.REG_R7);
			break;
		case InstructionSet.OP_RJS:
			cpu.getABusSelector().selectFrom(CPU.REG_R7);
			cpu.getBBusSelector().selectFrom(CPU.REG_B0);
			cpu.getALU().operate(InstructionSet.OP_ADD2);
			cpu.getSBusSelector().selectTo(CPU.REG_R7);
			break;
		}

		return getStateFactory().getState(StateFactory.SC_IF0);
	}
}
