package com.org.v4.graphics.drawable;

abstract class RippleDrawable {
    private RippleDrawable() {
    }

    public boolean draw() {
        return false;
    }

    public void setColor() {
    }

    public abstract void start();

    public abstract void stopAnimation();
}
