package Components;

import Prefabs.Prefab;
import UI.MapRender;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;
import Depends.CommonVariable;
import Depends.Drawable;
import Depends.ReferencableDouble;
import Depends.Vector;
public class Trigger extends Component implements Drawable{
	public static ArrayList<Trigger> triggers=new ArrayList<Trigger>();
	public boolean fill=true;
	public boolean attractOrRepel=true;//true is attract
	public double attractForce=CommonVariable.AttractionForce;
	protected String colorString=CommonVariable.TriggerColorString;//Decoded to color,used to transmits info.
	protected ReferencableDouble Radius=new ReferencableDouble(CommonVariable.TriggerRadius);
	protected double x=CommonVariable.TriggerX;
	protected double y=CommonVariable.TriggerY;
	public Vector Velocity=null;
	protected double Mass=CommonVariable.TriggerMass;
	private Trigger thisTrigger;
	public Trigger(Prefab prefab) {
		// TODO Auto-generated constructor stub
		super(prefab);
		MapRender.drawable.add(this);
		triggers.add(this);
		this.timer=new Timer(CommonVariable.ComponentTimerCalculateRate,move());
		this.Velocity=new Vector(0,0);
		this.thisTrigger=this;
		
	}
	public void Destroy() {
		super.Destroy();
		MapRender.drawable.remove(this);
		triggers.remove(this);
		
	}

	
	public static boolean PreRequiredComponent(Prefab prefab) {
		//Introduction:
		//		Some component need the support from others,
		//	if their requirement are not satisfied,
		//	the they will not be added to the prefab.
		//Reason for override:
		//		Each component require different components' support.
		
		return true;
	}
	
	
	
	public void addAcceleration(Vector Acceleration) {
		this.Velocity.x+=Acceleration.x;
		this.Velocity.y+=Acceleration.y;
	}
	
	private ActionListener move() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				simpleMove();
				if(Velocity.getMagnitude()>0) {
					appendFriction();
				}
				
