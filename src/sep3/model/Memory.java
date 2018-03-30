package sep3.model;

// SEP-3 主記憶
public class Memory {
	public static final int MEM_RD = 0;   // 読み出しモード
	public static final int MEM_WR = 1;   // 書き込みモード

	private Bus			addrBus, dataBus;		// アドレスバスとデータバス（CPUとのインタフェース）
	private int[]		mem = new int[0x10000];	// 記憶する場所
	private IOValue		ioValue;				// メモリマップトI/Oで出力した値を置く（ビューへの通知のため）
	private OnOffFlag	ackLamp;				// メモリマップトI/Oで入力要求があったことをビューへ通知するフラグ

	public IOValue		getIOValue()	{ return ioValue; }
	public OnOffFlag	getAckLamp()	{ return ackLamp; }

	// CPUとのインタフェースである、アドレスバス、データバスを受け取る
	public Memory(Bus aBus, Bus dBus) {
		addrBus   = aBus; dataBus = dBus;
		ioValue = new IOValue();
		ackLamp  = new OnOffFlag();
	}

	// 読み出しか書き込みを指定してアクセスする
	public void access(int rw) {
		int addr = addrBus.getValue();
		if (rw == MEM_RD) {
			if (addr == 0xFFE0) {		// 入力のとき
				ackLamp.on();
			} else {
				dataBus.setValue(mem[addr]);
			}
		} else {
			if (addr == 0xFFE0) {		// 出力のとき
				ioValue.setValue(dataBus.getValue());
			} else {
				mem[addr] = dataBus.getValue();
			}
		}
	}

	public void movToAccess(){
	    dataBus.setValue(mem[addrBus.getValue()]);
	}

	// モデルの初期化
	public void powerOn() {
		for (int i = 0; i < mem.length; ++i) { mem[i] = 0; }
		reset();
	}
	public void reset() {
		ackLamp.off();
		ioValue.setValue(0);
	}
	public void powerOff() { reset(); }

	// メモリを直接書き換えたいときに使う（binファイルのロード時）
	public void setValue(int addr, int value) { mem[addr] = value; }
}
