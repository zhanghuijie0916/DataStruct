package org.sunny.graph;

/**
 *
 */
public class VTime {
    private int earlyTime;
    private int lateTime;

    public VTime(){
        this(0,Integer.MAX_VALUE);
    }

    public VTime(int earlyTime,int lateTime){
        this.earlyTime = earlyTime;
        this.lateTime = lateTime;
    }

    public int getEarlyTime() {
        return earlyTime;
    }

    public void setEarlyTime(int earlyTime) {
        this.earlyTime = earlyTime;
    }

    public int getLateTime() {
        return lateTime;
    }

    public void setLateTime(int lateTime) {
        this.lateTime = lateTime;
    }

    @Override
    public String toString() {
        return "VTime{" +
                "earlyTime=" + earlyTime +
                ", lateTime=" + lateTime +
                '}';
    }
}
