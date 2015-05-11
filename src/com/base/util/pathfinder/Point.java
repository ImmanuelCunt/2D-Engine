package com.base.util.pathfinder;

import java.util.ArrayList;

/**
 * Created by Simon on 10.06.2014.
 * Object Class used for pathfinder
 */
public class Point {
    private int count;

    private int x;
    private int y;

    private int cost;
    private int heuristic;

    Point pre;

    public Point(int x, int y, int cost, int heuristic, Point pre) {
        this.setX(x);
        this.setY(y);
        this.cost = cost;
        this.heuristic = heuristic;
        this.pre = pre;
    }

    public int getTotalCost() {
        return getPreCost() + cost + heuristic;
    }

    public int getPreCost() {
        if (pre == null) return 0;
        return pre.getPreCost() + cost;
    }

    public void setPrePoint(Point point) {
        pre = point;
    }

    public void print() {
        if (pre != null) {
            pre.print();
        }

        System.out.println("x:" + getX() + " y:" + getY());
    }

    public ArrayList<Point> getPoints(ArrayList<Point> toReturn) {
        if(pre != null)
            toReturn = pre.getPoints(toReturn);

        toReturn.add(this);
        return toReturn;
    }

    public Point getPre() {
        return pre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
