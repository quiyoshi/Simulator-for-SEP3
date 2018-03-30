package sep3.model.runmode;

import sep3.*;
import sep3.model.cycle.*;

// CPUのクロックステップ走行モード：　1クロック進めて止まる
public class ClockStepMode extends RunMode {
	public ClockStepMode() { super.setID(RunModeFactory.RM_CLOCK); }
	public void run(Model model) {
		State s = model.getCPU().getCurrentState();
		model.clock();										// クロックを打って
		model.getCPU().setCurrentState(s.clockstep(model));	// ゲートを開ける
	}
}
