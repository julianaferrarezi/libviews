package representacao.view;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.chart.DonutChartModel;
 
@ManagedBean
public class ChartView implements Serializable {
 
    private DonutChartModel donutModel1;
    private DonutChartModel donutModel2;
 
    @PostConstruct
    public void init() {
        createDonutModels();
    }
 
    public DonutChartModel getDonutModel1() {
        return donutModel1;
    }
     
    public DonutChartModel getDonutModel2() {
        return donutModel2;
    }
     
    private void createDonutModels() {
        donutModel1 = initDonutModel();
        donutModel1.setTitle("Donut Chart");
        donutModel1.setLegendPosition("w");
         
        donutModel2 = initDonutModel();
        donutModel2.setTitle("Custom Options");
        donutModel2.setLegendPosition("e");
        donutModel2.setSliceMargin(5);
        donutModel2.setShowDataLabels(true);
        donutModel2.setDataFormat("value");
        donutModel2.setShadow(false);
    }
 
    private DonutChartModel initDonutModel() {
        DonutChartModel model = new DonutChartModel();
         
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        circle1.put("com/sun/jersey/server", 600);
        circle1.put("com/sun/jersey/spi", 200);
        circle1.put("com/sun/jersey/api", 100);
        model.addCircle(circle1);
         
        Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
        circle2.put("com/sun/jersey/spi", 540);
        circle2.put("Brand 2", 125);
        circle2.put("Brand 3", 702);
        circle2.put("Brand 4", 421);
        model.addCircle(circle2);
         
        Map<String, Number> circle3 = new LinkedHashMap<String, Number>();
        circle3.put("com/sun/jersey/api", 40);
        circle3.put("Brand 2", 325);
        circle3.put("Brand 3", 402);
        circle3.put("Brand 4", 421);
        model.addCircle(circle3);
         
        return model;
    }
    
}