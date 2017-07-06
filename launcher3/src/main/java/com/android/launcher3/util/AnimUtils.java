package com.android.launcher3.util;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;

import com.android.launcher3.R;

/**
 * Created by MartinRGB on 2017/7/6.
 */

public class AnimUtils {

    //TODO Fling Animtion Or Spring Function by Romain Guy
    //https://developer.android.com/reference/android/support/animation/package-summary.html
    //http://jroller.com/gfx/entry/real_world_physics_in_swing
    //https://twitter.com/crafty/status/842055117323026432



    public static class ScrollInterpolator implements Interpolator {
        public ScrollInterpolator() {
        }

        public float getInterpolation(float t) {
            t -= 1.0f;
            return t*t*t*t*t + 1;
        }
    }

    public static class CubicBezierInterpolator implements Interpolator {

        protected PointF start;
        protected PointF end;
        protected PointF a = new PointF();
        protected PointF b = new PointF();
        protected PointF c = new PointF();

        public CubicBezierInterpolator(PointF start, PointF end) throws IllegalArgumentException {
            if (start.x < 0 || start.x > 1) {
                throw new IllegalArgumentException("startX value must be in the range [0, 1]");
            }
            if (end.x < 0 || end.x > 1) {
                throw new IllegalArgumentException("endX value must be in the range [0, 1]");
            }
            this.start = start;
            this.end = end;
        }

        public CubicBezierInterpolator(float startX, float startY, float endX, float endY) {
            this(new PointF(startX, startY), new PointF(endX, endY));
        }

        public CubicBezierInterpolator(double startX, double startY, double endX, double endY) {
            this((float) startX, (float) startY, (float) endX, (float) endY);
        }

        @Override
        public float getInterpolation(float time) {
            return getBezierCoordinateY(getXForTime(time));
        }

        protected float getBezierCoordinateY(float time) {
            c.y = 3 * start.y;
            b.y = 3 * (end.y - start.y) - c.y;
            a.y = 1 - c.y - b.y;
            return time * (c.y + time * (b.y + time * a.y));
        }

        protected float getXForTime(float time) {
            float x = time;
            float z;
            for (int i = 1; i < 14; i++) {
                z = getBezierCoordinateX(x) - time;
                if (Math.abs(z) < 1e-3) {
                    break;
                }
                x -= z / getXDerivate(x);
            }
            return x;
        }

        private float getXDerivate(float t) {
            return c.x + t * (2 * b.x + 3 * a.x * t);
        }

        private float getBezierCoordinateX(float time) {
            c.x = 3 * start.x;
            b.x = 3 * (end.x - start.x) - c.x;
            a.x = 1 - c.x - b.x;
            return time * (c.x + time * (b.x + time * a.x));
        }
    }

    public static class SwiftOutInterpolator implements Interpolator{

        @Override
        public float getInterpolation(float time) {
            return new CubicBezierInterpolator(0.55f, 0.0f, 0.1f, 1f).getInterpolation(time);
        }
    }

    public static class SharpeInterpolator implements Interpolator{

        @Override
        public float getInterpolation(float time) {
            return new CubicBezierInterpolator(0.4f, 0.0f, 0.6f, 1f).getInterpolation(time);
        }
    }

    public static class BackEaseInInterpolater implements Interpolator {
        private final float mOvershot;

        public BackEaseInInterpolater() {
            mOvershot = 0;
        }

        /**
         * @param overshot
         */
        public BackEaseInInterpolater(float overshot) {
            mOvershot = overshot;
        }

        public float getInterpolation(float t) {
            float s = mOvershot == 0 ? 1.70158f : mOvershot;
            return t * t * ((s + 1) * t - s);
        }
    }

    public static class BackEaseInOutInterpolater implements Interpolator {
        private final float mOvershot;

        public BackEaseInOutInterpolater() {
            mOvershot = 0;
        }

        /**
         * @param overshot
         */
        public BackEaseInOutInterpolater(float overshot) {
            mOvershot = overshot;
        }

