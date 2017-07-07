package com.android.launcher3.AnimUtils.Equartion;

public class BouncerEquation extends DampingOscillatorEquation {
    
    public BouncerEquation(double amplitude, double frictionMultiplier,
                           double mass, double rigidity, double phase) {
        super(amplitude, frictionMultiplier, mass, rigidity, phase);
    }

    @Override
    public double compute(double time) {
        return Math.abs(super.compute(time));
    }
}
