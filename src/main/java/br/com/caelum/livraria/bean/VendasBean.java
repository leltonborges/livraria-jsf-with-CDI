package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.dao.VendaDAO;
import br.com.caelum.livraria.modelo.Venda;
import br.com.caelum.livraria.transaction.Logs;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.tooltip.Tooltip;
import org.primefaces.model.charts.optionconfig.title.Title;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

//@ManagedBean // Gerenciado beans pelo JSF
//@ViewScoped // Request do JSF package javax.faces.bean.ViewScoped

@Named // Gerenciando beans com CDI
@ViewScoped // Request do CDI, package javax.faces.view.ViewScoped

// CDI necessita do Serializable
@Logs
public class VendasBean implements Serializable {
    private final static long serialVersionUID = 1l;

    @Inject
    private VendaDAO vendaDAO;

    private BarChartModel barModel;

    public BarChartModel getBarModel() {
        return barModel;
    }

    @PostConstruct
    private void init() {
        getVendasModel();
    }

    public void getVendasModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet dataSet = new BarChartDataSet();
        dataSet.setLabel("Vendas dos Livros");

        List<Venda> vendaList = getVendas();
        dataSet.setData(vendaList.parallelStream().map(v -> v.getQuantidade()).collect(Collectors.toList()));

        Random randomColor = new Random(Calendar.getInstance().getTimeInMillis());

        List<String> bgColor = new ArrayList<>();
        for (int i = 0; i < vendaList.size(); i++) {
            Integer color1 = randomColor.nextInt(100) + 155;
            Integer color2 = randomColor.nextInt(100) + 155;
            Integer color3 = randomColor.nextInt(100) + 155;
            Double opacidade = randomColor.nextDouble() / 2 + 0.3;
            bgColor.add("rgba(" + color1 + "," + color2 + "," + color3 + "," + String.format("%.2f", opacidade) + ")");
        }
        dataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();

        for (int i = 0; i < vendaList.size(); i++) {
            Integer color1 = randomColor.nextInt(100) + 155;
            Integer color2 = randomColor.nextInt(155);
            Integer color3 = randomColor.nextInt(100) + 80;
            Double opacidade = randomColor.nextDouble();
            borderColor.add("rgba(" + color1 + "," + color2 + "," + color3 + "," + String.format("%.2f", opacidade) + ")");
        }
        dataSet.setBorderColor(borderColor);
        dataSet.setBorderWidth(1);

        data.addChartDataSet(dataSet);
        data.setLabels(vendaList.parallelStream().map(v -> v.getLivro().getTitulo()).collect(Collectors.toList()));

        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setPosition("top");
        title.setText("grafico por vendas");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        barModel.setData(data);
        barModel.setOptions(options);
    }

    public List<Venda> getVendas() {
        return vendaDAO.listaTodos();
    }
}
