package Components;

import java.util.ArrayList;

import javax.swing.Timer;

import Depends.CommonVariable;
import Prefabs.Prefab;

public class Component {
	public static ArrayList<Component> components=new ArrayList<Component>();
	
	protected int CalculateRate=CommonVariable.ComponentTimerCalculateRate;
	protected boolean enable=false;
	protected Timer timer=null;
	protected Prefab prefab;
	public Component(Prefab prefab) {
		// TODO Auto-generated constructor stub
		this.prefab=prefab;
		components.add(this);
		
	}
	public void Destroy() {
		this.setEnable(false);
		components.remove(this);
	}
	
	
	
	
	
	
	
	
	//*******************Override in subclass****************************down
	
	
	public static boolean PreRequiredComponent(Prefab prefab) {
		//Introduction:
		//		Some component need the support from others,
		//	if their requirement are not satisfied,
		//	the they will not be added to the prefab.
		//Reason for override:
		//		Each component require different components' support.
		
		return true;
	}
	
	
	
	
	//*******************Override in subclass****************************up
	
	public void setEnable(boolean flag) {
		if(flag) {
			if(timer!=null&&!this.timer.isRunning()) {
				this.timer.start();
			}
			this.enable=true;
		}else {
			if(timer!=null&&this.timer.isRunning()) {
				this.timer.stop();
			}
			this.enable=false;
		}
	}
	public boolean getEnable() {
		return this.enable;
	}
}
