package UI;

import java.awt.Panel;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import Components.Trigger;
import Depends.CommonVariable;
import Depends.SpecialEffect;
import Depends.Vector;
import Prefabs.Prefab;

public class GameWindow extends JFrame{
	private MapRender mapRender;
	private Menu menu;
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
		Trigger t=new Trigger(new Prefab());
		t.setEnable(true);
		t.addAcceleration(new Vector(-30,-30));
		//SpecialEffect.SmoothChanging(t.getRadius(), 100, 3000);
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
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
				System.out.println((start.x-end.x)+" "+(start.y-end.y));
			}
			
		};
	}

}
