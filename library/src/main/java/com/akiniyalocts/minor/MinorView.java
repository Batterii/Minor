package com.akiniyalocts.minor;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by anthonykiniyalocts on 5/13/16.
 */
public class MinorView extends LinearLayout{

    public MinorView(Context context) {
        super(context);
    }

    public MinorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MinorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MinorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {
        init(context, attrs);
    }


    private int selectedTitleColor = -1;

    private int titleColor = -1;

    private TextView titleTextView;

    private LayoutParams params;

    private void init(Context context, AttributeSet attrs) {

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MinorView,
                0, 0
        );

        params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        this.setLayoutParams(getLayoutParamsForIconView());
        this.setOrientation(VERTICAL);


        int iconViewRes = a.getResourceId(R.styleable.MinorView_minor_icon_view, -1);

        String title = a.getString(R.styleable.MinorView_minor_title);

        titleColor = a.getColor(R.styleable.MinorView_minor_title_text_color, ContextCompat.getColor(context,android.R.color.primary_text_light));

        selectedTitleColor = a.getColor(R.styleable.MinorView_minor_title_selected_color, -1);


        if(iconViewRes != -1){
            View iconView = inflate(context, iconViewRes, null);
            this.addView(iconView, getLayoutParamsForIconView());
        }
        else {
            throw new IllegalArgumentException("You must specify a view for MinorView to inflate as an icon. Use app:minor_icon_view=@layout/your_view");
        }

        if(title != null){
            titleTextView = new TextView(context, attrs);

            titleTextView.setLayoutParams(getLayoutParamsForIconView());
            titleTextView.setText(title);

            titleTextView.setTextColor(titleColor);

            if(a.getBoolean(R.styleable.MinorView_minor_selected, false)){
                if(selectedTitleColor != -1){
                    titleTextView.setTextColor(selectedTitleColor);
                }
            }

            this.addView(titleTextView);
        }



        this.setGravity(Gravity.CENTER_HORIZONTAL);

        this.setPadding(10, 10, 10, 10);

        this.requestLayout();


        a.recycle();

    }

    private LayoutParams getLayoutParamsForIconView(){
        if(params == null) {
            params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f);
        }
        return params;
    }

    public void selected(){
        if(titleTextView != null) {
            if (selectedTitleColor != -1) {
                this.titleTextView.setTextColor(selectedTitleColor);

                invalidate();
            }
        }
    }

    public void unselected(){
        if(titleTextView != null){
            if(titleColor != -1){
                this.titleTextView.setTextColor(titleColor);

                invalidate();
            }
        }
    }


}
