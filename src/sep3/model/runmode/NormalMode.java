package sep3.model.runmode;

import sep3.*;
import sep3.model.cycle.*;

// CPUの通常走行モード：　HLT, ILLのどちらか、あるいはACKランプが点灯して入力待ちになるまで走る
public class NormalMode extends RunMode {
	public NormalMode() { super.setID(RunModeFactory.RM_NORMAL); }
	public void run(Model model) {
		StateFactory sf = model.getCPU().getStateFactory();
		State hlt = sf.getState(StateFactory.SC_HLT);
		State ill = sf.getState(StateFactory.SC_ILL);
		//State ff2 = sf.getState(StateFactory.SC_FF2);
		State s = model.getCPU().getCurrentState();
		do {
			model.clock();			// クロックを打って
			s = s.clockstep(model);	// ゲートを開ける
		} while ((s != hlt) & (s != ill) & (!model.getMemory().getAckLamp().isOn()));
		// 入力待ちのときは、一旦終了してACKボタンが押されるのを待つ
		model.getCPU().setCurrentState(s);
	}
}
