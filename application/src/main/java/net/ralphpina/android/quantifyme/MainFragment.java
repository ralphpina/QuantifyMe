package net.ralphpina.android.quantifyme;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;

import java.util.ArrayList;

/**
 * Created by ralphpina on 3/2/14.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        ArrayList<Bar> bars = new ArrayList<Bar>();
        Bar d = new Bar();
        d.setColor(Color.parseColor("#99CC00"));
        d.setName("Weds");
        d.setValue(8);
        bars.add(d);
        d = new Bar();
        d.setColor(Color.parseColor("#FFBB33"));
        d.setName("Thurs");
        d.setValue(6);
        bars.add(d);
        d = new Bar();
        d.setColor(Color.parseColor("#FFBB33"));
        d.setName("Fri");
        d.setValue(7);
        bars.add(d);
        d = new Bar();
        d.setColor(Color.parseColor("#FFBB33"));
        d.setName("Sat");
        d.setValue(8);
        bars.add(d);
        d = new Bar();
        d.setColor(Color.parseColor("#FFBB33"));
        d.setName("Sun");
        d.setValue(5);
        bars.add(d);
        d = new Bar();
        d.setColor(Color.parseColor("#FFBB33"));
        d.setName("Mon");
        d.setValue(6);
        bars.add(d);
        d = new Bar();
        d.setColor(Color.parseColor("#FFBB33"));
        d.setName("Tues");
        d.setValue(7);
        bars.add(d);

        BarGraph g = (BarGraph)v.findViewById(R.id.bargraphMain);
        g.setBars(bars);
        g.setShowBarText(false);

        g.setOnBarClickedListener(new BarGraph.OnBarClickedListener(){

            @Override
            public void onClick(int index) {

            }

        });

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
