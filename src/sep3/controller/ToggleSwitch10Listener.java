package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;
import sep3.model.CPU;

public class ToggleSwitch10Listener implements ActionListener {
	private Model model;
	@SuppressWarnings("unused")
	private View  view;

	public ToggleSwitch10Listener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		//System.out.println("enter actionlistener of PC/ISR");
		// トグルスイッチ#10が切り替えられたら、7セグメントLEDの表示レジスタを替える
		model.getDispSW().toggle();
		if (model.getPowerSW().isOn()) {	// 電源が入っているときだけ、ビューを替える
			if (model.getDispSW().isOn()) {
				model.getCPU().getRegister(CPU.REG_ISR).touch();
			} else {
				model.getCPU().getRegister(CPU.REG_PC).touch();
			}
		}
	}
}
