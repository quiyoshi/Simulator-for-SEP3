package sep3.model;

import java.util.*;

// バスの入力口、出力口に設置されているゲートのどれを選択して、バスへ／からデータを取得するか決める
// ための制御回路（セレクタと呼ぶ）
public class Selector {
	// 選択対象レジスタ管理用
	private LinkedList<Integer> toConnected, fromConnected;
	// 接続バス
	private Bus outBus, inBus;
	private CPU cpu;

	public Selector(Bus in, Bus out) {
		inBus = in; outBus = out;
		if (in != null) fromConnected = new LinkedList<Integer>();
		if (out != null) toConnected  = new LinkedList<Integer>();
	}
	public Selector(CPU c, Bus in, Bus out) {
		this(in, out);
		cpu = c;
	}
	public void setCPU(CPU c) { cpu = c; }

	// 入力側ゲート集合に、新たなゲートを追加
	public void addFrom(int r) {
		fromConnected.add(new Integer(r));
	}
	// 出力側ゲート集合に、新たなゲートを追加
	public void addTo(int r) {
		toConnected.add(new Integer(r));
	}
	// 入力側ゲート集合のうち、ひとつだけを選択してバスへデータを流す
	public void selectFrom(int r) {
		Iterator<Integer> i = fromConnected.listIterator();
		while (i.hasNext()) {
			int p = i.next().intValue();
			if (p == r) {
				inBus.setValue(cpu.getRegister(r).getValue());
			}
		}
	}
	// 出力側ゲート集合のうち、ひとつゲートを開いてデータを渡す
	public void selectTo(int r) {
		Iterator<Integer> i = toConnected.listIterator();
		while (i.hasNext()) {
			int p = i.next().intValue();
			if (p == r) {
				cpu.getRegister(r).setPreValue(outBus.getValue());
			}
		}
	}
	// 出力側ゲート集合のうち、ふたつゲートを開いてデータを渡す
	public void selectTo(int r1, int r2) {
		Iterator<Integer> i = toConnected.listIterator();
		while (i.hasNext()) {
			int p = i.next().intValue();
			if (p == r1) { cpu.getRegister(r1).setPreValue(outBus.getValue()); }
			if (p == r2) { cpu.getRegister(r2).setPreValue(outBus.getValue()); }
		}
	}
	// 出力側ゲート集合のうち、みっつゲートを開いてデータを渡す
	public void selectTo(int r1, int r2, int r3) {
		Iterator<Integer> i = toConnected.listIterator();
		while (i.hasNext()) {
			int p = i.next().intValue();
			if (p == r1) { cpu.getRegister(r1).setPreValue(outBus.getValue()); }
			if (p == r2) { cpu.getRegister(r2).setPreValue(outBus.getValue()); }
			if (p == r3) { cpu.getRegister(r3).setPreValue(outBus.getValue()); }
		}
	}
}
