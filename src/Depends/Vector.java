package Depends;

import Depends.Vector;

public class Vector {
	//These variable needn't getter and setter,cause the change of them will not disturb system drastically.
	public double x;
	public double y;
	public Vector(double x,double y) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
	}
	
	
	//This function represents the vector  from v1 to v2
	public Vector(Vector v1,Vector v2) {
		this.x=v1.x-v2.x;
		this.y=v1.y-v2.y;
	}
	
	public static Vector getReflectioinRay(Vector IncomingRay,Vector Normal) {
		double angle1=Vector.getAngle(IncomingRay);
		double angle2=Vector.getAngle(Normal);
		double reflectAngle=angle2-(angle1-Math.PI-angle2);
		return new Vector(IncomingRay.getMagnitude()*Math.cos(reflectAngle),IncomingRay.getMagnitude()*Math.sin(reflectAngle));
	}
	
	
	//get and set
	
	
	public static double getCosAngle(Vector vector1,Vector vector2) {
		return (vector1.x*vector2.x+vector1.y*vector2.y)/vector1.getMagnitude()/vector2.getMagnitude();
	}
	public static double getSinAngle(Vector vector1,Vector vector2) {
		double angle1=Vector.getAngle(vector1);
		double angle2=Vector.getAngle(vector2);
		double cos=Vector.getCosAngle(vector1, vector2);
		double sin=Math.sqrt(1-cos*cos);
		if(Math.abs(angle1-angle2)>Math.PI) {
			sin=-1*sin;
		}
		return sin;
	}
	public double getMagnitude() {
		return Math.sqrt(x*x+y*y);
	}
	public Vector getUnitVector() {
		return new Vector(x/this.getMagnitude(),y/this.getMagnitude());
	}
	public Vector getReversedUnitVector() {
		return new Vector(x/this.getMagnitude()*-1,y/this.getMagnitude()*-1);
	}
	public static double getAngle(Vector vector) {
		double cos=vector.x/vector.getMagnitude();
		double sin=vector.y/vector.getMagnitude();
		double angle=Math.acos(cos);
		if(sin<0) {
			angle=2*Math.PI-angle;
		}
		return angle;
	}

}
