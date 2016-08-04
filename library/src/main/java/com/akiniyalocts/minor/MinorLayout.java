package com.akiniyalocts.minor;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

/**
 * Created by anthonykiniyalocts on 5/13/16.
 */
public class MinorLayout extends LinearLayout {

    public MinorLayout(Context context) {
        super(context);
    }

    public MinorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MinorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MinorLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

    private LayoutParams params;

    private TableLayout.LayoutParams weightParams;

    private boolean shouldCenter;

    private boolean vertical = false;

    private void initView(Context context, AttributeSet attrs) {

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MinorLayout,
                0, 0
        );

        shouldCenter = a.getBoolean(R.styleable.MinorLayout_minor_center_items, false);

        vertical = a.getBoolean(R.styleable.MinorLayout_minor_orientation_vertical, false);

        this.setOrientation(vertical ? VERTICAL : HORIZONTAL);

        if(shouldCenter) {
            this.setGravity(Gravity.CENTER_HORIZONTAL);
            this.getLayoutParams();
        }

        this.setLayoutParams(getOrientationParams());

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if(shouldCenter) {
            this.setWeightSum(this.getChildCount());

            applyChildWeight();
        }
    }

    private void applyChildWeight(){
        for(int i = 0; i < this.getChildCount(); i++){
            MinorView view = (MinorView)this.getChildAt(i);

            view.setLayoutParams(getParamsWithWeight());
        }
        invalidate();
    }

    private LayoutParams getOrientationParams(){
        if(vertical){
            return getVerticalLayoutParams();
        }
        return getLayoutParamsDefault();
    }

    private LayoutParams getLayoutParamsDefault(){
        if(params == null) {

            params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        return params;
    }

    private LayoutParams getVerticalLayoutParams(){
        if(params == null) {

            params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        }
        return params;
    }

    private TableLayout.LayoutParams getParamsWithWeight(){
        if(weightParams == null){
            weightParams = new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        }
        return weightParams;
    }


}
