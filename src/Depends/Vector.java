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
		double magnitude=Math.sqrt(IncomingRay.x*IncomingRay.x+IncomingRay.y*IncomingRay.y);
		double cos1=Normal.x/Math.sqrt(Normal.x*Normal.x+Normal.y*Normal.y);
		double cos2=(Normal.x*IncomingRay.x+Normal.y*IncomingRay.y)/(Math.sqrt(Normal.x*Normal.x+Normal.y*Normal.y)*magnitude);
		double sin1=Math.sqrt(1-cos1*cos1);
		if(Normal.y<0)
			sin1*=-1;
		double sin2=Math.sqrt(1-cos2*cos2);
		double sin3=(sin1*cos2+cos1*sin2)*-1;
		double cos3=(cos1*cos2-sin1*sin2)*-1;
		
		//System.out.println("cos1,cos2,sin1,sin2 "+cos1+" "+cos2+" "+sin1+" "+sin2 );
		//System.out.println(Math.acos(cos1)*180/3.14+" "+Math.acos(cos2)*180/3.14+" "+Math.asin(sin1)*180/3.14+" "+Math.asin(sin2)*180/3.14);
		Vector result=new Vector(magnitude*cos3,magnitude*sin3);
		return result;
	}
	
	
	//get and set
	
	
	public static double getCosAngle(Vector vector1,Vector vector2) {
		return (vector1.x*vector2.x+vector1.y*vector2.y)/Math.sqrt(vector1.x*vector1.x+vector1.y*vector1.y)/Math.sqrt(vector2.x*vector2.x+vector2.y*vector2.y);
	}
	public static double getSinAngle(Vector vector1,Vector vector2) {
		double cos=getCosAngle(vector1,vector2);
		return Math.sqrt(1-cos*cos);
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

}
