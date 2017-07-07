package com.android.launcher3.AnimUtils.Simulator;


import android.view.animation.Interpolator;

import com.android.launcher3.AnimUtils.Equartion.AbstractEquation;

public class SpringSimulator extends AbstractSimulator implements Interpolator {


    public SpringSimulator(AbstractEquation equation) {
        super(equation);

    }

    public float getInterpolation(float time) {
        float mValue = (float) equation.compute(time);
        return mValue;
    }

}
