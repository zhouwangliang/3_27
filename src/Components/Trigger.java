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
import Depends.Vector;
public class Trigger extends Component implements Drawable{
	private boolean fill=true;
	private Color color=CommonVariable.TriggerColor;
	private boolean isSphere=true;
	private double Radius=CommonVariable.TriggerRadius;
	private double Radius2=CommonVariable.TriggerRadius;
	private double x=CommonVariable.TriggerX;
	private double y=CommonVariable.TriggerY;
	private Vector Velocity=null;
	
	
	private double CurrentRadius;//for implements gradual transition.
	public Trigger(Prefab prefab) {
		// TODO Auto-generated constructor stub
		super(prefab);
		MapRender.drawable.add(this);
		this.timer=new Timer(CommonVariable.ComponentTimerCalculateRate,move());
		
	}
	public void Destroy() {
		MapRender.drawable.remove(this);
		super.Destroy();
	}

	
	
	
	private ActionListener move() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				x+=Velocity.x*CommonVariable.ComponentTimerCalculateRate/1000;
				y+=Velocity.y*CommonVariable.ComponentTimerCalculateRate/1000;
				
			}
		};
	}
	
	
	public static boolean PreRequiredComponent(Prefab prefab) {
		//This component requires nothing.
		return true;
	}
	//get and set
	public void setRadius(double Radius,double Radius2) {
		this.Radius=Radius;
		this.Radius2=Radius2;
	}
	@Override
	public void Draw(Graphics g) {
		// TODO Auto-generated method stub
		int WorldCordinateX=(int)(this.x+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX);
		int WorldCordinateY=(int)(this.y+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY);
		if(this.isSphere)
		{
			if(this.isSphere) {
				if(this.fill) {
					g.fillOval(WorldCordinateX-(int)(this.Radius/2), WorldCordinateY-(int)(this.Radius/2), (int)this.Radius, (int)this.Radius);
				}else {
					g.drawOval(WorldCordinateX-(int)(this.Radius/2), WorldCordinateY-(int)(this.Radius/2), (int)this.Radius, (int)this.Radius);
				}
			}else {
				if(this.fill) {
					g.fillRect(WorldCordinateX-(int)(this.Radius/2), WorldCordinateY-(int)(this.Radius2/2), (int)this.Radius, (int)this.Radius2);
				}else {
					g.drawRect(WorldCordinateX-(int)(this.Radius/2), WorldCordinateY-(int)(this.Radius2/2), (int)this.Radius, (int)this.Radius2);
				}
			}
		}
	}
}
