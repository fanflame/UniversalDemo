package com.fanyiran.customrecycleview;

import android.content.Context;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.fanyiran.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 只支持竖向滚动
 */
public class RecycleView extends ViewGroup {
    private static final String TAG = "RecycleView";
    private RecycledViewPool recycledViewPool;
    private Adapter adapter;
    private List<View> containedView;//添加的view
    private int scrolledY;
    private int minScrollDuration;
    private float lastMoveY;
    private boolean shouldRelayout = true;

    public RecycleView(Context context) {
        super(context);
        init(context);
    }

    public RecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        recycledViewPool = new RecycledViewPool();
        minScrollDuration = ViewConfiguration.get(context).getScaledTouchSlop();
        containedView = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (shouldRelayout) {
            int height = getMeasuredHeight();
            int tempHeight = 0;
            containedView.clear();
            for (int i = 0; i < adapter.getCount(); i++) {
                containedView.add(getView(i));
                tempHeight += adapter.getHeight(i);
                if (tempHeight > height) {
                    break;
                }
            }
        }
//        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
//                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (shouldRelayout) {
            shouldRelayout = false;
            if (adapter == null) {
                return;
            }
            View viewTemp;
            int lastBottom = t;
            for (int i = 0; i < containedView.size(); i++) {
                viewTemp = containedView.get(i);
                viewTemp.measure(r - l, adapter.getHeight(i));
                viewTemp.layout(l, lastBottom, r, lastBottom += adapter.getHeight(i));
                addView(viewTemp);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastMoveY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(ev.getRawY() - lastMoveY) > minScrollDuration) {
                    return true;
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (containedView.size() == 0) {
            return true;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float yDiff = event.getRawY() - lastMoveY;
                scrolledY += yDiff;
                int lastPosition = (int) containedView.get(containedView.size() - 1).getTag(R.id.view_tag_position);
                int firstPosition = (int) containedView.get(0).getTag(R.id.view_tag_position);
                if (yDiff < 0 && lastPosition == adapter.getCount() - 1 && (containedView.get(containedView.size() - 1).getBottom() <= getHeight())) {
                    scrolledY = getHeight() - (containedView.get(containedView.size() - 1).getBottom() - containedView.get(0).getTop());
                } else if (yDiff > 0 && firstPosition == 0 && containedView.get(0).getTop() == 0) {
                    scrolledY = 0;
                }
                scrollBy(0, (int) yDiff);
                lastMoveY = event.getRawY();
                break;
        }
        return true;
    }

    @Override
    public void scrollBy(int x, int y) {
        if (scrolledY == 0) {
            return;
        }
        int position;
        if (scrolledY > 0) {
            //手指下滑
            View view;
            LogUtil.v(TAG,"aaaaa","---------------------");
            while (containedView.size() != 0 &&
                    containedView.get(containedView.size() - 1).getTop() > getHeight()) {
                view = containedView.get(containedView.size() - 1);
//                LogUtil.v(TAG,"aaaaa","v position:"+view.getTag(R.id.view_tag_position));
//                LogUtil.v(TAG,"aaaaa","v top:"+view.getTop());
//                LogUtil.v(TAG,"aaaaa","diff:"+(getHeight() - Math.abs(scrolledY)));
//                LogUtil.v(TAG,"aaaaa","height:"+(getHeight()));
                containedView.remove(containedView.size() - 1);
                recycledViewPool.recycleView(view);
                removeView(view);
            }
            int heightTemp;
            while ((containedView.size() != 0)
                    && scrolledY > 0) {
                position = (int) containedView.get(0).getTag(R.id.view_tag_position);
                if (position - 1 < 0) {
                    scrolledY = 0;
                    break;
                }
                view = getView(position - 1);
                containedView.add(0, view);
                view.measure(getWidth(), heightTemp = (Integer) view.getTag(R.id.view_tag_height));
                addView(view, 0);
                scrolledY -= heightTemp;
            }
        } else {
            //手指上滑
            View view;
            int bottom;
            bottom = containedView.get(containedView.size() - 1).getBottom();
            int heightTemp;
            // TODO: 2019/3/17 下行的getHeight应该修改
//            if ((int) containedView.get(containedView.size() - 1).getTag(R.id.view_tag_position) == adapter.getCount() - 1
//                    && bottom < getHeight() && Math.abs(scrolledY) > getHeight() - bottom)
//                scrolledY = bottom - getHeight();//修正
            while (bottom < getHeight() + Math.abs(scrolledY)) {
                position = (int) containedView.get(containedView.size() - 1).getTag(R.id.view_tag_position);
                if (position == adapter.getCount() - 1) {
                    break;
                }
                view = getView(position + 1);
                containedView.add(view);
                view.measure(getWidth(), heightTemp = (Integer) view.getTag(R.id.view_tag_height));
                addView(view);
                bottom += heightTemp;
            }

            int temp;
            while ((containedView.size() != 0)) {
                view = containedView.get(0);
                temp = scrolledY;
                temp += (int) view.getTag(R.id.view_tag_height);
                if (temp > 0) {
//                    scrolledY = getHeight() - getItemHeight();
                    break;
                }
                containedView.remove(0);
                recycledViewPool.recycleView(view);
                removeView(view);
                scrolledY = temp;
            }
        }
        repositionViews();
    }

    private int getItemHeight() {
        if (containedView.size() == 0)
            return 0;
        int result = 0;
        for (View view : containedView) {
            result += (int) view.getTag(R.id.view_tag_height);
        }
        return result;

    }

    private void repositionViews() {
        int top = scrolledY;
//        LogUtil.v(TAG, "scrolledY" + scrolledY);
        int height;
        for (View view : containedView) {
            view.layout(0, top, getMeasuredWidth(), top + (height = (int) view.getTag(R.id.view_tag_height)));
            top += height;
        }
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        invalidate();
    }

    public View getView(int position) {
        int type = adapter.getType(position);
        View view = recycledViewPool.getRecycledView(type);
        if (view == null) {
            view = adapter.createView(position);
        }
        adapter.bindView(view, position);
        view.setTag(R.id.view_tag_type, type);
        view.setTag(R.id.view_tag_height, adapter.getHeight(position));
        view.setTag(R.id.view_tag_position, position);
        return view;
    }


    interface Adapter {
        int getHeight(int position);

        int getCount();

        int getType(int position);

        View createView(int position);

        void bindView(View view, int position);
    }

    class RecycledViewPool {
        SparseArray<List<View>> recycledView;

        public RecycledViewPool() {
            this.recycledView = new SparseArray<>();
        }

        public View getRecycledView(int type) {
            List<View> tempList = recycledView.get(type);
            if (tempList == null || tempList.size() == 0) {
                return null;
            }
            View view = tempList.get(0);
            tempList.remove(0);
            LogUtil.v("RecycledViewPool", "getRecycledView", type + "");
            return view;
        }

        public void recycleView(View view) {
            int type = (int) view.getTag(R.id.view_tag_type);
            List<View> tempList = recycledView.get(type);
            if (tempList == null) {
                tempList = new ArrayList<>();
                recycledView.put(type, tempList);
            }
            tempList.add(view);
            LogUtil.v("RecycledViewPool", "recycleView", type + "");
        }
    }
}
