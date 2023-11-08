package com.example.inventarios.controllers;

import com.example.inventarios.auxiliar_class.Formulas;
import com.example.inventarios.auxiliar_class.Redondeos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class QoptimaController implements Initializable {
    @FXML private HBox Grafica;
    @FXML private Label CalcularButton;
    @FXML private HBox RehacerButton;
    @FXML private TextField QMinTextField;
    @FXML private TextField QMaxTextField;
    Formulas F = new Formulas();
    Redondeos R = new Redondeos();
    @FXML private LineChart<Number, Number> GraficaEOQ;
    @FXML private TextField C_OrdenarTextField;
    @FXML private TextField C_MantenerTextField;
    @FXML private TextField C_UnidadTextField;
    @FXML private TextField QTextField;
    @FXML private TextField I_maxTextField;
    @FXML private TextField P_InventarioTextField;
    @FXML private TextField OrdenesPeriodoTextField;
    @FXML private TextField C_A_Mantener;
    @FXML private TextField C_A_InventarioTextField;
    @FXML private TextField C_A_Ordenar;
    @FXML private TextField C_A_UnidadesTextField;
    @FXML private TextField C_A_TotalTextField;
    @FXML private TextField DemandaTextField;

    int Qmin = 1; int Qmax = 0; int Q_optima; double Demanda;double C_Ordenar;double C_Mantener;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {//this.GraficaEOQ.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        Grafica.setVisible(false);
        RehacerButton.setVisible(false);
        GraficaEOQ.setVisible(false);
    }

    @FXML
    public void EOQ(MouseEvent event){
        try {
            this.GraficaEOQ.getData().clear();
            Qmin=1; QMinTextField.setText(String.valueOf(Qmin));
            Qmax=0; QMaxTextField.setText(String.valueOf(Qmax));
            Demanda = Double.parseDouble(this.DemandaTextField.getText());
            C_Ordenar = Double.parseDouble(this.C_OrdenarTextField.getText());
            C_Mantener = Double.parseDouble(this.C_MantenerTextField.getText());
            double C_unitario = Double.parseDouble(this.C_UnidadTextField.getText());
            Q_optima = R.cantidad((F.Qopt(Demanda, C_Ordenar, C_Mantener)));

            this.QTextField.setText(String.valueOf(Q_optima)); this.I_maxTextField.setText(String.valueOf(Q_optima));
            double InvProm = F.Q_2(Q_optima); this.P_InventarioTextField.setText(String.valueOf(R.cantidad(InvProm)));
            double Ordenes = F.D_Q(Demanda,Q_optima); this.OrdenesPeriodoTextField.setText(String.valueOf(R.cantidad(Ordenes)));
            double Cost_A_Pedidos = F.DS_Q(Demanda, C_Ordenar, Q_optima); this.C_A_Ordenar.setText(String.valueOf(R.dinero(Cost_A_Pedidos)));
            double Cost_A_Mantenimiento = F.QH_2(Q_optima, C_Mantener); this.C_A_Mantener.setText(String.valueOf(R.dinero(Cost_A_Mantenimiento)));
            double Cost_A_Inventario = Cost_A_Pedidos + Cost_A_Mantenimiento; this.C_A_InventarioTextField.setText(String.valueOf(R.dinero(Cost_A_Inventario)));
            double Cost_A_Compra = F.DC(Demanda, C_unitario); this.C_A_UnidadesTextField.setText(String.valueOf(R.dinero(Cost_A_Compra)));
            double Cost_A_total = Cost_A_Inventario+Cost_A_Compra; this.C_A_TotalTextField.setText(String.valueOf(R.dinero(Cost_A_total)));
            RehacerButton.setVisible(true);
            Grafica.setVisible(true);
            CalcularButton.setVisible(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Aumentar_QMin(MouseEvent event) {
        if (Qmin < Q_optima) {
            Qmin++;
            QMinTextField.setText(String.valueOf(Qmin));
            Graficar();
        }
    }

    public void Disminuir_QMin(MouseEvent event) {
        if (Qmin > 1) {
            Qmin--;
            QMinTextField.setText(String.valueOf(Qmin));
            Graficar();
        }
    }

    public void Aumentar_QMax(MouseEvent event) {
        if (Qmax < Q_optima) {
            Qmax++;
            QMaxTextField.setText(String.valueOf(Qmax));
            Graficar();
        }
    }

    public void Disminuir_QMax(MouseEvent event) {
        if (Qmax > 1){
            Qmax--;
            QMaxTextField.setText(String.valueOf(Qmax));
            Graficar();
        }
    }

    public void Graficar(){
        this.GraficaEOQ.getData().clear();
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Costo Ordenar");
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Costo Mantener");
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        series3.setName("Costo Ordenar+mantener");

        for (int i = Qmin; i <= (Q_optima+Qmax); i++) {
            double CostoOrd = F.DS_Q(Demanda, C_Ordenar,i);
            double CostoMan = F.QH_2(i, C_Mantener);
            series1.getData().add(new XYChart.Data<>(i, CostoOrd));
            series2.getData().add(new XYChart.Data<>(i, CostoMan));
            double CostOrdMand = CostoOrd+CostoMan;
            series3.getData().add(new XYChart.Data<>(i, CostOrdMand));
        }

        this.GraficaEOQ.getData().add(series1);
        this.GraficaEOQ.getData().add(series2);
        this.GraficaEOQ.getData().add(series3);
        this.GraficaEOQ.setCreateSymbols(false);
    }

    public void Rehacer(MouseEvent event) {
        RehacerButton.setVisible(false);
        Grafica.setVisible(false);
        CalcularButton.setVisible(true); GraficaEOQ.setVisible(false);
    }

    public void GraficaEOQ(MouseEvent event) {
        Graficar();
        GraficaEOQ.setVisible(true);
    }
}
