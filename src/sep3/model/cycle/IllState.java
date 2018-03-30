package sep3.model.cycle;

import sep3.*;

public class IllState extends State {

	IllState(StateFactory sf, int s) { super(sf, s); }

	@Override
	public State clockstep(Model model) {
		return this;
	}

}