        public float getInterpolation(float t) {
            float s = mOvershot == 0 ? 1.70158f : mOvershot;

            t *= 2;
            if (t < 1) {
                s *= (1.525);
                return 0.5f * (t * t * ((s + 1) * t - s));
            }

            t -= 2;
            s *= (1.525);
            return 0.5f * (t * t * ((s + 1) * t + s) + 2);
        }
    }

    public static class BackEaseOutInterpolater implements Interpolator {
        private final float mOvershot;

        public BackEaseOutInterpolater() {
            mOvershot = 0;
        }

        /**
         * @param overshot
         */
        public BackEaseOutInterpolater(float overshot) {
            mOvershot = overshot;
        }

        public float getInterpolation(float t) {
            float s = mOvershot == 0 ? 1.70158f : mOvershot;
            t -= 1;
            return (t * t * ((s + 1) * t + s) + 1);
        }
    }

    public static class BounceEaseInInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return 1 - new BounceEaseOutInterpolater().getInterpolation(1 - t);
        }
    }

    public static class BounceEaseInOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            if (t < 0.5f) {
                return new BounceEaseInInterpolater().getInterpolation(t * 2) * 0.5f;
            } else {
                return new BounceEaseOutInterpolater().getInterpolation(t * 2 - 1) * 0.5f + 0.5f;
            }
        }
    }

    public static class BounceEaseOutInterpolater implements Interpolator {

        public float getInterpolation(float t) {
            if (t < (1 / 2.75)) {
                return 7.5625f * t * t;
            } else if (t < (2 / 2.75)) {
                t -= (1.5 / 2.75);
                return 7.5625f * t * t + 0.75f;
            } else if (t < (2.5 / 2.75)) {
                t -= (2.25 / 2.75);
                return 7.5625f * t * t + 0.9375f;
            } else {
                t -= (2.625 / 2.75);
                return 7.5625f * t * t + 0.984375f;
            }
        }
    }

    public static class CircEaseInInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return -(float) (Math.sqrt(1 - t * t) - 1);
        }
    }

    public static class CircEaseOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return (float) Math.sqrt(1 - (t -= 1) * t);
        }
    }

    public static class CircEaseInOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            t *= 2;
            if (t < 1) {
                return -0.5f * (float) (Math.sqrt(1 - t * t) - 1);
            }

            t -= 2;
            return 0.5f * (float) (Math.sqrt(1 - t * t) + 1);
        }
    }

    public static class CubicEaseInInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return t * t * t;
        }
    }

    public static class CubicEaseInOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            t *= 2;
            if (t < 1) {
                return 0.5f * t * t * t;
            }

            t -= 2;
            return 0.5f * (t * t * t + 2);
        }
    }

    public static class CubicEaseOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            t -= 1;
            return t * t * t + 1;
        }
    }

    public static class ElasticEaseInInterpolater implements Interpolator {
        private final float mAmplitude;
        private final float mPeriod;

        public ElasticEaseInInterpolater() {
            mAmplitude = 0;
            mPeriod = 0;
        }

        /**
         * @param amplitude
         * @param period
         */
        public ElasticEaseInInterpolater(float amplitude, float period) {
            mAmplitude = amplitude;
            mPeriod = period;
        }

        public float getInterpolation(float t) {
            float p = mPeriod;
            float a = mAmplitude;

            float s;
            if (t == 0) {
                return 0;
            }
            if (t == 1) {
                return 1;
            }
            if (p == 0) {
                p = 0.3f;
            }
            if (a == 0 || a < 1) {
                a = 1;
                s = p / 4;
            } else {
                s = (float) (p / (Math.PI * 2) * Math.asin(1 / a));
            }
            t -= 1;
            return -(float) (a * Math.pow(2, 10 * t) * Math.sin((t - s) * (Math.PI * 2) / p));
        }
    }

    public static class ElasticEaseInOutInterpolater implements Interpolator {
        private final float mAmplitude;
        private final float mPeriod;

        public ElasticEaseInOutInterpolater() {
            mAmplitude = 0;
            mPeriod = 0;
        }

        /**
         * @param amplitude
         * @param period
         */
        public ElasticEaseInOutInterpolater(float amplitude, float period) {
            mAmplitude = amplitude;
            mPeriod = period;
        }

        public float getInterpolation(float t) {
            float p = mPeriod;
            float a = mAmplitude;

            float s;
            if (t == 0) {
                return 0;
            }

            t /= 0.5f;
            if (t == 2) {
                return 1;
            }
            if (p == 0) {
                p = 0.3f * 1.5f;
            }
            if (a == 0 || a < 1) {
                a = 1;
                s = p / 4;
            } else {
                s = (float) (p / (Math.PI * 2) * Math.asin(1 / a));
            }
            if (t < 1) {
                t -= 1;
                return -0.5f * (float) (a * Math.pow(2, 10 * t) * Math.sin((t - s) * (Math.PI *
                        2) / p));
            }

            t -= 1;
            return (float) (a * Math.pow(2, -10 * t) * Math.sin((t - s) * (Math.PI * 2) / p) *
                    0.5f + 1);
        }
    }

    public static class ElasticEaseOutInterpolater implements Interpolator {
        private final float mAmplitude;
        private final float mPeriod;

        public ElasticEaseOutInterpolater() {
            mAmplitude = 0;
            mPeriod = 0;
        }

        /**
         * @param amplitude
         * @param period
         */
        public ElasticEaseOutInterpolater(float amplitude, float period) {
            mAmplitude = amplitude;
            mPeriod = period;
        }

        public float getInterpolation(float t) {
            float p = mPeriod;
            float a = mAmplitude;

            float s;
            if (t == 0) {
                return 0;
            }
            if (t == 1) {
                return 1;
            }
            if (p == 0) {
                p = 0.3f;
            }
            if (a == 0 || a < 1) {
                a = 1;
                s = p / 4;
            } else {
                s = (float) (p / (Math.PI * 2) * Math.asin(1 / a));
            }
            return (float) (a * Math.pow(2, -10 * t) * Math.sin((t - s) * (Math.PI * 2) / p) + 1);
        }
    }

    public static class ExpoEaseInInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return (t == 0) ? 0 : (float) Math.pow(2, 10 * (t - 1));
        }
    }

    public static class ExpoEaseInOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            if (t == 0) {
                return 0;
            }
            if (t == 1) {
                return 1;
            }

            t *= 2;
            if (t < 1) {
                return 0.5f * (float) Math.pow(2, 10 * (t - 1));
            }

            --t;
            return 0.5f * (float) (-Math.pow(2, -10 * t) + 2);
        }
    }

    public static class ExpoEaseOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return (t == 1) ? 1 : (float) (-Math.pow(2, -10 * t) + 1);
        }
    }

    public static class LinearEaseNoneInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return t;
        }
    }

    public static class QuadEaseInInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return t * t;
        }
    }

    public static class QuadEaseInOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            t *= 2;
            if (t < 1) {
                return 0.5f * t * t;
            }
            --t;
            return -0.5f * (t * (t - 2) - 1);
        }
    }

    public static class QuadEaseOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return -t * (t - 2);
        }
    }

    public static class QuartEaseInInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return t * t * t * t;
        }
    }

    public static class QuartEaseInOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            t *= 2;
            if (t < 1) {
                return 0.5f * t * t * t * t;
            }
            t -= 2;
            return -0.5f * (t * t * t * t - 2);
        }
    }

    public static class QuartEaseOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            t -= 1;
            return -(t * t * t * t - 1);
        }
    }

    public static class QuintEaseInInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return t * t * t * t * t;
        }
    }

    public static class QuintEaseInOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            t *= 2;
            if (t < 1) {
                return 0.5f * t * t * t * t * t;
            }
            t -= 2;
            return 0.5f * (t * t * t * t * t + 2);
        }
    }

    public static class QuintEaseOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            t -= 1;
            return (t * t * t * t * t + 1);
        }
    }

    public static class SineEaseInInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return -(float) Math.cos(t * (Math.PI / 2)) + 1;
        }
    }

    public static class SineEaseInOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return -0.5f * (float) (Math.cos(Math.PI * t) - 1);
        }
    }

    public static class SineEaseOutInterpolater implements Interpolator {
        public float getInterpolation(float t) {
            return (float) Math.sin(t * (Math.PI / 2));
        }
    }
}
