package com.xiamo.xmbase.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.xiamo.xmbase.R;

public class XmTabItem extends LinearLayout {

    TextView itemNameTv;
    View itemLineView;
    LinearLayout tabItemLl;

    int checkedColor;
    int unCheckedColor;


    private OnItemClickListener mOnItemClickListener;
    private boolean selected = false;

    public XmTabItem(Context context) {
        super(context);
    }

    public XmTabItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XmTabItem);
        String itemName = "";
        itemName = typedArray.getString(R.styleable.XmTabItem_itemName);
        selected = typedArray.getBoolean(R.styleable.XmTabItem_selected,false);
        Drawable checkedLine = typedArray.getDrawable(R.styleable.XmTabItem_checkedLine);
        checkedColor = typedArray.getColor(R.styleable.XmTabItem_checkedColor, Color.parseColor("#333333"));
        unCheckedColor = typedArray.getColor(R.styleable.XmTabItem_unCheckedColor, Color.parseColor("#999999"));


        typedArray.recycle();
        View view = View.inflate(context, R.layout.widget_tab_item, this);
        itemNameTv = (TextView)view.findViewById(R.id.item_name_tv);
        itemLineView = (View)view.findViewById(R.id.item_line_view);
        tabItemLl = (LinearLayout)view.findViewById(R.id.tab_item_ll);

        itemNameTv.setText(itemName);
        itemLineView.setBackground(checkedLine);
        if(selected){
            itemNameTv.setTextColor(checkedColor);
            itemLineView.setVisibility(VISIBLE);
        }else {
            itemNameTv.setTextColor(unCheckedColor);
            itemLineView.setVisibility(INVISIBLE);
        }

        tabItemLl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick();
                }
            }
        });
    }

    public XmTabItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setItemTitle(String title){
        if(title!=null){
            itemNameTv.setText(title);
        }
    }

    public void setSelect(boolean select){
        selected = select;
        if(selected){
            itemNameTv.setTextColor(checkedColor);
            itemLineView.setVisibility(VISIBLE);
        }else {
            itemNameTv.setTextColor(unCheckedColor);
            itemLineView.setVisibility(INVISIBLE);
        }
    }

    public interface OnItemClickListener{
        void onItemClick();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        if(mOnItemClickListener==null){
            this.mOnItemClickListener = onItemClickListener;
        }
    }
}
