package net.noyark.scpslserver.jsmod2.math;

import java.util.Objects;

/**
 * @author magiclu550 #(code) jsmod2
 */

public class Vector {

    private double x;

    private double y;

    private double z;

    public static final Vector ZERO = new Vector(0,0,0);

    public static final Vector ONE = new Vector(1,1,1);

    public static final Vector FORWARD = new Vector(0,0,1);

    public static final Vector BACK = new Vector(0,0,-1);

    public static final Vector UP = new Vector(0,1,0);

    public static final Vector DOWN = new Vector(0,-1,0);

    public static final Vector RIGHT = new Vector(1,0,0);

    public static final Vector LEFT = new Vector(-1,0,0);

    public double sqrMagnitude = x*x+y*y+z*z;

    public static double distance(Vector a, Vector b){
        return vectorSub(a,b).getMagnitude();
    }

    public Vector getNormalize(){
        double num = getMagnitude();
        if(num > 9.99999974737875E-06) return vectorFloatDivided(this,num);
        return ZERO;
    }

    public static Vector lerp(Vector a, Vector b, double t){
        t = Math.min(t,1);
        t = Math.max(t,0);

        return lerpUnclamped(a,b,t);
    }

    public static Vector max(Vector a, Vector b){
        return new Vector(
                Math.max(a.x,b.x),
                Math.max(a.y,b.y),
                Math.max(a.z,b.z)
        );
    }

    public static Vector min(Vector a, Vector b){
        return new Vector(
                Math.min(a.x,b.x),
                Math.min(a.y,b.y),
                Math.min(a.z,b.z)
        );
    }

    public static Vector lerpUnclamped(Vector a, Vector b, double t){
        return vectorAdd(a,vectorFloatMulti(vectorSub(a,b),t));
    }

    public static Vector vectorSub(Vector a, Vector b){
        return new Vector(a.x-b.x,a.y-b.y,a.z-b.z);
    }

    public static Vector vectorAdd(Vector a, Vector b){
        return new Vector(a.x+b.x,a.y+b.y,a.z+b.z);
    }

    public static Vector vectorMulti(Vector a, Vector b){
        return new Vector(a.x*b.x,a.y*b.y,a.z*b.z);
    }

    public static Vector vectorDivided(Vector a, Vector b){
        return new Vector(a.x/b.x,a.y/b.y,a.z/b.z);
    }

    public static Vector vectorFloatSub(Vector a, double b){
        return new Vector(a.x-b,a.y-b,a.z-b);
    }

    public static Vector vectorFloatAdd(Vector a, double b){
        return new Vector(a.x+b,a.y+b,a.z+b);
    }

    public static Vector vectorFloatMulti(Vector a, double d){
        return new Vector(a.x*d,a.y*d,a.z*d);
    }


    public static Vector vectorFloatDivided(Vector a, double b){
        return new Vector(a.x/b,a.y/b,a.z/b);
    }

    public static Vector vectorFloatDivided(double a, Vector b){
        return new Vector(a/b.x,a/b.y,a/b.z);
    }

    public static Vector vectorFloatSub(double a, Vector b){
        return new Vector(a-b.x,a-b.y,a-b.z);
    }

    public double getMagnitude(){
        return Math.sqrt(sqrMagnitude);
    }


    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Double.compare(vector.x, x) == 0 &&
                Double.compare(vector.y, y) == 0 &&
                Double.compare(vector.z, z) == 0 &&
                Double.compare(vector.sqrMagnitude, sqrMagnitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, sqrMagnitude);
    }

}
