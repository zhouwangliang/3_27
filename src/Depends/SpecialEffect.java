package Depends;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.Timer;

public class SpecialEffect {
	
	public static void SmoothChanging(ReferencableDouble origin,double target,int duration) {
		TimerTask timer=new TimerTask(origin,target,duration);
		timer.start();
	}

}
class smoothChangingListener implements TimerTask{
	private ReferencableDouble origin;
	private double target;
	int duration;
	double delta;
	public smoothChangingListener(ReferencableDouble origin,double target,int duration) {
		this.origin=origin;
		this.target=target;
		this.duration=duration;
		delta=(target-origin.value)/duration*CommonVariable.ComponentTimerCalculateRate;
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		origin.value+=delta;
		System.out.println("radius:"+origin.value);
		if(Math.abs(origin.value-target)<=delta) {
			//this
		}
	}
}