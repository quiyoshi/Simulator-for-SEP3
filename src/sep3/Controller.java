package sep3;


import sep3.model.*;
import sep3.view.*;
import sep3.controller.*;

public class Controller {

	public Controller(Model model, View view) {
		// メニューの処理
		view.getFileMenu().addActionListener(new FileMenuListener(model, view));
		view.getExitMenu().addActionListener(new ExitMenuListener(model, view));

		// LEDアレイ内の表示位置をセットする
		for (int i = CPU.REG_R0; i <= CPU.REG_R7; ++i) {
			model.getCPU().getRegister(i).setPosition(new LEDPosition(LEDPosition.LEFT, i));
		}
		model.getCPU().getRegister(CPU.REG_B0).setPosition(new LEDPosition(LEDPosition.RIGHT, 0));
		model.getCPU().getRegister(CPU.REG_MAR).setPosition(new LEDPosition(LEDPosition.RIGHT, 1));
		model.getCPU().getRegister(CPU.REG_MDR).setPosition(new LEDPosition(LEDPosition.RIGHT, 2));
		model.getDataBus().setPosition(new LEDPosition(LEDPosition.RIGHT, 3));
		model.getCPU().getABus().setPosition(new LEDPosition(LEDPosition.RIGHT, 4));
		model.getCPU().getBBus().setPosition(new LEDPosition(LEDPosition.RIGHT, 5));
		model.getCPU().getSBus().setPosition(new LEDPosition(LEDPosition.RIGHT, 6));
		model.getCPU().getRegister(CPU.REG_SC).setPosition(new LEDPosition(LEDPosition.RIGHT, 7));

	// ビューの変更をモデルに伝える
		// 電源スイッチ
		view.getPowerSwitch().addActionListener(new PowerSwitchListener(model, view));

		// トグルスイッチ10： PC/ISR切り替え
		view.getSw10().addActionListener(new ToggleSwitch10Listener(model, view));

		// トグルスイッチ11： STOP
		//   リスナーなし

		// トグルスイッチ12： RUN2 クロックステップ
		view.getSw12().addActionListener(new ToggleSwitch12Listener(model, view));

		// トグルスイッチ13： RUN1 命令ステップ
		view.getSw13().addActionListener(new ToggleSwitch13Listener(model, view));

		// ack ボタン
		view.getAckButton().addActionListener(new AckButtonListener(model, view));

		// reset ボタン
		view.getResetButton().addActionListener(new ResetButtonListener(model, view));

		// start ボタン
		view.getStartButton().addActionListener(new StartButtonListener(model, view));

		// データ入力スイッチ
		//   リスナーなし

	// モデルの変更をビューに伝える
		// ISR
		model.getCPU().getRegister(CPU.REG_ISR).addObserver(new ISRegisterObserver(model, view));

		// R0 から R6
		for (int i = 0; i < CPU.REG_R7; ++i) {
			model.getCPU().getRegister(i).addObserver(new RegisterObserver(model, view));
		}

		// R7 (PC)
		model.getCPU().getRegister(CPU.REG_PC).addObserver(new PcRegisterObserver(model, view));

		// B0, MAR, MDR
		model.getCPU().getRegister(CPU.REG_B0).addObserver(new RegisterObserver(model, view));
		model.getCPU().getRegister(CPU.REG_MAR).addObserver(new RegisterObserver(model, view));
		model.getCPU().getRegister(CPU.REG_MDR).addObserver(new RegisterObserver(model, view));

		// バス
		model.getDataBus().addObserver(new BusObserver(model, view));
		model.getCPU().getABus().addObserver(new BusObserver(model, view));
		model.getCPU().getBBus().addObserver(new BusObserver(model, view));
		model.getCPU().getSBus().addObserver(new BusObserver(model, view));

		// ステータスカウンタ
		model.getCPU().getRegister(CPU.REG_SC).addObserver(new RegisterObserver(model, view));

		// データ出力LED
		model.getMemory().getIOValue().addObserver(new IOOutputObserver(model, view));

		// HLT LED
		model.getCPU().getHaltLamp().addObserver(new HLTLampObserver(model, view));

		// ILL LED
		model.getCPU().getIllLamp().addObserver(new ILLLampObserver(model, view));

 		// ACK LED
		model.getMemory().getAckLamp().addObserver(new AckLampObserver(model, view));
	}
}
