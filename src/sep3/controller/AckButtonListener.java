package sep3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sep3.*;
import sep3.model.CPU;

public class AckButtonListener implements ActionListener {
	private Model model;
	private View  view;

	public AckButtonListener(Model m, View v) {
		model = m; view = v;
	}

	public void actionPerformed(ActionEvent e){
		// ランプを消し、入力スイッチの状態を読み、データバスに載せ、MDRに届くようにする
		model.getMemory().getAckLamp().off();
		int v = view.getDataInputSwitch().getData();
		model.getDataBus().setValue(v);
		model.getDataBusSelector().selectTo(CPU.REG_MDR);
		// Ack ボタンはクロックと別なので、一度クロックを入れてまた走る
		model.clock();
		model.run();
	}
}
