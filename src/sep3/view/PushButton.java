package sep3.view;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

// 押しボタン
@SuppressWarnings("serial")
public class PushButton extends JButton {
	private static Dimension d = null;

	public PushButton(String s) {
		setFont(new Font("Arial", Font.PLAIN, 8));
		if (d == null) { d = new Dimension(60, 30); }
		setPreferredSize(d);
		setText(s);
	}
}
