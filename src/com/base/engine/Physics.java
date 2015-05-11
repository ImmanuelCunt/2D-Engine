/**
 * Author: Simon Gerhalter
 * Last Change: 5.4.2014
 * Function: Physics stuff right here!
 */

package com.base.engine;

import com.base.gameobjects.Circle;

public class Physics {

    public static Vector2f checkCollision(Circle circle1, Circle circle2) {
        float totalRadius = circle1.getRadius() + circle2.getRadius();
        float distanceSquared = (float) (Math.pow(circle1.getPos().getX()-circle2.getPos().getX(), 2) + Math.pow(circle1.getPos().getY()-circle2.getPos().getY(), 2));

        if(distanceSquared < Math.pow(totalRadius, 2)) {
            float difference = (float) (totalRadius - Math.sqrt(distanceSquared)); //find the difference. Square roots are needed here.

            return new Vector2f((circle2.getPos().getX() - circle1.getPos().getX())*difference, (circle2.getPos().getY() - circle1.getPos().getY())*difference);
            //find the movement needed to separate the circles
        }
        return null;
    }

    public static boolean checkCollision(Circle circle1, GameObject go1) {
        for(int i = 0; i < go1.getVertices().length; i++) {
            float x = go1.getVertices()[i].getX() + go1.getPos().getX();
            float y = go1.getVertices()[i].getY() + go1.getPos().getY();

            float distanceSquared = (float) (Math.pow(circle1.getPos().getX()-x, 2) + Math.pow(circle1.getPos().getY()-y, 2));

            if(distanceSquared < Math.pow(circle1.getRadius(), 2)) {
                return true;
            }
        }
        return false;
    }

    //Separating Axis Theorem
    public static boolean checkCollision(GameObject go1, GameObject go2) {
        Vector2f voffset, tmp = null, proj;
        float dp, amin, amax, bmin, bmax, d1, d2, foffset;

        voffset = go1.getPos().sub(go2.getPos());

        for(int i = 0; i < go1.getHitBox().length; i++) {
            tmp = go1.getHitBox()[i].sub(go1.getHitBox()[i + 1 == go1.getHitBox().length ? 0 : i + 1]);
            proj = new Vector2f(-tmp.getY(), tmp.getX()).normalized();

            amin = go1.getHitBox()[0].dot(proj);
            amax = amin;

            for(int j = 1; j < go1.getHitBox().length; j++) {
                dp = go1.getHitBox()[j].dot(proj);
                if (dp < amin)
                    amin = dp;
                if (dp > amax)
                    amax = dp;
            }

            bmin = go2.getHitBox()[0].dot(proj);
            bmax = bmin;

            for(int j = 1; j < go2.getHitBox().length; j++) {
                dp = go2.getHitBox()[j].dot(proj);
                if (dp < bmin)
                    bmin = dp;
                if (dp > bmax)
                    bmax = dp;
            }

            foffset = voffset.dot(proj);
            amin += foffset;
            amax += foffset;
            d1 = amin - bmax;
            d2 = bmin - amax;

            if (d1 > 0 || d2 > 0)
                return false;
        }

        for(int i = 0; i < go2.getHitBox().length; i++) {
            go2.getHitBox()[i].sub(go2.getHitBox()[i + 1 == go2.getHitBox().length ? 0 : i + 1]);
            proj = new Vector2f(-tmp.getY(), tmp.getX()).normalized();

            amin = go1.getHitBox()[0].dot(proj);
            amax = amin;

            for(int j = 1; j < go1.getHitBox().length; j++) {
                dp = go1.getHitBox()[j].dot(proj);
                if (dp < amin)
                    amin = dp;
                if (dp > amax)
                    amax = dp;
            }

            bmin = go2.getHitBox()[0].dot(proj);
            bmax = bmin;

            for(int j = 1; j < go2.getHitBox().length; j++) {
                dp = go2.getHitBox()[j].dot(proj);
                if (dp < bmin)
                    bmin = dp;
                if (dp > bmax)
                    bmax = dp;
            }

            foffset = voffset.dot(proj);
            amin += foffset;
            amax += foffset;
            d1 = amin - bmax;
            d2 = bmin - amax;

            if (d1 > 0 || d2 > 0)
                return false;
        }

        return true;
    }
}
