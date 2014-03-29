package net.ralphpina.android.quantifyme;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import net.ralphpina.android.quantifyme.model.Sleep;
import net.ralphpina.android.quantifyme.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ralphpina on 3/2/14.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private BarGraph sleepGraph;
    private RelativeLayout sleepNoDataOverlay;

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

        sleepGraph = (BarGraph)v.findViewById(R.id.bargraphMain);
        sleepGraph.setShowBarText(false);
        sleepGraph.setOnBarClickedListener(new SleepBarClickListener());

        sleepNoDataOverlay = (RelativeLayout) v.findViewById(R.id.sleepNoDataMain);
        sleepNoDataOverlay.setOnClickListener(new EmptySleepBarClickListener());

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

    @Override
    public void onStart() {
        super.onStart();

        ParseQuery<Sleep> query = ParseQuery.getQuery(Sleep.class);
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.whereEqualTo("user", User.getCurrentUser());
        query.findInBackground(new FindCallback<Sleep>() {
            public void done(List<Sleep> sleepObjects, ParseException e) {
                if (e == null) {
                    // Results were successfully found, looking first on the
                    // network and then on disk.
                    Log.i(TAG, "Sleep objects founds : count = " + sleepObjects.size());
                    if (sleepObjects.size() == 0) {
                        populateEmptySleep();
                    }
                } else {
                    // The network was inaccessible and we have no cached data
                    // for this query.
                    Log.i(TAG, "No sleep objects found");
                    populateEmptySleep();
                }
            }
        });
    }

    private void populateEmptySleep() {
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

        sleepGraph.setBars(bars);
        sleepNoDataOverlay.setVisibility(View.VISIBLE);

    }

    private class SleepBarClickListener implements BarGraph.OnBarClickedListener {

        @Override
        public void onClick(int index) {

        }
    }

    private class EmptySleepBarClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            getActivity().getFragmentManager().beginTransaction()
                    .replace(R.id.container, new SleepFragment())
                    .commit();
        }
    }
}
