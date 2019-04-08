package Prefabs;

import java.util.concurrent.LinkedBlockingQueue;

import Components.FragileTrigger;
import Depends.SpecialEffect;
import Depends.Vector;


public class Sphere extends Prefab {
	FragileTrigger trigger=null;

	public Sphere() {
		// TODO Auto-generated constructor stub
		super();
		this.trigger=new FragileTrigger(this);
		this.components.offer(this.trigger);
	}
	public void setRadius(double Radius) {
		SpecialEffect.SmoothChanging(trigger.getRadius(), Radius, 2000);
	}
	public void setPosition(double x,double y) {
		this.trigger.setPosition(x, y);
	}
	public void addAcceleration(Vector v) {
		this.trigger.addAcceleration(v);
	}
	public void setHp(int HP) {
		this.trigger.setHp(HP);
	}
	@Override
	public void sendMessage(LinkedBlockingQueue<String> LBQ,String s) throws InterruptedException {
		//[Sphere|CordinateX,CordinateY,Radius,ColorString,HP]
			LBQ.put("[Sphere|"+(int)this.trigger.getX()+","+(int)this.trigger.getY()+","+this.trigger.getRadius()+","+this.trigger.getColorString()+","+this.trigger.getHp()+"]");
	}
	@Override
	public void Destory() {
		super.Destory();
		
	}
}
