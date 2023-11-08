package com.example.inventarios.auxiliar_class;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class Redondeos {
    public BigDecimal dinero(double n) {
        BigDecimal bd = new BigDecimal(n).setScale(2, RoundingMode.HALF_UP);
        //double bd1=bd.doubleValue();
        return bd;
    }
    public int cantidad(double n){
        BigDecimal bd = new BigDecimal(n).setScale(0, RoundingMode.UP);
        int bd1 = bd.intValue();
        return bd1;
    }
}
