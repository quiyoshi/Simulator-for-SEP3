package sep3.controller;

import java.util.Observable;
import java.util.Observer;
import sep3.*;
import sep3.model.*;
import sep3.view.LEDPosition;

public class BusObserver implements Observer {
	@SuppressWarnings("unused")
	private Model model;
	private View  view;

	public BusObserver(Model m, View v) { model = m; view = v; }
	public void update(Observable o, Object arg) {
		// バス上を流れるデータの値が変わったら、LEDアレイの該当部分に新しいデータ（値）を表示する
		Bus b = (Bus) o;
		LEDPosition p = b.getPosition();									// LED上の表示位置を特定し
		view.getLED8x8x2()[p.getWhich()].showValue(p.getLine(), b.getValue());	// そこに書き込む
	}
}
