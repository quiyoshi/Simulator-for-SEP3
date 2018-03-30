package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;

public class PowerSwitchListener implements ActionListener {
	private Model model;
	private View  view;

	public PowerSwitchListener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		//System.out.println("enter actionlistner of PowerSwitch");
		// ビュー上で電源スイッチが切り替わったとき、モデル上でも切り替える
		model.getPowerSW().toggle();
		if (model.getPowerSW().isOn()) {
			// ONになったら、モデルも初期化
			model.powerOn();
			//String s = model.getMessage();
			//view.printMessage(s);
		} else {
			// OFFになったら、ビュー上の7セグメントLEDも消す。そしてモデルでも消灯する
			// （7セグメントLEDだけは、モデルの変更がビューに通知されても消えず、値が表示されてしまう）
			// 【ビューからモデルが見えない設計にしてあるので、sep3.view.LED7segmentから電源スイッチを確認する方法がない】
			model.powerOff();
			view.getLED7segment().powerOff();
		}
	}
}
