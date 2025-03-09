package com.example.test;

import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;

public class product_view extends LinearLayout {

        public product_view(Context context, AttributeSet attrs)
        {
        super(context, attrs);
        init(context);
        }


        public product_view(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        }

            private void init(Context context)
            {
         LayoutInflater.from(context).inflate(R.layout.product_view, this, true);
            }




}
