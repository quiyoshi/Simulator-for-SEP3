package sep3;
import java.awt.*;
import javax.swing.*;
import sep3.view.*;

@SuppressWarnings("serial")
public class View extends JFrame {
	private Sep3Menu	menu;
	private Sep3Upper	upper;
	private Sep3Lower	lower;

	// 各種 getter
	public JMenuItem		getFileMenu()			{ return menu.getFileMenu(); }
	public JMenuItem		getExitMenu()			{ return menu.getExitMenu(); }
	public LED7segment		getLED7segment()		{ return upper.getLED7segment(); }
	public LED8x8x2[]		getLED8x8x2()			{ return upper.getLED8x8x2(); }
	public PowerSwitch		getPowerSwitch()		{ return lower.getPowerSwitch(); }
	public PushButton		getStartButton()		{ return lower.getStartButton(); }
	public PushButton		getResetButton()		{ return lower.getResetButton(); }
	public PushButton		getAckButton()			{ return lower.getAckButton(); }
	public LED				getIllLED()				{ return lower.getIllLED(); }
	public LED				getHltLED()				{ return lower.getHltLED(); }
	public LED				getAckLED()				{ return lower.getAckLED(); }
	public ToggleSwitch		getSw10()				{ return lower.getSw10(); }
	public ToggleSwitch		getSw11()				{ return lower.getSw11(); }
	public ToggleSwitch		getSw12()				{ return lower.getSw12(); }
	public ToggleSwitch		getSw13()				{ return lower.getSw13(); }
	public DataOutputLED	getDataOutputLED()		{ return lower.getDataOutputLED(); }
	public DataInputSwitch	getDataInputSwitch()	{ return lower.getDataInputSwitch(); }

	// only for test
	JTextArea jt;
	public void printMessage(String s) { jt.append(s); }

	public View() {
		setLayout(new BorderLayout());
		// only for test
		jt = new JTextArea(); add("West", jt);

		menu  = new Sep3Menu();  add("North", menu);		// メニューバー
		upper = new Sep3Upper(); add("Center", upper);		// 7セグメントLEDとLEDアレイ
		lower = new Sep3Lower(); add("South", lower);		// LED類とスイッチ

		// ウィンドウ消去の時の処理
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// アプリケーション名
		setTitle("SEP-3 Simulator");
		// 表示
		setSize(750,300);
		setVisible(true);
	}
}

@SuppressWarnings("serial")
// 上段：　メニューバー、メニュー生成
class Sep3Menu extends JMenuBar {
	JMenuItem jmiFile, jmiExit;

	public JMenuItem getFileMenu()	{ return jmiFile; }
	public JMenuItem getExitMenu()	{ return jmiExit; }
	Sep3Menu() {
		JMenu jm = new JMenu("File");
		jm.setMnemonic('F');
			jmiFile = new JMenuItem("Open");
			jmiFile.setMnemonic('O');
			jm.add(jmiFile);
			jmiExit = new JMenuItem("Exit");
			jmiExit.setMnemonic('E');
			jm.add(jmiExit);
		add(jm);
	}
}

@SuppressWarnings("serial")
// 中段：　7セグメントLEDとLEDアレイ
class Sep3Upper extends JPanel {
	private LED7segment	seg;
	private LED8x8x2[]	ledArray;

	public LED7segment	getLED7segment()	{ return seg; }
	public LED8x8x2[]	getLED8x8x2()		{ return ledArray; }
	Sep3Upper() {
		Font font = new Font("Arial", Font.PLAIN, 11);
		setLayout(new FlowLayout());
			// 7セグメントLED ラベル付き
			JPanel p = new JPanel();
			p.setLayout(new BorderLayout());
				JLabel pcisr = new JLabel("PC/ ISR");
				pcisr.setFont(font);
			p.add("North", pcisr);
				seg = new LED7segment();
			p.add("South", seg);
		add(p);
			// 間を空けるためのJLabel
			JLabel l = new JLabel("    ");
		add(l);
			// LEDアレイ
			JTextArea regname = new JTextArea("R0\nR1\nR2\nR3\nR4\nR5\nR6\nR7");
			regname.setFont(font);
			regname.setBackground(this.getBackground());
		add(regname);
			ledArray = new LED8x8x2[2];
			ledArray[0] = new LED8x8x2();
			ledArray[1] = new LED8x8x2();
		add(ledArray[0]);
		add(ledArray[1]);
			JTextArea busname = new JTextArea("B0\nMAR\nMDR\nMbus\nAbus\nBbus\nSbus\nSC");
			busname.setFont(font);
			busname.setBackground(this.getBackground());
		add(busname);
	}
}

@SuppressWarnings("serial")
// 下段：　スイッチ、ボタン類
class Sep3Lower extends JPanel {
	private Sep3LowerCenter center;
	private PowerSwitch	powerSW;
	private PushButton	start, reset, ack;

