package com.example.graduation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class slideadapter extends PagerAdapter {
    Context context ;
    LayoutInflater layoutInflater;
    public slideadapter (Context context)
    {
        this.context=context;

    }

    //Array

    public int[] slide_images = {
            R.drawable.first,
            R.drawable.nnn,
            R.drawable.second,
            R.drawable.hhh,
            R.drawable.fin,
    };

    public String [] slide_headings =
            {
                    "Welcome to our blood bank application",
                    "You will find your blood bags easily",
                    "You can donate any time safely",
                     "Find nearest hospitals","Application have information"

            };

    public String [] slide_description = {
            " ","  "," "," ","to keep you healthy"

    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout)o;
    }

    @NonNull
    @Override
    public Object instantiateItem (ViewGroup container,int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide, container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.intro_img);
        TextView slideHeadings = (TextView) view.findViewById(R.id.intro_title);
        TextView slideDescription = (TextView) view.findViewById(R.id.intro_description);
        slideImageView.setImageResource(slide_images[position]);
        slideHeadings.setText(slide_headings[position]);
        slideDescription.setText(slide_description[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);



    }
}