package UI;

import java.awt.Panel;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;

import Components.FragileTrigger;
import Components.Trigger;
import Depends.CommonVariable;
import Depends.SpecialEffect;
import Depends.Transimitable;
import Depends.Vector;
import Prefabs.BlackHole;
import Prefabs.Prefab;
import Prefabs.Sphere;

public class GameWindow extends JFrame implements Transimitable{
	public static MapRender mapRender;
	public static Menu menu;
	public GameWindow() {
		mapRender=new MapRender();
		this.setSize(CommonVariable.WindowWidth,CommonVariable.WindowHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mapRender);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.addMouseListener(mouseController());
		this.addKeyListener(keyController());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameWindow();
		/*
		Sphere s=new Sphere();
		s2=new Sphere();
		((Trigger)(s2.FindComponentByName("Trigger"))).setPosition(200, 200);
		Sphere s3=new Sphere();
		((Trigger)(s3.FindComponentByName("Trigger"))).setPosition(200, 300);
		((Trigger)(s.FindComponentByName("Trigger"))).addAcceleration(new Vector(300,400));
		((Trigger)(s2.FindComponentByName("Trigger"))).addAcceleration(new Vector(300,400));
		((Trigger)(s3.FindComponentByName("Trigger"))).addAcceleration(new Vector(300,400));
		s.ActivatePrefab(true);
		s2.ActivatePrefab(true);
		s3.ActivatePrefab(true);
		SpecialEffect.SmoothChanging(((Trigger)(s.FindComponentByName("Trigger"))).getRadius(), 100, 3000);
		GameWindow.mapRender.lookAt((Trigger)s.FindComponentByName("Trigger"));
		GameWindow.mapRender.ChangeBorder(300, 500, 300, 500);
		BlackHole b=new BlackHole();
		((Trigger)b.FindComponentByName("Trigger")).setPosition(400, 400);
		((Trigger)b.FindComponentByName("Trigger")).setRadius(300);
		*/
		Sphere s=null;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				s=new Sphere();	
				s.setPosition(50*i+i+50, 50*j+j+50);
				//s.ActivatePrefab(true);
				s.addAcceleration(new Vector(-100,0));
			}
		}
		BlackHole b=null;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				b=new BlackHole();
				b.setRadius(150);
				b.setPosition(100*i+100*i+100, 100*j+100*j+100);
				//b.ActivatePrefab(true);
			}
		}
		GameWindow.mapRender.ChangeBorder(500, 1000, 500, 1000);
	}
	
	private KeyListener keyController() {
		return new KeyListener() {

			@Override
			public void keyPressed(KeyEvent k) {
				// TODO Auto-generated method stub
				if(k.getKeyCode()==KeyEvent.VK_W) {
					if(mapRender.CurrentCenterY>CommonVariable.WindowHeight/2)
						mapRender.CurrentCenterY-=CommonVariable.MapMovingSensitive;
				}else if(k.getKeyCode()==KeyEvent.VK_S) {
					if(mapRender.CurrentCenterY<CommonVariable.MapHeight-CommonVariable.WindowHeight/2)
						mapRender.CurrentCenterY+=CommonVariable.MapMovingSensitive;
				}else if(k.getKeyCode()==KeyEvent.VK_A) {
					if(mapRender.CurrentCenterX>CommonVariable.WindowWidth/2)
						mapRender.CurrentCenterX-=CommonVariable.MapMovingSensitive;
				}else if(k.getKeyCode()==KeyEvent.VK_D) {
					if(mapRender.CurrentCenterX<CommonVariable.MapWidth-CommonVariable.WindowWidth/2)
						mapRender.CurrentCenterX+=CommonVariable.MapMovingSensitive;
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent k) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	private MouseListener mouseController() {
		return new MouseListener() {
			Point start;
			Point end;
			boolean state=false;
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Click info");
				for(int i=0;i<Prefab.prefabs.size();i++) {
					Prefab.prefabs.get(i).ActivatePrefab(state);
					System.out.println(((Trigger)Prefab.prefabs.get(i).FindComponentByName("Trigger")).Velocity.x+" "+((Trigger)Prefab.prefabs.get(i).FindComponentByName("Trigger")).Velocity.y);
				}
				System.out.println("Clicked");
				state=!state;
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				this.start=arg0.getPoint();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				this.end=arg0.getPoint();
				//System.out.println((start.x-end.x)+" "+(start.y-end.y));
				for(int i=0;i<Prefab.prefabs.size();i++) {
					if(Prefab.prefabs.get(i) instanceof Sphere) {
						((Sphere)Prefab.prefabs.get(i)).addAcceleration(new Vector(end.x-start.x,end.y-start.y));
					}
				}
			}
			
		};
	}

	@Override
	public void sendMessage(LinkedBlockingQueue<String> LBQ,String s) throws InterruptedException {
		// TODO Auto-generated method stub
		LBQ.put(s);
	}

}