	// 各種getter
	public PowerSwitch		getPowerSwitch()		{ return powerSW; }
	public PushButton		getStartButton()		{ return start; }
	public PushButton		getResetButton()		{ return reset; }
	public PushButton		getAckButton()			{ return ack; }
	public LED				getIllLED()				{ return center.getIllLED(); }
	public LED				getHltLED()				{ return center.getHltLED(); }
	public LED				getAckLED()				{ return center.getAckLED(); }
	public ToggleSwitch		getSw10()				{ return center.getSw10(); }
	public ToggleSwitch		getSw11()				{ return center.getSw11(); }
	public ToggleSwitch		getSw12()				{ return center.getSw12(); }
	public ToggleSwitch		getSw13()				{ return center.getSw13(); }
	public DataOutputLED	getDataOutputLED()		{ return center.getDataOutputLED(); }
	public DataInputSwitch	getDataInputSwitch()	{ return center.getDataInputSwitch(); }
	Sep3Lower() {
		setLayout(new FlowLayout());
			// 左：　電源スイッチ
			powerSW = new PowerSwitch();
		add(powerSW);
			// 中央：　LEDとトグルスイッチ
			center = new Sep3LowerCenter();
		add(center);
			// 右：　ボタン
			JPanel lor = new JPanel();
			lor.setLayout(new GridLayout(2,4));
				start = new PushButton("START");
				reset = new PushButton("RESET");
				ack   = new PushButton("ACK");
			lor.add(new PushButton(""));
			lor.add(new PushButton(""));
			lor.add(new PushButton(""));
			lor.add(ack);
			lor.add(new PushButton(""));
			lor.add(new PushButton(""));
			lor.add(reset);
			lor.add(start);
		add(lor);
	}
}

@SuppressWarnings("serial")
// 下段中央部
class Sep3LowerCenter extends JPanel {
	private LED itfLED, illLED, hltLED, ackLED;
	private ToggleSwitch sw10, sw11, sw12, sw13;
	private DataOutputLED out;
	private DataInputSwitch in;

	// 各種getter
	public LED	getItlLED()		{ return itfLED; }
	public LED	getIllLED()		{ return illLED; }
	public LED	getHltLED()		{ return hltLED; }
	public LED	getAckLED()		{ return ackLED; }
	public ToggleSwitch		getSw10()				{ return sw10; }
	public ToggleSwitch		getSw11()				{ return sw11; }
	public ToggleSwitch		getSw12()				{ return sw12; }
	public ToggleSwitch		getSw13()				{ return sw13; }
	public DataOutputLED	getDataOutputLED()		{ return out; }
	public DataInputSwitch	getDataInputSwitch()	{ return in; }
	Sep3LowerCenter() {
		setLayout(new GridLayout(2,1));
			// 上にラベルとLED
			JPanel lomu = new JPanel();
			//lomu.setLayout(new GridLayout(2,1));
			lomu.setLayout(new BorderLayout());
				// 上段にラベル
				Font font = new Font("Arial", Font.PLAIN, 8);
				JLabel label = new JLabel("          ILL HLT ACK");
				label.setFont(font);
			lomu.add("Center", label);
				// 下段にLED
				JPanel lomud = new JPanel();
				//lomud.setLayout(new BorderLayout());
				lomud.setLayout(new FlowLayout());
					// 左に単独LEDが4つ
					JPanel lomudl = new JPanel();
					lomudl.setLayout(new BoxLayout(lomudl, BoxLayout.X_AXIS));
						itfLED = new LED(); lomudl.add(itfLED);
						illLED = new LED(); lomudl.add(illLED);
						hltLED = new LED(); lomudl.add(hltLED);
						ackLED = new LED(); lomudl.add(ackLED);
				//lomud.add("West", lomudl);
				lomud.add(lomudl);
					// 右にデータ出力LED
					out = new DataOutputLED();
				//lomud.add("East", out);
				lomud.add(out);
			lomu.add("South", lomud);
		add(lomu);
			// 下にトグルスイッチ
			JPanel lomd = new JPanel();
			lomd.setLayout(new FlowLayout());
				// 左に単独トグルスイッチが4つ
					JPanel lomdl = new JPanel();
					lomdl.setLayout(new BoxLayout(lomdl, BoxLayout.X_AXIS));
					//lomdl.setLayout(new GridLayout(1,4));
						sw10 = new ToggleSwitch(); lomdl.add(sw10);
						sw11 = new ToggleSwitch(); lomdl.add(sw11);
						sw12 = new ToggleSwitch(); lomdl.add(sw12);
						sw13 = new ToggleSwitch(); lomdl.add(sw13);
			lomd.add("West", lomdl);
				// 右にデータ入力スイッチ
				in = new DataInputSwitch();
			lomd.add("East", in);
		add(lomd);
	}
}
