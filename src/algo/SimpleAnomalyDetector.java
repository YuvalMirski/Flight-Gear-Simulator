package algo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import viewModel.TimeSeries;

import java.util.ArrayList;
import java.util.List;

public class SimpleAnomalyDetector extends Canvas implements AnomalyDetector {
    @FXML
    Canvas algPaint;

    ArrayList<CorrelatedFeatures> cf;

    public SimpleAnomalyDetector() {
        cf = new ArrayList<>();
    }

    @Override
    public void learnNormal(TimeSeries ts) {
        ArrayList<String> atts = ts.getAttributes();
        int len = ts.getRowSize();

        float vals[][] = new float[atts.size()][len];
        for (int i = 0; i < atts.size(); i++) {
            for (int j = 0; j < ts.getRowSize(); j++) {
                vals[i][j] = ts.getAttributeData(atts.get(i)).get(j);
            }
        }


        for (int i = 0; i < atts.size(); i++) {
            for (int j = i + 1; j < atts.size(); j++) {
                float p = StatLib.pearson(vals[i], vals[j]);
                if (Math.abs(p) > 0.9) {

                    Point ps[] = toPoints(ts.getAttributeData(atts.get(i)), ts.getAttributeData(atts.get(j)));
                    Line lin_reg = StatLib.linear_reg(ps);
                    float threshold = findThreshold(ps, lin_reg) * 1.1f; // 10% increase

                    CorrelatedFeatures c = new CorrelatedFeatures(atts.get(i), atts.get(j), p, lin_reg, threshold);

                    cf.add(c);
                }
            }
        }
    }

    private Point[] toPoints(ArrayList<Float> x, ArrayList<Float> y) {
        Point[] ps = new Point[x.size()];
        for (int i = 0; i < ps.length; i++)
            ps[i] = new Point(x.get(i), y.get(i));
        return ps;
    }

    private float findThreshold(Point ps[], Line rl) {
        float max = 0;
        for (int i = 0; i < ps.length; i++) {
            float d = Math.abs(ps[i].y - rl.f(ps[i].x));
            if (d > max)
                max = d;
        }
        return max;
    }


    @Override
    public List<AnomalyReport> detect(TimeSeries ts) {
        ArrayList<AnomalyReport> v = new ArrayList<>();

        for (CorrelatedFeatures c : cf) {
            ArrayList<Float> x = ts.getAttributeData(c.feature1);
            ArrayList<Float> y = ts.getAttributeData(c.feature2);
            for (int i = 0; i < x.size(); i++) {
                if (Math.abs(y.get(i) - c.lin_reg.f(x.get(i))) > c.threshold) {
                    String d = c.feature1 + "-" + c.feature2;
                    v.add(new AnomalyReport(d, (i + 1)));
                }
            }
        }
        return v;
    }


    public void paintALGgraph() {
       // FXMLLoader fxl = algPaint



            System.out.println("inside runnable");
            //GraphicsContext helps to add shapes
            algPaint = new Canvas(200,200);
            GraphicsContext gc = algPaint.getGraphicsContext2D();
            algPaint.setHeight(200);
            algPaint.setWidth(200);
            algPaint.setLayoutX(200);
            algPaint.setLayoutY(174);
            //find the mid of the canvas
            double mx = algPaint.getWidth() / 2;
            double my = algPaint.getHeight() / 2;

            //paint a shape around the middle of the canvas
            gc.strokeOval(mx - 50, my - 50, 100, 100);

    }

    public List<CorrelatedFeatures> getNormalModel() {
        return cf;
    }

    public String getCorrelateFeature(String attribute1) {
        for (CorrelatedFeatures c : cf) {
            if (c.feature1.equals(attribute1))
                return c.feature2;
        }
        return null;
    }

    public Line getRegLine(String f1, String f2) {
        for (CorrelatedFeatures c : cf) {
            if (c.feature1.equals(f1) && c.feature2.equals(f2))
                return c.lin_reg;
        }
        return null;
    }
}