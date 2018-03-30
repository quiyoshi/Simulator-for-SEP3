package sep3.view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

// 7セグメントLED
@SuppressWarnings("serial")
public class LED7segment extends JLabel {
	private int value;

	public LED7segment() {
		// 現物に似せて作るのは大変なので、単なるテキストで誤魔化す
		setFont(new Font("Courier New", Font.BOLD, 48));
		value = 0;
		setBorder(new LineBorder(Color.BLACK, 1, true));
		powerOff();
	}
	public void setValue(int v)	{ value = v; setText(String.format("%1$04x", (value & 0xFFFF)).toUpperCase()); }
	public void powerOff()		{ setText("    "); }	// 電源offのときは、何も表示しない
}
