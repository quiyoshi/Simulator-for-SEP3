package sep3.model.runmode;

import sep3.*;
import sep3.model.cycle.*;

// CPUの命令ステップ走行モード：　命令読み出しの最初の状態、HLT、ILLのどれか、
// あるいはACKランプが点灯して入力待ちになるまで走るになるまで走る
public class InstructionStepMode extends RunMode {
	public InstructionStepMode() { super.setID(RunModeFactory.RM_INST); }
	public void run(Model model) {
		StateFactory sf = model.getCPU().getStateFactory();
		State hlt = sf.getState(StateFactory.SC_HLT);
		State ill = sf.getState(StateFactory.SC_ILL);
		State beg = sf.getState(StateFactory.SC_IF1);
		State s = model.getCPU().getCurrentState();
		do {
			model.clock();			// クロックを打って
			s = s.clockstep(model);	// ゲートを開ける
		} while ((s != hlt) & (s != ill) & (s != beg)& (!model.getMemory().getAckLamp().isOn()));
		// 入力待ちのときは、一旦終了してACKボタンが押されるのを待つ
		model.getCPU().setCurrentState(s);
	}
}