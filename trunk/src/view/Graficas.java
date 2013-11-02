package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.util.HashMap;
import java.util.Map;

import model.Ladrillo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import utils.Utils.Ensayo;
import utils.Utils.Resultado;
import controller.GraficasController;

public class Graficas extends View<GraficasController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3187984413603970112L;

	/**
	 * Create the frame.
	 * @param cantLadrillos 
	 * @param estadisticasEnsayo 
	 */
	public Graficas(Map<Resultado, Integer> cantLadrillos, Map<Ensayo, Map<Resultado, Integer>> estadisticasEnsayo) {

		/*
		 * Ejemplo grafico torta
		 */
		/*
		PieDataset dataset = createDataset(cantLadrillos);
		// based on the dataset we create the chart
		JFreeChart chart = createChart(dataset, "Resumen Ladrillos");
		// we put the chart into a panel
		ChartPanel chartPanel = new ChartPanel(chart);
		// default size
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		// add it to our application
		setContentPane(chartPanel);*/

		/*
		 * Ejemplo grfico barras
		 */
		final CategoryDataset dataset = createDatasetBar(estadisticasEnsayo);
		final JFreeChart chart = createChartBar(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartPanel);
	}

	private  PieDataset createDataset(Map<Resultado, Integer> cantLadrillos) {
		DefaultPieDataset result = new DefaultPieDataset();
		Integer buenos = cantLadrillos.get(Resultado.BUENO);
		Integer malos = cantLadrillos.get(Resultado.MALO);
		Integer regulares = cantLadrillos.get(Resultado.REGULAR);
		if (buenos > 0){
			result.setValue("Buenos = "+buenos, buenos);
		} 
		if (malos > 0){
			result.setValue("Malos = "+malos, malos);
		}
		if (regulares > 0){
			result.setValue("Regulares = "+regulares, regulares);
		}
		return result;

	}

	private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart(
                title,  // chart title
                dataset,             // data
                true,               // include legend
                true,
                false
            );

            PiePlot plot = (PiePlot) chart.getPlot();
            plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            plot.setNoDataMessage("No data available");
            plot.setCircular(false);
            plot.setLabelGap(0.02);
            return chart;

	}

	private JFreeChart createChartBar(final CategoryDataset dataset) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createBarChart(
				"Bar Chart Demo",         // chart title
				"Category",               // domain axis label
				"Value",                  // range axis label
				dataset,                  // data
				PlotOrientation.VERTICAL, // orientation
				true,                     // include legend
				true,                     // tooltips?
				false                     // URLs?
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

		// set the background color for the chart...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		// set the range axis to display integers only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// disable bar outlines...
		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// set up gradient paints for series...
		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 
				 0.0f, 0.0f, new Color(0, 0, 64));
		GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 
				 0.0f, 0.0f, new Color(0, 64, 0));
		GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 
				 0.0f, 0.0f, new Color(64, 0, 0));
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);

		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(
				CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
				);
		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;

	}

	private CategoryDataset createDatasetBar(Map<Ensayo, Map<Resultado, Integer>> estadisticasEnsayo) {

		// row keys...
		final String regulares = "Regulares";
		final String malas = "Malas";
		final String buenas = "Buenas";

		// column keys...
		final String ensayoDimension = "Ensayo Dimensión";
		final String ensayoTemperatura = "Ensayo Temperatura";
		final String ensayoUltraSonido = "Ensayo Ultra Sonido";
		final String ensayoDureza = "Ensayo Dureza";

		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		Map<Resultado, Integer> dimension = estadisticasEnsayo.get(Ensayo.DIMENSION);
		Map<Resultado, Integer> temperatura = estadisticasEnsayo.get(Ensayo.TEMPERATURA);
		Map<Resultado, Integer> velocidad = estadisticasEnsayo.get(Ensayo.VELOCIDAD);
		Map<Resultado, Integer> dureza = estadisticasEnsayo.get(Ensayo.DUREZA);

		dataset.addValue(dimension.get(Resultado.REGULAR), regulares, ensayoDimension);
		dataset.addValue(temperatura.get(Resultado.REGULAR), regulares, ensayoTemperatura);
		dataset.addValue(velocidad.get(Resultado.REGULAR), regulares, ensayoUltraSonido);
		dataset.addValue(dureza.get(Resultado.REGULAR), regulares, ensayoDureza);

		dataset.addValue(dimension.get(Resultado.MALO), malas, ensayoDimension);
		dataset.addValue(temperatura.get(Resultado.MALO), malas, ensayoTemperatura);
		dataset.addValue(velocidad.get(Resultado.MALO), malas, ensayoUltraSonido);
		dataset.addValue(dureza.get(Resultado.MALO), malas, ensayoDureza);

		dataset.addValue(dimension.get(Resultado.BUENO), buenas, ensayoDimension);
		dataset.addValue(temperatura.get(Resultado.BUENO), buenas, ensayoTemperatura);
		dataset.addValue(velocidad.get(Resultado.BUENO), buenas, ensayoUltraSonido);
		dataset.addValue(dureza.get(Resultado.BUENO), buenas, ensayoDureza);

		return dataset;

	}

}
