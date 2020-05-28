package com.xiamo.xmbase.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiamo.xmbase.R;


public class XmTitleBar extends RelativeLayout implements View.OnClickListener {
    ImageView backImg;
    ImageView closeImg;
    TextView titleTv;
    TextView titleRightTv;
    RelativeLayout titleRl;


    //相关接口
    private OnBackClickListener mOnBackClickListener;
    private OnCloseClickListener mOnCloseClickListener;
    private OnRightTextClickListener mOnRightTextClickListener;


    public XmTitleBar(Context context) {
        super(context);
    }

    public XmTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XmTitleBar);
        boolean isBackShow = typedArray.getBoolean(R.styleable.XmTitleBar_isBackShow, true);
        boolean isCloseShow = typedArray.getBoolean(R.styleable.XmTitleBar_isCloseShow, false);
        boolean isRightShow = typedArray.getBoolean(R.styleable.XmTitleBar_isRightShow, false);
        String titleName = "";
        titleName = typedArray.getString(R.styleable.XmTitleBar_titleName);
        String rightText = typedArray.getString(R.styleable.XmTitleBar_rightText);
        if (rightText == null) {
            rightText = "";
        }
        int bgColor = typedArray.getColor(R.styleable.XmTitleBar_backgroundColor, Color.WHITE);
        int titleColor = typedArray.getColor(R.styleable.XmTitleBar_titleColor, Color.parseColor("#333333"));
        int rightTextColor = typedArray.getColor(R.styleable.XmTitleBar_rightTextColor, Color.parseColor("#333333"));

        Drawable backImgRes = typedArray.getDrawable(R.styleable.XmTitleBar_backImg);
        Drawable closeImgRes = typedArray.getDrawable(R.styleable.XmTitleBar_closeImg);

        typedArray.recycle();

        View view = View.inflate(context, R.layout.widget_titlebar, this);
        backImg = (ImageView) view.findViewById(R.id.back_img);
        closeImg = (ImageView) view.findViewById(R.id.close_img);
        titleTv = (TextView) view.findViewById(R.id.title_tv);
        titleRightTv = (TextView) view.findViewById(R.id.title_right_tv);
        titleRl = (RelativeLayout) view.findViewById(R.id.title_rl);

        //设置标题
        backImg.setVisibility(isBackShow ? VISIBLE : INVISIBLE);
        closeImg.setVisibility(isCloseShow ? VISIBLE : GONE);
        titleRightTv.setVisibility(isRightShow ? VISIBLE : INVISIBLE);
        titleTv.setText(titleName);
        titleRightTv.setText(rightText);
        titleRl.setBackgroundColor(bgColor);
        if (backImgRes != null) backImg.setImageDrawable(backImgRes);
        if (closeImgRes != null) closeImg.setImageDrawable(closeImgRes);
        titleTv.setTextColor(titleColor);
        titleRightTv.setTextColor(rightTextColor);

        backImg.setOnClickListener(this);
        closeImg.setOnClickListener(this);
        titleRightTv.setOnClickListener(this);


    }

    public XmTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.close_img) {
            if (mOnBackClickListener == null) {
                ((Activity) getContext()).finish();
            } else {
                mOnBackClickListener.onBackClick();
            }
        } else if (id == R.id.title_right_tv) {
            if (mOnBackClickListener == null) {
                ((Activity) getContext()).finish();
            } else {
                mOnBackClickListener.onBackClick();
            }
        } else if (id == R.id.close_img) {
            if (mOnCloseClickListener != null) mOnCloseClickListener.onCloseClick();
        }

    }


    /**
     * 设置标题
     * @param title
     */
    public void setTitleTv(String title){
        if(title!=null){
            titleTv.setText(title);
        }
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitleTv(int title){
        titleTv.setText(title);
    }

    public void setRightVisiable(boolean visiable){
        titleRightTv.setVisibility(visiable?VISIBLE:INVISIBLE);
    }

    public void setCloseVisiable(boolean visiable){
        closeImg.setVisibility(visiable?VISIBLE:INVISIBLE);
    }


    /**
     * 返回监听接口
     */
    public interface OnBackClickListener {
        void onBackClick();
    }

    /**
     * 设置返回监听
     *
     * @param onBackClickListener
     */
    public void setOnBackClickListener(OnBackClickListener onBackClickListener) {
        if (mOnBackClickListener == null) {
            this.mOnBackClickListener = onBackClickListener;
        }
    }

    /**
     * 右侧按键监听
     */
    public interface OnRightTextClickListener {
        void onRightClick();
    }

    /**
     * 设置右侧按键监听
     *
     * @param onRightTextClickListener
     */
    public void setOnRightTextClickListener(OnRightTextClickListener onRightTextClickListener) {
        if (mOnBackClickListener == null) {
            this.mOnRightTextClickListener = onRightTextClickListener;
        }
    }

    /**
     * 右侧按键监听
     */
    public interface OnCloseClickListener {
        void onCloseClick();
    }

    /**
     * 设置右侧按键监听
     *
     * @param onCloseClickListener
     */
    public void setOnCloseClickListener(OnCloseClickListener onCloseClickListener) {
        if (mOnCloseClickListener == null) {
            this.mOnCloseClickListener = onCloseClickListener;
        }
    }
}
