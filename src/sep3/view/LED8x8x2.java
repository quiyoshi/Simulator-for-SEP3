package sep3.view;

import java.awt.*;
import javax.swing.*;

// 8x8 の LEDアレイ
@SuppressWarnings("serial")
public class LED8x8x2 extends JPanel {
	private LED8x8 left, right;

	public LED8x8x2() {
		left  = new LED8x8();
		right = new LED8x8();
		add(left);
		add(right);
	}
	public void showValue(int r, int v) {
		left.showValue(r, (v & 0xFF00) >> 8);	// 上位8ビットを左のLEDアレイへ表示
		right.showValue(r, v & 0x00FF);			// 下位8ビットを右のLEDアレイへ表示
	}
}

@SuppressWarnings("serial")
class LED8x8 extends JPanel {
	private LED[][] array;

	LED8x8() {
		setLayout(new GridLayout(8,8));
		array = new LED[8][8];
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				array[i][j] = new LED();
				add(array[i][j]);
			}
		}
	}
	// 値vを、アレイのr行目に2進変換して表示
	public void showValue(int r, int v) {
		for (int j = 0; j < 8; ++j) {
			if ((v & 0x01) == 1) { array[r][7-j].on(); }
			else                 { array[r][7-j].off(); }
			v >>= 1;
		}
	}
}
