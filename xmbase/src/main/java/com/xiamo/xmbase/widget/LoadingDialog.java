package com.xiamo.xmbase.widget;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xiamo.xmbase.R;

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder{
        private Context context;
        private String message;
        private boolean isShowMessage = true;
        private boolean isCancelable = false;
        private boolean isCancelOutside = false;

        public Builder(Context context){
            this.context = context;
        }
        public Builder setMessage(String message){
            this.message = message;
            return this;
        }
        public Builder setMessage(int message){
            this.message = context.getString(message);
            return this;
        }
        public Builder setShowMessage(boolean isShowMessage){
            this.isShowMessage = isShowMessage;
            return this;
        }

        public Builder setCancelable(boolean isCancelable){
            this.isCancelable=isCancelable;
            return this;
        }

        public Builder setCancelOutside(boolean isCancelOutside){
            this.isCancelOutside=isCancelOutside;
            return this;
        }

        public LoadingDialog create(){
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.pop_loading,null);
            LoadingDialog loadingDialog = new LoadingDialog(context,R.style.TCDialog);
            TextView msgText= (TextView) view.findViewById(R.id.tipTextView);
            if(isShowMessage){
                msgText.setText(message);
            }else{
                msgText.setVisibility(View.GONE);
            }
            loadingDialog.setContentView(view);
            loadingDialog.setCancelable(isCancelable);
            loadingDialog.setCanceledOnTouchOutside(isCancelOutside);
            return loadingDialog;
        }
    }
}