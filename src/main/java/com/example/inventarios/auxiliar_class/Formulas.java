package com.example.inventarios.auxiliar_class;

public class Formulas {
    //Q optima
    public double Qopt (double D, double S, double H){
        double Qo =Math.sqrt( (2 * D * S)/H);
        return Qo;
    }

    //Costo Anual Total
    public double TC (double DC, double DS_Q, double QH_2){
        double Tc = DC + DS_Q + QH_2;
        return Tc;
    }

    //Costo de Compra anual
    public double DC (double D, double C){
        double Dc = D * C;
        return Dc;
    }

    //Costo de Pedidos Anual
    public double DS_Q (double D, double S, double Q){
        double Ds_q = (D / Q) * S;
        return Ds_q;
    }

    //Costo de Mantenimiento anual
    public double QH_2 (double Q, double H){
        double Qh_2 = (Q / 2) * H;
        return Qh_2;
    }

    //Inventario promedio
    public double Q_2 (double Q){
        double q_2 = (Q / 2);
        return q_2;
    }

    //Ordenes anuales
    public double D_Q (double D, double Q){
        double D_q = D / Q;
        return D_q;
    }
}
