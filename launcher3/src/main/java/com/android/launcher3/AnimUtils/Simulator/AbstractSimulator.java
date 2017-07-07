package com.android.launcher3.AnimUtils.Simulator;

import com.android.launcher3.AnimUtils.Equartion.AbstractEquation;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class AbstractSimulator implements PropertyChangeListener {
    protected AbstractEquation equation;
    protected double time;
    protected int timeScale;    
    
    
    public AbstractSimulator(AbstractEquation equation) {
        this.equation = equation;
        this.equation.addPropertyChangeListener(this);
        this.time = 0.0f;
        this.timeScale = 1;
    }

    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void setTime(double time) {
        this.time = time;
    }
    
    public double getTime() {
        return this.time;
    }
    
    public void setTimeScale(int timeScale) {
        this.timeScale = timeScale;
    }
    
    public int getTimeScale() {
        return this.timeScale;
    }
}
