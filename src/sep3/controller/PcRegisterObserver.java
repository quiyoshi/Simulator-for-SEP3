package sep3.controller;

import java.util.Observable;
import java.util.Observer;
import sep3.*;
import sep3.model.*;
import sep3.view.LEDPosition;

public class PcRegisterObserver implements Observer {
	private Model model;
	private View  view;

	public PcRegisterObserver(Model m, View v) { model = m; view = v; }
	public void update(Observable o, Object arg) {
		//System.out.println("enter R7 Observer");
		// モデル上でPC(R7)が変更されたとき、DISPスイッチ（スイッチ#10）がOFFのときに限って
		// 7セグメントLEDとLEDアレイに表示する
		Register pc = (Register) o;
		LEDPosition p = pc.getPosition();
		int v = pc.getValue();
		if (!model.getDispSW().isOn()) {
			view.getLED7segment().setValue(v);
		}
		view.getLED8x8x2()[p.getWhich()].showValue(p.getLine(), v);
	}
}
