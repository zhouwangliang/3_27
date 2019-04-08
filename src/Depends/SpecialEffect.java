package Depends;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.Timer;

public class SpecialEffect {
	
	public static void SmoothChanging(ReferencableDouble origin,double target,int duration) {
		new smoothChangingFunction(origin,target,duration);
	}
	public static void SmoothRangdomChangeColor(ReferencableString origin,int rate) {
		new smoothRandomChangeColorFunction(origin,1000);
	}

}
class smoothChangingFunction{
	private Timer timer=null;
	private ReferencableDouble origin;
	private double target;
	int duration;
	double delta;
	public smoothChangingFunction(ReferencableDouble origin,double target,int duration) {
		this.origin=origin;
		this.target=target;
		this.duration=duration;
		delta=(target-origin.value)/duration*CommonVariable.ComponentTimerCalculateRate;
		this.timer=new Timer(CommonVariable.ComponentTimerCalculateRate,smoothChangingListener());
		this.timer.start();
	}
	private ActionListener smoothChangingListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				origin.value+=delta;
				//System.out.println("radius:"+origin.value);
				if(Math.abs(origin.value-target)<=Math.abs(delta)) {
					timer.stop();
				}
			}
			
		};
	}
}
class smoothRandomChangeColorFunction{
	private Timer timer=null;
	private ReferencableString origin;
	private double rate;
	private int tempInt;
	private double red;
	private double green;
	private double blue;
	private String redString;
	private String greenString;
	private String blueString;
	public smoothRandomChangeColorFunction(ReferencableString origin,double rate) {
		this.origin=origin;
		this.rate=rate/1000;
		tempInt=Integer.parseInt(origin.value);
		//System.out.println("TempInt "+tempInt);
		red=tempInt/1000000;
		green=tempInt%1000000/1000;
		blue=tempInt%1000;
		redString=""+(int)red;
		greenString=""+(int)green;
		blueString=""+(int)blue;
		this.timer=new Timer(1000,smoothRandomChangeColorWhiterListener());
		timer.start();
	}
	private ActionListener smoothRandomChangeColorWhiterListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
				int flag=(int) (Math.random()*3);
				if(red>100&&green>100&&blue>100) {
					if(flag==0) {
						red-=rate;
						if(red<100) {
							redString=""+(int)red;
						}
					}else if(flag==1) {
						green-=rate;
						if(green<100) {
							greenString=""+(int)green;
						}
					}else if(flag==2) {
						blue-=rate;
						if(blue<100) {
							blueString=""+(int)blue;
						}
					}
					origin.value=redString+greenString+blueString;
					//System.out.println(origin.value+" "+redString+" "+greenString+" "+blueString+" "+rate);
				}else {
					timer.stop();
					timer=new Timer(1000,smoothRandomChangeColorDarkerListener());
					timer.start();
				}
			}
			
		};
	}
	private ActionListener smoothRandomChangeColorDarkerListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				int flag=(int) (Math.random()*3);
				if(red<200&&green<200&&blue<200) {
					if(flag==0) {
						red+=rate;
						redString=""+(int)red;
					}else if(flag==1) {
						green+=rate;
						greenString=""+(int)green;
					}else if(flag==2) {
						blue+=rate;
						blueString=""+(int)blue;
					}
					origin.value=redString+greenString+blueString;
					//System.out.println(origin.value+" "+red+" "+green+" "+blue+" "+rate);
				}else {
					timer.stop();
					timer=new Timer(CommonVariable.FreshingRate,smoothRandomChangeColorWhiterListener());
					timer.start();
				}
			}
			
		};
		
	}
}