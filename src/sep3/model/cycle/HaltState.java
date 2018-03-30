package sep3.model.cycle;

import sep3.Model;

public class HaltState extends State {

	HaltState(StateFactory sf, int s) { super(sf, s); }

	@Override
	public State clockstep(Model model) {
		return this;
	}

}
