package ufrn.campusseguro.Fragment;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import ufrn.campusseguro.Banco.MockAPI;
import ufrn.campusseguro.Banco.MockEstatisticas;
import ufrn.campusseguro.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentEstatistica#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentEstatistica extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";




    public fragmentEstatistica() {
        // Required empty public constructor
    }

    public static fragmentEstatistica newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragmentEstatistica fragment = new fragmentEstatistica();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estatistica, container, false);
        MockEstatisticas mockEstatisticas = new MockEstatisticas();
        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        GraphView graph1 = (GraphView) view.findViewById(R.id.graph2);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(mockEstatisticas.inserirDados(1));
        graph.addSeries(series);
        series.setShape(PointsGraphSeries.Shape.POINT);

        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<DataPoint>(mockEstatisticas.inserirDados(2));
        graph.addSeries(series2);
        series2.setShape(PointsGraphSeries.Shape.RECTANGLE);
        series2.setColor(Color.RED);

        PointsGraphSeries<DataPoint> series3 = new PointsGraphSeries<>(mockEstatisticas.inserirDados(3));
        graph.addSeries(series3);
        series3.setShape(PointsGraphSeries.Shape.TRIANGLE);
        series3.setColor(Color.YELLOW);

        PointsGraphSeries<DataPoint> series4 = new PointsGraphSeries<>(mockEstatisticas.inserirDados(4));
        graph.addSeries(series4);
        series4.setColor(Color.GREEN);
        series4.setCustomShape(new PointsGraphSeries.CustomShape() {
            @Override
            public void draw(Canvas canvas, Paint paint, float x, float y, DataPointInterface dataPoint) {
                paint.setStrokeWidth(10);
                canvas.drawLine(x-20, y-20, x+20, y+20, paint);
                canvas.drawLine(x+20, y-20, x-20, y+20, paint);
            }
        });

        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(mockEstatisticas.inserirDados(5));
        graph1.addSeries(series1);


        return view;
    }

}
