package sep3.model.cycle;

import sep3.Model;
import sep3.model.CPU;
import sep3.model.operation.InstructionSet;

public class ExectState0 extends State {

	ExectState0(StateFactory sf, int s) { super(sf, s); }

	@Override
	public State clockstep(Model model) {
		//System.out.println("%% EX0 %%");
		CPU cpu = model.getCPU();
		super.prolog(cpu);

		// Bus(A,B,S) Reset
		cpu.getABus().setValue(0x0000);
		cpu.getBBus().setValue(0x0000);
		cpu.getSBus().setValue(0x0000);

		// 命令実行
		cpu.getABusSelector().selectFrom(CPU.REG_MDR);
		cpu.getBBusSelector().selectFrom(CPU.REG_B0);
		cpu.getALU().operate(cpu.getDecoder().getOpCode());


		//Toオペランド書き込み動作
		if(cpu.getDecoder().getToMode() == sep3.model.Decoder.MODE_D){
			switch(cpu.getDecoder().getOpCode()){
			case InstructionSet.OP_BRN: case InstructionSet.OP_RBN:
				if((cpu.getRegister(CPU.REG_PSW).getValue() & CPU.PSW_N) == CPU.PSW_N){
					cpu.getSBusSelector().selectTo(CPU.REG_R7);
				}
				break;
			case InstructionSet.OP_BRZ: case InstructionSet.OP_RBZ:
				if((cpu.getRegister(CPU.REG_PSW).getValue() & CPU.PSW_Z) == CPU.PSW_Z){
					cpu.getSBusSelector().selectTo(CPU.REG_R7);
				}
				break;
			case InstructionSet.OP_BRV: case InstructionSet.OP_RBV:
				if((cpu.getRegister(CPU.REG_PSW).getValue() & CPU.PSW_V) == CPU.PSW_V){
					cpu.getSBusSelector().selectTo(CPU.REG_R7);
				}
				break;
			case InstructionSet.OP_BRC: case InstructionSet.OP_RBC:
				if((cpu.getRegister(CPU.REG_PSW).getValue() & CPU.PSW_C) == CPU.PSW_C){
					cpu.getSBusSelector().selectTo(CPU.REG_R7);
				}
				break;
			case InstructionSet.OP_CMP: case InstructionSet.OP_BIT:
				break;
			case InstructionSet.OP_HLT:
				cpu.getSBusSelector().selectTo(CPU.REG_R0);
				return getStateFactory().getState(StateFactory.SC_HLT);
			case InstructionSet.OP_ILL:
				return getStateFactory().getState(StateFactory.SC_ILL);
			default:
				cpu.getSBusSelector().selectTo(cpu.getDecoder().getToRegister());
				break;
			}

			return getStateFactory().getState(StateFactory.SC_IF0);
		} else {
			cpu.getSBusSelector().selectTo(CPU.REG_MDR);

			return getStateFactory().getState(StateFactory.SC_EX1);
		}
	}

}
