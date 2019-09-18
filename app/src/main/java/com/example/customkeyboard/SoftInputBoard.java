package com.example.customkeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author Eddy
 * @desc SoftInputBoard
 * @date 2019-09-18 15:42
 */
public class SoftInputBoard extends BaseInputBoard {


    public SoftInputBoard(Context context) {
        super(context);
    }

    public SoftInputBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SoftInputBoard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void findViewsForResource(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.view_soft_input, null);
        TextView tv0 = view.findViewById(R.id.tv0);
        TextView tv1 = view.findViewById(R.id.tv1);
        TextView tv2 = view.findViewById(R.id.tv2);
        TextView tv3 = view.findViewById(R.id.tv3);
        TextView tv4 = view.findViewById(R.id.tv4);
        TextView tv5 = view.findViewById(R.id.tv5);
        TextView tv6 = view.findViewById(R.id.tv6);
        TextView tv7 = view.findViewById(R.id.tv7);
        TextView tv8 = view.findViewById(R.id.tv8);
        TextView tv9 = view.findViewById(R.id.tv9);

        final RelativeLayout ivDelete = view.findViewById(R.id.iv_delete);
        ivDelete.setClickable(true);

        tv0.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        ivDelete.setOnClickListener(this);

        ivDelete.setOnLongClickListener(this);
    }


    @Override
    public void onClick(View v) {

        String value = "";

        int id = v.getId();
        if (id == R.id.tv0)
            value = "0";
        else if (id == R.id.tv1)
            value = "1";
        else if (id == R.id.tv2)
            value = "2";
        else if (id == R.id.tv3)
            value = "3";
        else if (id == R.id.tv4)
            value = "4";
        else if (id == R.id.tv5)
            value = "5";
        else if (id == R.id.tv6)
            value = "6";
        else if (id == R.id.tv7)
            value = "7";
        else if (id == R.id.tv8)
            value = "8";
        else if (id == R.id.tv9)
            value = "9";
        else if (id == R.id.iv_delete)
            value = DELETE;
        callBackData(value);
    }

    @Override
    public boolean onLongClick(View v) {
        callBackData(LONG_DELETE);
        return false;
    }
}
