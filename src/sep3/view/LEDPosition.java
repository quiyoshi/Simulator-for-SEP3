package sep3.view;

// LEDアレイのどの位置に表示するかを保持するデータ構造
public class LEDPosition {
	public static final int LEFT	= 0;
	public static final int RIGHT	= 1;
	private int which;						// 左のLEDアレイか、右のか？
	private int line;						// アレイの何行目か？

	public LEDPosition(int which, int line) { this.which = which; this.line = line; }
	public int getWhich() { return which; }
	public int getLine()  { return line; }
}
