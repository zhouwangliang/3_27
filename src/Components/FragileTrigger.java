package Components;

import java.awt.Color;
import java.awt.Graphics;

import Depends.CommonVariable;
import Prefabs.Prefab;
import UI.MapRender;

public class FragileTrigger extends Trigger {
	
	private int HP=CommonVariable.TriggerHP;
	public FragileTrigger(Prefab prefab) {
		super(prefab);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void ActionAfterCollision() {
		int s=Integer.parseInt(this.colorString);
		this.HP-=1;
		int temp=(int) (Math.random()*3);
		if(temp==0)
			s+=10;
		else if(temp==1)
			s+=10000;
		else if(temp==2)
			s+=10000000;
		//System.out.println(Integer.toString(s));
		this.colorString=Integer.toString(s);
		if(this.HP<=0) {
			this.prefab.Destory();
		}
		//this.timer.stop();
	
	}
	@Override
	protected void ActionAfterAttraction() {
		
	}
	@Override
	protected void extraDraw(Graphics g) {
		int WorldCordinateX=(int)(this.x+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX);
		int WorldCordinateY=(int)(this.y+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY);
		g.drawString(Integer.toString(this.HP),WorldCordinateX-(int)(this.Radius.value/2), WorldCordinateY-(int)(this.Radius.value/2)+10);
	}
	public void setHp(int HP) {
		this.HP=HP;
	}
	public int getHp() {
		return this.HP;
	}
}
