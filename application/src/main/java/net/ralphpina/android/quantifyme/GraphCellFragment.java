package net.ralphpina.android.quantifyme;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

import java.util.ArrayList;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GraphCellFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GraphCellFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class GraphCellFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GraphCellFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GraphCellFragment newInstance(String param1, String param2) {
        GraphCellFragment fragment = new GraphCellFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public GraphCellFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_graph_cell, container, false);

        Line l = new Line();
        LinePoint p = new LinePoint();
        p.setX(0);
        p.setY(5);
        p.setColor("#FF0000");
        l.addPoint(p);
        p = new LinePoint();
        p.setX(8);
        p.setY(8);
        p.setColor("#0000FF");
        l.addPoint(p);
        p = new LinePoint();
        p.setX(10);
        p.setY(4);
        l.addPoint(p);
        p.setColor("#00FF00");
        l.setColor(Color.parseColor("#FFBB33"));

        Line l2 = new Line();
        p = new LinePoint();
        p.setX(2);
        p.setY(3);
        p.setColor("#000000");
        l2.addPoint(p);
        p = new LinePoint();
        p.setX(5);
        p.setY(9);
        p.setColor("#000000");
        l2.addPoint(p);
        p = new LinePoint();
        p.setX(10);
        p.setY(10);
        p.setColor("#000000");
        l2.addPoint(p);
        l2.setColor(Color.parseColor("#000000"));

        LineGraph li = (LineGraph)v.findViewById(R.id.linegraph);
        li.addLine(l);
        li.addLine(l2);
        li.setRangeY(0, 10);
        li.setLineToFill(1);

        li.setOnPointClickedListener(new LineGraph.OnPointClickedListener(){

            @Override
            public void onClick(int lineIndex, int pointIndex) {
                // TODO Auto-generated method stub

            }

        });

        ArrayList<Bar> points = new ArrayList<Bar>();
        Bar d = new Bar();
        d.setColor(Color.parseColor("#99CC00"));
        d.setName("Test1");
        d.setValue(1000);
        d.setValueString("$1,000");
        Bar d2 = new Bar();
        d2.setColor(Color.parseColor("#FFBB33"));
        d2.setName("Test2");
        d2.setValue(2000);
        d2.setValueString("$2,000");
        points.add(d);
        points.add(d2);

        BarGraph g = (BarGraph)v.findViewById(R.id.bargraph);
        g.setBars(points);

        g.setOnBarClickedListener(new BarGraph.OnBarClickedListener(){

            @Override
            public void onClick(int index) {

            }

        });

        PieGraph pg = (PieGraph)v.findViewById(R.id.piegraph);
        PieSlice slice = new PieSlice();
        slice.setColor(Color.parseColor("#99CC00"));
        slice.setValue(2);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#FFBB33"));
        slice.setValue(3);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#AA66CC"));
        slice.setValue(8);
        pg.addSlice(slice);

        pg.setOnSliceClickedListener(new PieGraph.OnSliceClickedListener(){

            @Override
            public void onClick(int index) {

            }

        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
