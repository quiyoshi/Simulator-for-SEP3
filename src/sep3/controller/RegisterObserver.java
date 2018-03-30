package sep3.controller;

import java.util.Observable;
import java.util.Observer;
import sep3.*;
import sep3.model.*;
import sep3.view.LEDPosition;

public class RegisterObserver implements Observer {
	@SuppressWarnings("unused")
	private Model model;
	private View  view;

	public RegisterObserver(Model m, View v) { model = m; view = v; }
	public void update(Observable o, Object arg) {
		//System.out.println("enter Observer of Register #" + rn);
		// モデル上でレジスタ値が変更されたら、該当するLEDアレイの表示を変更する
		Register r = (Register) o;
		LEDPosition p = r.getPosition();
		int rn = p.getLine();
		view.getLED8x8x2()[p.getWhich()].showValue(rn, r.getValue());
	}
}
