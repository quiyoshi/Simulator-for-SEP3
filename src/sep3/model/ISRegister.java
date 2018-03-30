package sep3.model;

// 命令レジスタ：　ただのレジスタにはない機能が必要
public class ISRegister extends Register {
	private Decoder decoder;

	ISRegister(Decoder d) { decoder = d; }
	// セレクタを通じて、バスからゲートを越えてやってきた値の処理
	public void setPreValue(int v) {
		super.setPreValue(v);
	}
	public void clock() {
		if (super.getValue() != super.getPreValue()) {
			super.clock();
			decoder.decode(super.getValue());	// 命令が格納されたので、デコーダに渡す
			setChanged();
			notifyObservers();					// 値の変更をviewに通知
		}
	}
}
