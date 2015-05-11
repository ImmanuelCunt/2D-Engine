/**
 * Author: Simon Gerhalter
 * Last Change: 3.4.2014
 * Function: Klasse wurde entwickelt um "Thread.sleep(x)" zu umgehen, da dieser den Prozess einfriert.
 */

package com.base.engine;

public class Delay {

    public static final long SECOND = 1000000000L;
    public static final long MILLISECOND = 1000000L;

    private static double delta;

    private int length;
    private long endTime;
    boolean started;
    
    public Delay(int millisecond) {
        this.length = millisecond;
        started = false;
    }

    public void start() {
        started = true;
        endTime = length * MILLISECOND + getTime();
    }

    public void stop() {
        started = false;
    }

    public boolean isOver() {
        if(!started)
            return false;
        return getTime() > endTime;
    }
    
    public boolean isActive() {
        return started;
    }
    
    public void terminate() {
        started = true;
        endTime = 0;
    }

    public static double getDelta() {
        return delta;
    }

    public static void setDelta(double delta) {
        Delay.delta = delta;
    }

    public static long getTime() {
        return System.nanoTime();
    }
}