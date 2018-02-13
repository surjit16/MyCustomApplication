package com.example.surjit.mycustomapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by surjit on 29-01-2018.
 */

public class CustomGridViewAdapter extends ArrayAdapter<Mobile> {

    ArrayList<Mobile> mobileList = new ArrayList<>();

    public CustomGridViewAdapter(Context context, int textViewResourceId, ArrayList<Mobile> objects) {
        super(context, textViewResourceId, objects);
        mobileList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_grid_view, parent, false);
        }

        ImageView imageView = listItemView.findViewById(R.id.imageView);
        TextView textView = listItemView.findViewById(R.id.textView);

        imageView.setImageResource(mobileList.get(position).getImageID());
        textView.setText(mobileList.get(position).getMobileName());

        return listItemView;
    }
}
