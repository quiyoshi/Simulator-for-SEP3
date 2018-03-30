package sep3.controller;

import sep3.view.LEDPosition;

// バスLEDアレイへの表示用のインタフェース（バス、レジスタが実装してないとダメ）
public interface LEDDisplayable {
	public int getValue();					// バスやレジスタが現在保持している値
	public LEDPosition getPosition();		// LEDアレイのどの場所が、こいつの表示位置か
	//public void setPosition(LEDPosition p);
}
