package sep3.model;

import java.util.Observable;
import sep3.controller.*;
import sep3.view.LEDPosition;

// SEP-3 のバス
public class Bus extends Observable implements LEDDisplayable {
	public static final boolean NeedSelector = true;
	private LEDPosition	myPosition;						// LEDアレイ上の表示位置
	private int			value;							// このバスに現在流れている値
	private Selector	selector;						// どのレジスタの値を選択的に流すか決める回路

	// 各種getterとsetter
	public int			getValue()					{ return value; }
	public void			setValue(int v)				{ value = v; setChanged(); notifyObservers(); } // model が変わったことを通知
	public LEDPosition	getPosition()				{ return myPosition; }
	public void			setPosition(LEDPosition p)	{ myPosition = p; }
	public Selector		getSelector()				{ return selector; }
	public void			setSelector(Selector s)		{ selector = s; }

	public Bus(boolean in, boolean out) {
		// このバスへデータを流すためのセレクタ（in）と、バスから渡すためのセレクタ（out）を登録する
		if (in & out) { selector = new Selector(this, this); }
		else if (in)  { selector = new Selector(this, null); }
		else if (out) { selector = new Selector(null, this); }
	}
	public Bus(CPU cpu, boolean in, boolean out) {
		this(in, out);
		selector.setCPU(cpu);
	}
}