				EnableCollisionOrAttraction(attractOrRepel);
				
			}
		};
	}
	protected void ActionAfterCollision() {
		
	}
	protected void ActionAfterAttraction() {
		
	}
	private void EnableCollisionOrAttraction(boolean flag) {
		//Calculate the Impact by other sphere
		Trigger t;
		double dis;
		double radius;
		Vector vec;
		double forceMagnitude;
		double radiusDivideDis;
		for(int i=0;i<triggers.size();i++) {
			t=triggers.get(i);
			if(thisTrigger!=triggers.get(i)) {
				
				dis=Math.sqrt((t.x-x)*(t.x-x)+(t.y-y)*(t.y-y));
				radius=Radius.value/2+t.getRadius().value/2;
				radiusDivideDis=radius/dis;
				if(dis<=radius) {
					
					vec=new Vector(t.getX()-x,t.getY()-y).getUnitVector();
					//System.out.println(vec.x+" "+vec.y);
					//Repel other triggers away from it's center;
					if(attractOrRepel) {
						if(t.getAttract()) {//target is not a blackhole
							
							if(Velocity.getMagnitude()>0.1) {
								/*
								forceMagnitude=Vector.getCosAngle(Velocity, vec)*Velocity.getMagnitude()*0.8;
								vec.x=vec.x*forceMagnitude*Mass/t.Mass;
								vec.y=vec.y*forceMagnitude*Mass/t.Mass;
						
								//addAcceleration(new Vector(-1*vec.x,-1*vec.y));
								
								t.Velocity.x+=vec.x;
								t.Velocity.y+=vec.y;
								Velocity.x=Velocity.x-vec.x;
								Velocity.y=Velocity.y-vec.y;
								*/
								if(dis<radius*0.9) {
									Velocity.x+=vec.x/vec.getMagnitude()*-10*radiusDivideDis;
									Velocity.y+=vec.y/vec.getMagnitude()*-10*radiusDivideDis;
									t.Velocity.x+=vec.x/vec.getMagnitude()*10*radiusDivideDis;
									t.Velocity.y+=vec.y/vec.getMagnitude()*10*radiusDivideDis;
								}
								
								vec.x=vec.x*Velocity.getMagnitude()/2;
								vec.y=vec.y*Velocity.getMagnitude()/2;
								t.addAcceleration(new Vector(vec.x, vec.y));
								Velocity.x=Velocity.x/2-vec.x;
								Velocity.y=Velocity.y/2-vec.y;
								ActionAfterCollision();
							}
							
						}
					}else {//Attract this trigger to it's center;
						if(dis>1&&t.getAttract()) {
							t.addAcceleration(new Vector(vec.x*-1*attractForce,vec.y*-1*attractForce));
							ActionAfterAttraction();
						}
					}
					
				}
				
			}
			t=null;
		}
	}
	private void appendFriction() {
		//Calculate the influence imposed by friction
		double frictionForce=Mass*CommonVariable.FrictionRate*CommonVariable.ComponentTimerCalculateRate/1000;
		if(Velocity.getMagnitude()>frictionForce) {
			Velocity.x-=Velocity.getUnitVector().x*frictionForce;
			Velocity.y-=Velocity.getUnitVector().y*frictionForce;
		}else {
			Velocity.x=0;
			Velocity.y=0;
		}
	}
	private void simpleMove() {
		//Calculate the influence imposed by velocity.
		x+=Velocity.x*CommonVariable.ComponentTimerCalculateRate/1000;
		y+=Velocity.y*CommonVariable.ComponentTimerCalculateRate/1000;
		//System.out.println("Velocity:"+Velocity.x+" "+Velocity.y+" "+x+" "+y+" "+Radius.value);
		
		/*
		//Limit the activating range of trigger.
		if(x-Radius.value/2<CommonVariable.DynamicMapMinX+CommonVariable.MapBorderWidth) {
			Velocity=Vector.getReflectioinRay(Velocity, new Vector(1,0));
		}else if(x+Radius.value/2>CommonVariable.DynamicMapMaxX-CommonVariable.MapBorderWidth){
			Velocity=Vector.getReflectioinRay(Velocity, new Vector(-1,0));
		}
		if(y-Radius.value/2<CommonVariable.DynamicMapMinY+CommonVariable.MapBorderWidth) {
			Velocity=Vector.getReflectioinRay(Velocity, new Vector(0,1));
		}else if(y+Radius.value/2>CommonVariable.DynamicMapMaxY-CommonVariable.MapBorderWidth){
			Velocity=Vector.getReflectioinRay(Velocity, new Vector(0,-1));
		}*/
		//Calculate the influence imposed by velocity.
		x+=Velocity.x*CommonVariable.ComponentTimerCalculateRate/1000;
		y+=Velocity.y*CommonVariable.ComponentTimerCalculateRate/1000;
		//Limit the activating range of trigger.
		double forceFromBorder=(double)CommonVariable.MapChangingRate/CommonVariable.ComponentTimerCalculateRate;
		if(x-Radius.value/2<CommonVariable.DynamicMapMinX.value+CommonVariable.MapBorderWidth) {
				Velocity.x=Math.abs(Velocity.x);
				if(Velocity.x*-1<forceFromBorder+0.1) {
					Velocity.x+=forceFromBorder*10;
				}
		}else if(x+Radius.value/2>CommonVariable.DynamicMapMaxX.value-CommonVariable.MapBorderWidth){
				Velocity.x=Math.abs(Velocity.x)*-1-forceFromBorder;
				if(Velocity.x<forceFromBorder+0.1) {
					Velocity.x-=forceFromBorder*10;
				}
		}
		if(y-Radius.value/2<CommonVariable.DynamicMapMinY.value+CommonVariable.MapBorderWidth) {
			Velocity.y=Math.abs(Velocity.y)+forceFromBorder;
			if(Velocity.y*-1<forceFromBorder+0.1) {
				Velocity.y+=forceFromBorder*10;
			}
		}else if(y+Radius.value/2>CommonVariable.DynamicMapMaxY.value-CommonVariable.MapBorderWidth){
			Velocity.y=Math.abs(Velocity.y)*-1-forceFromBorder;
			if(Velocity.y<forceFromBorder+0.1) {
				Velocity.y-=forceFromBorder*10;
			}
		}
	}
	//get and set
	public void setRadius(double Radius) {
		this.Radius.value=Radius;
	}
	protected void extraDraw(Graphics g) {
		
	}
	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
		int WorldCordinateX=(int)(this.x+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX);
		int WorldCordinateY=(int)(this.y+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY);
		if(fill)
			g.fillOval(WorldCordinateX-(int)(this.Radius.value/2), WorldCordinateY-(int)(this.Radius.value/2), (int)this.Radius.value, (int)this.Radius.value);
		else
			g.drawOval(WorldCordinateX-(int)(this.Radius.value/2), WorldCordinateY-(int)(this.Radius.value/2), (int)this.Radius.value, (int)this.Radius.value);
		g.setColor(Color.black);
		g.drawLine((int)(WorldCordinateX), (int)(WorldCordinateY), (int)(WorldCordinateX+this.Velocity.x),(int)(WorldCordinateY+this.Velocity.y));
		extraDraw(g);
		//System.out.println(this.Velocity.y);
	}
	@Override
	public Color getColor() {
		return Color.decode(this.colorString);
	}
	public String getColorString() {
		return this.colorString;
	}
	public ReferencableDouble getRadius() {
		return this.Radius;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double getMass() {
		return this.Mass;
	}
	public void setPosition(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public boolean getAttract() {
		return this.attractOrRepel;
	}
}
