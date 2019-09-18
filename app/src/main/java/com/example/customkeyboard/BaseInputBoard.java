package com.example.customkeyboard;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;


/**
 * Created by Nipuream on 2016-07-15.
 */
public abstract class BaseInputBoard<T extends View> extends RelativeLayout
        implements View.OnClickListener, View.OnLongClickListener {

    public static final String DELETE = "delete";
    public static final String LONG_DELETE = "xdelete";

    protected T view;
    private Scroller mScroller;
    private int mScreenHeigh = 0;
    private int mScreenWidth = 0;
    private Boolean isMoving = false;
    private int viewHeight = 0;
    public boolean isShow = false;
    public boolean mEnabled = true;
    public boolean mOutsideTouchable = true;
    private int mDuration = 800;
    public ChoosePayWayListener payListener;

    public interface ChoosePayWayListener {
        void chooseWay(String value);
    }

    public void setOnChoosePayWayListener(ChoosePayWayListener payListener) {
        this.payListener = payListener;
    }

    public BaseInputBoard(Context context) {
        super(context);
        init(context);
    }

    public BaseInputBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseInputBoard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    protected abstract void findViewsForResource(Context context);


    private void init(Context context) {
        setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
        setFocusable(true);
        mScroller = new Scroller(context);
        mScreenHeigh = UIUtils.getWindowHeigh(context);
        mScreenWidth = UIUtils.getWindowWidth(context);
        this.setBackgroundColor(Color.argb(0, 0, 0, 0));
        findViewsForResource(context);

        if (view == null) {
            throw new IllegalStateException("Are you ensure has been inflater xml for filed view?");
        }

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(view, params);

        this.setBackgroundColor(Color.argb(0, 0, 0, 0));
        view.post(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                viewHeight = view.getHeight();
            }
        });
        scrollTo(0, mScreenHeigh);
        TextView btn_close = view.findViewById(R.id.tv_close);
        btn_close.setOnClickListener(v -> dismiss());
    }

    public void dismiss() {
        if (isShow && !isMoving) {
            startMoveAnim(0, -viewHeight, mDuration);
            isShow = false;
            changed();
            if (l != null) {
                l.dismiss();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!mEnabled) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void startMoveAnim(int startY, int dy, int duration) {
        isMoving = true;
        mScroller.startScroll(0, startY, 0, dy, duration);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
            isMoving = true;
        } else {
            isMoving = false;
        }
        super.computeScroll();
    }

    public void show() {
        if (!isShow && !isMoving) {
            startMoveAnim(-viewHeight, viewHeight, mDuration);
            isShow = true;
            changed();
        }
    }

    public interface dismissListener {
        void dismiss();
    }

    private dismissListener l;

    public void setOnDismissListener(dismissListener l) {
        this.l = l;
    }

    public boolean isShow() {
        return isShow;
    }

    public boolean isSlidingEnabled() {
        return mEnabled;
    }

    public void setSlidingEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    public void setOnStatusListener(onStatusListener listener) {
        this.statusListener = listener;
    }

    public void setOutsideTouchable(boolean touchable) {
        mOutsideTouchable = touchable;
    }


    public void changed() {
        if (statusListener != null) {
            if (isShow) {
                statusListener.onShow();
            } else {
                statusListener.onDismiss();
            }
        }
    }

    public onStatusListener statusListener;

    public interface onStatusListener {
        public void onShow();

        public void onDismiss();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        super.onLayout(changed, l, t, r, b);
    }

    public int getSoftInputBoardHeight() {
        return viewHeight;
    }


    public void callBackData(String value) {
        if (payListener != null) {
            payListener.chooseWay(value);
        }
    }

}
