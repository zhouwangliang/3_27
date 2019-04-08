package Prefabs;

import java.util.concurrent.LinkedBlockingQueue;

import Components.Trigger;
import Depends.SpecialEffect;

public class BlackHole extends Prefab {
	private Trigger t;
	public BlackHole() {
		super();
		// TODO Auto-generated constructor stub
		t=new Trigger(this);
		this.components.offer(t);
		t.attractOrRepel=false;
		t.fill=false;
	}
	public void attractForce(double force) {
		this.t.attractForce=force;
	}
	public void setRadius(double Radius) {
		SpecialEffect.SmoothChanging(t.getRadius(), Radius, 2000);
	}
	public void setPosition(double x,double y) {
		this.t.setPosition(x, y);
	}
	@Override
	public void sendMessage(LinkedBlockingQueue<String> LBQ,String s) throws InterruptedException {
		// [BlackHole|CordinateX,CordinateY,Radius]
		LBQ.put("[BlackHole|"+(int)this.t.getX()+","+(int)this.t.getY()+","+this.t.getRadius()+"]");
	}
	@Override
	public void Destory() {
		super.Destory();
	}
}
