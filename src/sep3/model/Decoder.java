package sep3.model;

// SEP-3 デコーダ
public class Decoder {
    private int opCode, fromMode, fromRegister, toMode, toRegister;

    public static final int MODE_D  = 0x0; // レジスタ
    public static final int MODE_I  = 0x1; // レジスタ間接
    public static final int MODE_MI = 0x2; // プレデクリメントレジスタ間接
    public static final int MODE_IP = 0x3; // ポストインクリメントレジスタ間接

    // instをopCode, fromMode, fromRegister, toMode, toRegisterに分割する
    public void decode(int inst) {
    	opCode			= (inst >> 10) & 0x003F;
    	fromMode		= (inst >>  8) & 0x0003;
    	fromRegister	= (inst >>  5) & 0x0007;
    	toMode			= (inst >>  3) & 0x0003;
    	toRegister		=  inst        & 0x0007;
    }
    public int getOpCode()       { return opCode; }
    public int getFromMode()     { return fromMode; }
    public int getFromRegister() { return fromRegister; }
    public int getToMode()       { return toMode; }
    public int getToRegister()   { return toRegister; }
}
