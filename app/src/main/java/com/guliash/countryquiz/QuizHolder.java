package com.guliash.countryquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class QuizHolder extends FrameLayout {

    private static final String SUPER_STATE_EXTRA = "super_state";
    private static final String CURRENT_VIEW_INDEX_EXTRA = "current_view_index";

    private static final float OPACITY_FACTOR = 0.3f;

    private static final float WIDTH_THRESHOLD = 0.5f;

    private static final float SCALE_FACTOR = 0.2f;

    private static final float SWIPE_THRESHOLD = 0.7f;

    private static final long SWIPE_DURATION = 250;

    private GestureDetectorCompat mDetector;
    private int mCurrentViewIndex = 0;
    private boolean mSwiping;
    private boolean swipedAfterFling;
    private boolean mReverting;
    private Scroller mScroller;

    public QuizHolder(Context context) {
        super(context);
        init();
    }

    public QuizHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuizHolder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mDetector = new GestureDetectorCompat(getContext(), mGestureListener);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SUPER_STATE_EXTRA, super.onSaveInstanceState());
        bundle.putInt(CURRENT_VIEW_INDEX_EXTRA, mCurrentViewIndex);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mCurrentViewIndex = bundle.getInt(CURRENT_VIEW_INDEX_EXTRA);
            state = bundle.getParcelable(SUPER_STATE_EXTRA);
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2) {
            throw new IllegalStateException("Only for 2 children");
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        moveBackAnotherChild();
    }

    private int getCurrentViewIndex() {
        return mCurrentViewIndex;
    }

    private int getAnotherViewIndex() {
        return mCurrentViewIndex == 0 ? 1 : 0;
    }

    private View getCurrentChild() {
        return getChildAt(getCurrentViewIndex());
    }

    private View getAnotherChild() {
        return getChildAt(getAnotherViewIndex());
    }

    private void changeCurrentView() {
        mCurrentViewIndex = getAnotherViewIndex();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        mDetector.onTouchEvent(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                Log.e("TAG ", "ACTION UP");
                if (exceedsSwipeThreshold() || swipedAfterFling) {
                    swipeCurrentChild();
                    moveInAnotherChild();
                } else {
                    revertCurrentChild();
                    revertAnotherChild();
                }
                return true;

        }
        return super.onTouchEvent(event);
    }

    private final GestureDetector.OnGestureListener mGestureListener = new SimpleOnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (velocityX >= 0) {
                return false;
            }
            final View currentChild = getCurrentChild();
            mScroller.forceFinished(true);
            mScroller.fling((int) currentChild.getX(), (int) currentChild.getY(),
                    (int) velocityX, (int) velocityY, Integer.MIN_VALUE, Integer.MAX_VALUE,
                    Integer.MIN_VALUE, Integer.MAX_VALUE);
            float diff = currentChild.getX() - mScroller.getFinalX();

            if (Math.abs(ViewCompat.getTranslationX(getCurrentChild())) + diff > SWIPE_THRESHOLD * getWidthThreshold()) {
                swipedAfterFling = true;
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (mSwiping || mReverting) {
                return false;
            }

            updateCurrentChild(distanceX, distanceY);
            updateAnotherChild(distanceX, distanceY);

            return true;

        }
    };

    private void updateCurrentChild(float distanceX, float distanceY) {
        final View currentChild = getCurrentChild();

        float widthThreshold = getWidthThreshold();

        float newScale = ViewCompat.getScaleX(currentChild) - SCALE_FACTOR * distanceX / widthThreshold;
        newScale = Math.min(Math.max(0, newScale), 1);

        float newTranslationX = ViewCompat.getTranslationX(currentChild) - distanceX;
        newTranslationX = Math.min(0, newTranslationX);

        float newAlpha = ViewCompat.getAlpha(currentChild) - OPACITY_FACTOR * distanceX / widthThreshold;
        newAlpha = Math.min(1, newAlpha);

        ViewCompat.setScaleX(currentChild, newScale);
        ViewCompat.setScaleY(currentChild, newScale);
        ViewCompat.setTranslationX(currentChild, newTranslationX);
        ViewCompat.setAlpha(currentChild, newAlpha);
    }

    private void updateAnotherChild(float distanceX, float distanceY) {
        final View anotherChild = getAnotherChild();

        float widthThreshold = getWidthThreshold();

        float newScale = ViewCompat.getScaleX(anotherChild) + SCALE_FACTOR * distanceX / widthThreshold;
        newScale = Math.min(newScale, 1);

        float newTranslationX = ViewCompat.getTranslationX(anotherChild) - distanceX;
        newTranslationX = Math.max(0, newTranslationX);

        float newAlpha = ViewCompat.getAlpha(anotherChild) + OPACITY_FACTOR * distanceX / widthThreshold;
        newAlpha = Math.min(1, newAlpha);

        ViewCompat.setScaleX(anotherChild, newScale);
        ViewCompat.setScaleY(anotherChild, newScale);
        ViewCompat.setTranslationX(anotherChild, newTranslationX);
        ViewCompat.setAlpha(anotherChild, newAlpha);
    }

    private boolean exceedsSwipeThreshold() {
        return Math.abs(ViewCompat.getTranslationX(getCurrentChild())) > SWIPE_THRESHOLD * getWidthThreshold();
    }

    private float getWidthThreshold() {
        return getWidth() * WIDTH_THRESHOLD;
    }

    private void swipeCurrentChild() {
        final View currentChild = getCurrentChild();
        mSwiping = true;
        currentChild
                .animate()
                .alpha(0f)
                .x(-currentChild.getWidth())
                .setDuration(SWIPE_DURATION)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        onViewSwiped();
                    }
                });
    }

    private void onViewSwiped() {
        mSwiping = false;
        swipedAfterFling = false;
        changeCurrentView();
        moveBackAnotherChild();
    }

    private void moveInAnotherChild() {
        final View anotherChild = getAnotherChild();
        anotherChild
                .animate()
                .translationX(0)
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setListener(null)
                .setDuration(SWIPE_DURATION);
    }

    private void revertCurrentChild() {
        final View currentChild = getCurrentChild();
        mReverting = true;
        currentChild
                .animate()
                .alpha(1f)
                .scaleX(1)
                .scaleY(1)
                .translationX(0)
                .setDuration(SWIPE_DURATION)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mReverting = false;
                    }
                });
    }

    private void revertAnotherChild() {
        final View anotherChild = getAnotherChild();
        anotherChild
                .animate()
                .x(getWidth())
                .scaleY(1 - SCALE_FACTOR)
                .scaleX(1 - SCALE_FACTOR)
                .alpha(0f)
                .setListener(null)
                .setDuration(SWIPE_DURATION);
    }

    private void moveBackAnotherChild() {
        final View anotherChild = getAnotherChild();
        ViewCompat.setX(anotherChild, getWidth());
        ViewCompat.setAlpha(anotherChild, 0);
        ViewCompat.setScaleX(anotherChild, 1 - SCALE_FACTOR);
        ViewCompat.setScaleY(anotherChild, 1 - SCALE_FACTOR);
    }
}
