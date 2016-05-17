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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by anthonykiniyalocts on 5/13/16.
 */
public class MinorView extends FrameLayout{

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


        // get attributes
        int iconViewRes = a.getResourceId(R.styleable.MinorView_minor_icon_view, -1);

        String title = a.getString(R.styleable.MinorView_minor_title);

        titleColor = a.getColor(R.styleable.MinorView_minor_title_text_color, ContextCompat.getColor(context,android.R.color.primary_text_light));

        selectedTitleColor = a.getColor(R.styleable.MinorView_minor_title_selected_color, -1);



        // Build layout
        LinearLayout minorLayout = new LinearLayout(getContext());

        minorLayout.setOrientation(LinearLayout.VERTICAL);


        minorLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        minorLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        minorLayout.setPadding(5, 5, 5, 5);


        this.setClickable(true);


        // Add icon and title
        if(iconViewRes != -1){
            View iconView = inflate(context, iconViewRes, null);
            minorLayout.addView(iconView, getLayoutParamsForIconView());
        }
        else {
            throw new IllegalArgumentException("You must specify a view for MinorView to inflate as an icon. Use app:minor_icon_view=@layout/your_view");
        }

        if(title != null){
            titleTextView = new TextView(context, attrs);

            titleTextView.setLayoutParams(getLayoutParamsForIconView());
            titleTextView.setText(title);
            titleTextView.setClickable(false);

            titleTextView.setTextColor(titleColor);

            if(a.getBoolean(R.styleable.MinorView_minor_selected, false)){
                if(selectedTitleColor != -1){
                    titleTextView.setTextColor(selectedTitleColor);
                }
            }

            titleTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            minorLayout.addView(titleTextView);
        }



        minorLayout.requestLayout();

        this.addView(minorLayout);

        a.recycle();

    }


    private LayoutParams getLayoutParamsForIconView(){
        if(params == null) {
            params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
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

    public void addNotifcation(int notificationCount){
        FrameLayout notificationView = (FrameLayout) inflate(getContext(), R.layout.minor_notification, null);

        TextView notificationTextView = (TextView) notificationView.findViewById(R.id.minor_notification_text);


        notificationTextView.setText(String.valueOf(notificationCount));
        LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.TOP | Gravity.RIGHT);
        params.setMargins(20,15,5,5);
        notificationView.setLayoutParams(params);

        this.addView(notificationView);

        invalidate();
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
