package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;
import sep3.model.runmode.RunModeFactory;

public class ToggleSwitch12Listener implements ActionListener {
	private Model model;
	@SuppressWarnings("unused")
	private View  view;

	public ToggleSwitch12Listener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		//System.out.println("enter actionlistener of clock step");
		// トグルスイッチ#12が切り替えられたら、#13も見て、どの走行モードに切り替えるか決めて、替える
		model.getRun2SW().toggle();
		if (model.getRun1SW().isOn() & model.getRun2SW().isOn()) {
			model.setRunMode(RunModeFactory.RM_CLOCK);
		} else if (model.getRun1SW().isOn() & !model.getRun2SW().isOn()){
			model.setRunMode(RunModeFactory.RM_INST);
		} else if (!model.getRun1SW().isOn()){
			model.setRunMode(RunModeFactory.RM_NORMAL);
		}
	}
}
