package net.ralphpina.android.quantifyme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ralphpina on 3/9/14.
 */
public class NavDrawerAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private ArrayList<String> mTitles;
    private ArrayList<Integer> mImages;

    public NavDrawerAdapter(Context context, ArrayList<String> titles, ArrayList<Integer> images) {
        super(context, R.layout.nav_drawer_cell, R.id.navDrawerItemText, titles);
        this.mContext = context;
        this.mTitles = titles;
        this.mImages = images;
    }

    @Override
    public View getView(int position, View coverView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.nav_drawer_cell,
                parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.navDrawerItemText);
        textView.setText(mTitles.get(position));

        ImageView imageView = (ImageView) rowView.findViewById(R.id.navDrawerItemImage);
        imageView.setImageResource(mImages.get(position));

        return rowView;

    }
}
