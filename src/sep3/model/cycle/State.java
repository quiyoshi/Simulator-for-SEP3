package sep3.model.cycle;

import sep3.Model;
import sep3.model.CPU;

public abstract class State {
	private StateFactory sf;
	private int status;
	State(StateFactory f, int s) { sf = f; status = s; }
	public StateFactory getStateFactory() { return sf; }
	public abstract State clockstep(Model model);
	public void prolog(CPU cpu) {
		cpu.getRegister(CPU.REG_SC).setValue(status);
	}
}
