package sep3.controller;

import java.util.Observable;
import java.util.Observer;
import sep3.*;
import sep3.model.CPU;

public class ISRegisterObserver implements Observer {
	private Model model;
	private View  view;

	public ISRegisterObserver(Model m, View v) { model = m; view = v; }
	public void update(Observable o, Object arg) {
		//System.out.println("enter ISR Observer");
		// モデル上でISRが変更されたとき、DISPスイッチ（スイッチ#10）がONのときに限って7セグメントLEDに表示する
		if (model.getDispSW().isOn()) {
			view.getLED7segment().setValue(model.getCPU().getRegister(CPU.REG_ISR).getValue());
		}
	}
}
