package sep3.view;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.*;

// LEDのひとつ分
@SuppressWarnings("serial")
public class LED extends JLabel {
	static final String onFile  = "sep3/view/ledon.png";	// 点灯時リソースファイル
	static final String offFile = "sep3/view/ledoff.png";	// 消灯時リソースファイル

	public LED(boolean b) {
		ImageIcon onIcon = null, offIcon = null;
		URL url = this.getClass().getClassLoader().getResource(onFile);
		if (url != null) {
			onIcon = new ImageIcon(url);
		}
		setIcon(onIcon);
		url = getClass().getClassLoader().getResource(offFile);
		if (url != null) {
			offIcon = new ImageIcon(url);
		}
		setDisabledIcon(offIcon);
		setPreferredSize(new Dimension(onIcon.getIconWidth(), onIcon.getIconHeight()));
		setEnabled(b);
	}

	public LED()  {
		this(false);	// 最初に作るときはoff状態
	}

	public void on()  { setEnabled(true); }
	public void off() { setEnabled(false); }
}
