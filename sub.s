; サブルーチン : int fib(int n, int fibdash)

          . = 0x100
FIBMOCK:  MOV  R1, (SP)+
          MOV  R2, (SP)+
          MOV  -5(SP), R2
          MOV  -4(SP), R1
          CMP  #0, R2
          BRZ  IFTRUE
IFFALSE:  SUB  #1, R2
          MOV  R2, (SP)+
          MOV  R1, (SP)+
          JSR  FIBMOCK
          SUB  #2, SP
          MOV  R0, (SP)+
          SUB  #1, R2
          MOV  R2, (SP)+
          MOV  R0, (SP)+
          RJS  FIBMOCK
          SUB  #2, SP
          ADD  -(SP), R0
          RJP  FIN
IFTRUE:   MOV  R1, R0
FIN:      MOV  -(SP), R2
          MOV  -(SP), R1
          RET
          .END
