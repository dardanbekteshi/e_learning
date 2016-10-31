package com.example.dardan.elearning;

/**
 * Created by Dardan on 4/19/2016.
 */
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomCategoryAdapter extends ArrayAdapter<Category> {
    public CustomCategoryAdapter(Context context, ArrayList<Category> categories) {
        super(context, 0, categories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Category category = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_template, parent, false);
        }
        // Lookup view for data population
        TextView categoryTitle = (TextView) convertView.findViewById(R.id.card_title);
        TextView categoryHighscore = (TextView) convertView.findViewById(R.id.card_highscore);
        ImageView categoryImage=(ImageView) convertView.findViewById(R.id.card_image);
        RelativeLayout categoryLayout=(RelativeLayout) convertView.findViewById(R.id.cardLayout);
        // Populate the data into the template view using the data object
        categoryTitle.setText(category.title);
        categoryHighscore.setText("Highscore: "+ category.highscore);
        categoryImage.setImageResource(category.image);
        categoryLayout.setBackgroundColor(category.color);
        // Return the completed view to render on screen
        return convertView;
    }
}