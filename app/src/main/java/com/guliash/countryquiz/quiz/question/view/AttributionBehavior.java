package com.guliash.countryquiz.quiz.question.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.guliash.countryquiz.R;

import timber.log.Timber;

public class AttributionBehavior extends CoordinatorLayout.Behavior<TextView> {

    float mPaddingBottom;

    public AttributionBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mPaddingBottom = context.getResources().getDimension(R.dimen.answers_panel_peek_height);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof FrameLayout && dependency.getId() == R.id.answers_container;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        child.setY(dependency.getY() - child.getHeight());
        Timber.e("%f %f", dependency.getY(), mPaddingBottom);
        return true;
    }
}
