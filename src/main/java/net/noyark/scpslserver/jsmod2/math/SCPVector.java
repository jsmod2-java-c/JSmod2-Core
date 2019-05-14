package net.noyark.scpslserver.jsmod2.math;

import java.util.Objects;

/**
 * @author magiclu550 #(code) jsmod2
 */

public class SCPVector {

    private double x;

    private double y;

    private double z;

    public static final SCPVector ZERO = new SCPVector(0,0,0);

    public static final SCPVector ONE = new SCPVector(1,1,1);

    public static final SCPVector FORWARD = new SCPVector(0,0,1);

    public static final SCPVector BACK = new SCPVector(0,0,-1);

    public static final SCPVector UP = new SCPVector(0,1,0);

    public static final SCPVector DOWN = new SCPVector(0,-1,0);

    public static final SCPVector RIGHT = new SCPVector(1,0,0);

    public static final SCPVector LEFT = new SCPVector(-1,0,0);

    public double sqrMagnitude = x*x+y*y+z*z;

    public static double distance(SCPVector a, SCPVector b){
        return vectorSub(a,b).getMagnitude();
    }

    public SCPVector getNormalize(){
        double num = getMagnitude();
        if(num > 9.99999974737875E-06) return vectorFloatDivided(this,num);
        return ZERO;
    }

    public static SCPVector lerp(SCPVector a, SCPVector b, double t){
        t = Math.min(t,1);
        t = Math.max(t,0);

        return lerpUnclamped(a,b,t);
    }

    public static SCPVector max(SCPVector a, SCPVector b){
        return new SCPVector(
                Math.max(a.x,b.x),
                Math.max(a.y,b.y),
                Math.max(a.z,b.z)
        );
    }

    public static SCPVector min(SCPVector a, SCPVector b){
        return new SCPVector(
                Math.min(a.x,b.x),
                Math.min(a.y,b.y),
                Math.min(a.z,b.z)
        );
    }

    public static SCPVector lerpUnclamped(SCPVector a, SCPVector b, double t){
        return vectorAdd(a,vectorFloatMulti(vectorSub(a,b),t));
    }

    public static SCPVector vectorSub(SCPVector a, SCPVector b){
        return new SCPVector(a.x-b.x,a.y-b.y,a.z-b.z);
    }

    public static SCPVector vectorAdd(SCPVector a, SCPVector b){
        return new SCPVector(a.x+b.x,a.y+b.y,a.z+b.z);
    }

    public static SCPVector vectorMulti(SCPVector a, SCPVector b){
        return new SCPVector(a.x*b.x,a.y*b.y,a.z*b.z);
    }

    public static SCPVector vectorDivided(SCPVector a, SCPVector b){
        return new SCPVector(a.x/b.x,a.y/b.y,a.z/b.z);
    }

    public static SCPVector vectorFloatSub(SCPVector a, double b){
        return new SCPVector(a.x-b,a.y-b,a.z-b);
    }

    public static SCPVector vectorFloatAdd(SCPVector a, double b){
        return new SCPVector(a.x+b,a.y+b,a.z+b);
    }

    public static SCPVector vectorFloatMulti(SCPVector a, double d){
        return new SCPVector(a.x*d,a.y*d,a.z*d);
    }


    public static SCPVector vectorFloatDivided(SCPVector a, double b){
        return new SCPVector(a.x/b,a.y/b,a.z/b);
    }

    public static SCPVector vectorFloatDivided(double a, SCPVector b){
        return new SCPVector(a/b.x,a/b.y,a/b.z);
    }

    public static SCPVector vectorFloatSub(double a, SCPVector b){
        return new SCPVector(a-b.x,a-b.y,a-b.z);
    }

    public double getMagnitude(){
        return Math.sqrt(sqrMagnitude);
    }


    public SCPVector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SCPVector vector = (SCPVector) o;
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
