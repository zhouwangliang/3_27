package Components;

import Prefabs.Prefab;
import UI.MapRender;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import Depends.CommonVariable;
import Depends.Drawable;
import Depends.ReferencableDouble;
import Depends.Vector;
public class Trigger extends Component implements Drawable{
	private Color color=CommonVariable.TriggerColor;
	private String colorString=CommonVariable.TriggerColorString;//Decoded to color,used to transmits info.
	private ReferencableDouble Radius=new ReferencableDouble(CommonVariable.TriggerRadius);
	private double x=CommonVariable.TriggerX;
	private double y=CommonVariable.TriggerY;
	private Vector Velocity=null;
	

	public Trigger(Prefab prefab) {
		// TODO Auto-generated constructor stub
		super(prefab);
		MapRender.drawable.add(this);
		this.timer=new Timer(CommonVariable.ComponentTimerCalculateRate,move());
		this.Velocity=new Vector(0,0);
		
	}
	public void Destroy() {
		MapRender.drawable.remove(this);
		super.Destroy();
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
		//System.out.println("Velocity:"+Velocity.x+" "+Velocity.y);
		this.Velocity.x+=Acceleration.x;
		this.Velocity.y+=Acceleration.y;
		//System.out.println("Velocity:"+Velocity.x+" "+Velocity.y);
	}
	
	private ActionListener move() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				x+=Velocity.x*CommonVariable.ComponentTimerCalculateRate/1000;
				y+=Velocity.y*CommonVariable.ComponentTimerCalculateRate/1000;
				System.out.println("Velocity:"+Velocity.x+" "+Velocity.y+" "+x+" "+y+" "+Radius.value);
				if(x-Radius.value/2<CommonVariable.DynamicMapMinX+CommonVariable.MapBorderWidth) {
					Velocity=Vector.getReflectioinRay(Velocity, new Vector(1,0));
				}else if(x+Radius.value/2>CommonVariable.DynamicMapMaxX-CommonVariable.MapBorderWidth){
					Velocity=Vector.getReflectioinRay(Velocity, new Vector(-1,0));
				}
				if(y-Radius.value/2<CommonVariable.DynamicMapMinY+CommonVariable.MapBorderWidth) {
					Velocity=Vector.getReflectioinRay(Velocity, new Vector(0,1));
				}else if(y+Radius.value/2>CommonVariable.DynamicMapMaxY-CommonVariable.MapBorderWidth){
					Velocity=Vector.getReflectioinRay(Velocity, new Vector(0,-1));
				}
			}
		};
	}
	
	
	//get and set
	public void setRadius(double Radius) {
		this.Radius.value=Radius;
	}
	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
		int WorldCordinateX=(int)(this.x+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX);
		int WorldCordinateY=(int)(this.y+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY);
		g.fillOval(WorldCordinateX-(int)(this.Radius.value/2), WorldCordinateY-(int)(this.Radius.value/2), (int)this.Radius.value, (int)this.Radius.value);
	}
	@Override
	public Color getColor() {
		return this.color;
	}
	public ReferencableDouble getRadius() {
		return this.Radius;
	}
}
