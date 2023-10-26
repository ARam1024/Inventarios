package com.example.inventarios.controllers;

import com.example.inventarios.auxiliar_class.Formulas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class QoptimaController implements Initializable {
    Formulas F = new Formulas();
    @FXML
    private LineChart<Number, Number> GraficaEOQ;
    @FXML
    private TextField C_OrdenarTextField;
    @FXML
    private TextField C_MantenerTextField;
    @FXML
    private TextField C_UnidadTextField;
    @FXML
    private TextField QTextField;
    @FXML
    private TextField I_maxTextField;
    @FXML
    private TextField P_InventarioTextField;
    @FXML
    private TextField OrdenesPeriodoTextField;
    @FXML
    private TextField C_A_Mantener;
    @FXML
    private TextField C_A_InventarioTextField;
    @FXML
    private TextField C_A_Ordenar;
    @FXML
    private TextField C_A_UnidadesTextField;
    @FXML
    private TextField C_A_TotalTextField;
    @FXML
    private TextField DemandaTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void EOQ(ActionEvent event){
        try {
            this.GraficaEOQ.getData().clear();
            double Demanda = Double.parseDouble(this.DemandaTextField.getText());
            double C_Ordenar = Double.parseDouble(this.C_OrdenarTextField.getText());
            double C_Mantener = Double.parseDouble(this.C_MantenerTextField.getText());
            double C_unitario = Double.parseDouble(this.C_UnidadTextField.getText());
            double Q_optima = F.Qopt(Demanda, C_Ordenar, C_Mantener); this.QTextField.setText(String.valueOf(Q_optima)); this.I_maxTextField.setText(String.valueOf(Q_optima));
            double InvProm = F.Q_2(Q_optima); this.P_InventarioTextField.setText(String.valueOf(InvProm));
            double Ordenes = F.D_Q(Demanda,Q_optima); this.OrdenesPeriodoTextField.setText(String.valueOf(Ordenes));
            double Cost_A_Pedidos = F.DS_Q(Demanda, C_Ordenar, Q_optima); this.C_A_Ordenar.setText(String.valueOf(Cost_A_Pedidos));
            double Cost_A_Mantenimiento = F.QH_2(Q_optima, C_Mantener); this.C_A_Mantener.setText(String.valueOf(Cost_A_Mantenimiento));
            double Cost_A_Inventario = Cost_A_Pedidos + Cost_A_Mantenimiento; this.C_A_InventarioTextField.setText(String.valueOf(Cost_A_Inventario));
            double Cost_A_Compra = F.DC(Demanda, C_unitario); this.C_A_UnidadesTextField.setText(String.valueOf(Cost_A_Compra));
            double Cost_A_total = Cost_A_Inventario+Cost_A_Compra; this.C_A_TotalTextField.setText(String.valueOf(Cost_A_total));

            XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
            series1.setName("Costo de Ordenar");
            XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
            series2.setName("Costo de Mantener");
            //series1.getData().add(new XYChart.Data<>(1, 20));
            for (int i = 25; i < (Q_optima + 100); i++) {
                series1.getData().add(new XYChart.Data<>(i, F.DS_Q(Demanda, C_Ordenar,i)));
            }
            for (int i = 25; i < (Q_optima + 100); i++) {
                series2.getData().add(new XYChart.Data<>(i, F.QH_2(i, C_Mantener)));
            }
            this.GraficaEOQ.getData().add(series1);
            this.GraficaEOQ.getData().add(series2);
            this.GraficaEOQ.setCreateSymbols(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
