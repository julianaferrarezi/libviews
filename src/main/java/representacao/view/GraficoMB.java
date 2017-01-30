package representacao.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@ViewScoped
public class GraficoMB implements Serializable {
	
	private static final long serialVersionUID = 404884016720547828L;
	
	private LineChartModel lineModelNRM;
	private LineChartModel lineModelNSM;
	private LineChartModel lineModelNNM;
	
	@PostConstruct
    public void init() {
		createLineModelsNRM();
        createLineModelsNNM();
        createLineModelsNSM();
    }
	 

	public LineChartModel getLineModel1() {
		return lineModelNNM;
	}

	private void createLineModelsNNM() {
		lineModelNNM = initLinearModelNNM();
		lineModelNNM.setTitle("Primefaces - Number of New Methods (NNM)");
		lineModelNNM.setLegendPosition("e");
		lineModelNNM.setShowDatatip(true);
		Axis xAxis = lineModelNRM.getAxis(AxisType.X);
    	xAxis.setLabel("Version");
        Axis yAxis = lineModelNNM.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(500);
    }
     
    private LineChartModel initLinearModelNNM() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("NNM");
 
        series1.set(5.0, 0);
        series1.set(5.1, 158);
        series1.set(5.2, 329);
 
        model.addSeries(series1);
        return model;
    }
    
    private void createLineModelsNSM() {
    	lineModelNSM = initLinearModelNSM();
    	lineModelNSM.setTitle("Primefaces - Number of Snapshot Methods (NSM)");
    	lineModelNSM.setLegendPosition("e");
    	lineModelNSM.setShowDatatip(true);
    	Axis xAxis = lineModelNRM.getAxis(AxisType.X);
    	xAxis.setLabel("Version");
        Axis yAxis = lineModelNSM.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10000);
    }
     
    private LineChartModel initLinearModelNSM() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("NSM");
 
        series1.set(5.0, 6210);
        series1.set(5.1, 6242);
        series1.set(5.2, 6820);
 
        model.addSeries(series1);
        return model;
    }
    
    private void createLineModelsNRM() {
    	lineModelNRM = initLinearModelNRM();
    	lineModelNRM.setTitle("Primefaces - Number of Removed Methods (NRM)");
    	lineModelNRM.setLegendPosition("e");
    	lineModelNRM.setShowDatatip(true);
    	Axis xAxis = lineModelNRM.getAxis(AxisType.X);
    	xAxis.setLabel("Version");
        Axis yAxis = lineModelNRM.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(50);
        yAxis.setLabel("Metrics");
    }
     
    private LineChartModel initLinearModelNRM() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("NRM");
 
        series1.set(5.0, 0);
        series1.set(5.1, 37);
        series1.set(5.2, 28);
 
        model.addSeries(series1);
        return model;
    }


	public LineChartModel getLineModelNRM() {
		return lineModelNRM;
	}


	public LineChartModel getLineModelNSM() {
		return lineModelNSM;
	}


	public LineChartModel getLineModelNNM() {
		return lineModelNNM;
	}
	
    
}
