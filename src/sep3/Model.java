package sep3;
import java.io.InputStream;

import sep3.misc.FileIO;
import sep3.model.Bus;
import sep3.model.CPU;
import sep3.model.Memory;
import sep3.model.OnOffFlag;
import sep3.model.Selector;
import sep3.model.runmode.RunMode;
import sep3.model.runmode.RunModeFactory;

public class Model {
	private Bus			addrBus, dataBus;  // アドレスバスとデータバス
	private CPU			cpu;               // SEP-3 CPU
	private Memory		mem;               // 主記憶装置
	private OnOffFlag	dispSW;            // 走行モード設定スイッチ10：ISR, PCの表示切替
	private OnOffFlag	run1SW;            // 走行モード設定スイッチ12：クロックステップ
	private OnOffFlag	run2SW;            // 走行モード設定スイッチ13：命令ステップ
	private OnOffFlag	powerSW;           // 電源スイッチ

	// 走行モードごとに仕事を生成
	RunModeFactory rmf = new RunModeFactory();
	// 現走行モード
	RunMode currentRunMode;

	// 各種 getter
	public Selector getAddrBusSelector()	{ return addrBus.getSelector(); }
	public Selector getDataBusSelector()	{ return dataBus.getSelector(); }
	public Bus getAddrBus()					{ return addrBus; }
	public Bus getDataBus()					{ return dataBus; }
	public CPU getCPU()						{ return cpu; }
	public Memory getMemory()				{ return mem; }
	public OnOffFlag getDispSW()			{ return dispSW; }
	public OnOffFlag getRun1SW()			{ return run1SW; }
	public OnOffFlag getRun2SW()			{ return run2SW; }
	public OnOffFlag getPowerSW()			{ return powerSW; }

	// SEP-3 のモデル生成
	public Model() {
		addrBus   = new Bus(Bus.NeedSelector, !Bus.NeedSelector);	// アドレスバスの出力先セレクタは不要（メモリしかない）
		dataBus   = new Bus(Bus.NeedSelector, Bus.NeedSelector);	// データバスはどちらにもセレクタ必要
		cpu       = new CPU(addrBus, dataBus);
		getAddrBusSelector().setCPU(cpu);
		getDataBusSelector().setCPU(cpu);
		mem       = new Memory(addrBus, dataBus);
		dispSW    = new OnOffFlag();
		run1SW    = new OnOffFlag();
		run2SW    = new OnOffFlag();
		powerSW   = new OnOffFlag();
		currentRunMode = rmf.getRunMode(RunModeFactory.RM_NORMAL);	// 最初に起動するときは、通常走行モード
	}

	// only for test
	String msg;
	public String getMessage() { return msg; }
	String stack2string(StackTraceElement[] sa) {
		String msg = "";
		for (int i = 0; i < sa.length; ++i) {
			msg = msg + sa[i].toString() + "\n";
		}
		return msg;
	}

	// 電源投入
	public void powerOn() {
		mem.powerOn();
		reset();
		// IPL の仕事をする
		try {
			final String iplFile = "sep3/misc/pro.bin";		// これはテスト用であって、実機のIPLとは違う
			FileIO fio = new FileIO(this);
			InputStream in = getClass().getClassLoader().getResourceAsStream(iplFile);
			if (in != null) {
				fio.load(in);
				in.close();
			}
		} catch (Exception e1) {
				e1.printStackTrace();
		}
	}

	// 電源断
	public void powerOff() {
		cpu.powerOff();
		mem.powerOff();
		// ランプを消すには、0をセットしなければならない
		addrBus.setValue(0);
		dataBus.setValue(0);
	}

	// 電源を落とさずにリセット（主記憶装置はそのままで、他を初期化）
	public void reset() {
		cpu.reset();
		mem.reset();
		addrBus.setValue(0);
		dataBus.setValue(0);
		cpu.setCurrentState(cpu.getCurrentState().clockstep(this));	// まず最初のゲートを開ける
	}

	// 走行モード設定
	public void setRunMode(int rm)	{ currentRunMode = rmf.getRunMode(rm); }
	//public RunMode getRunMode()	{ return currentRunMode; }

	// 実行
	public void run()	{ currentRunMode.run(this); }
	// モデルにクロック投入（1ステップの状態遷移をさせる）： 上記 run() の中で使う
	public void clock()	{ cpu.clock(); }

}
