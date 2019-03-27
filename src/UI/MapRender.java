package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import Components.Trigger;
import Depends.CommonVariable;
import Depends.Drawable;
import Prefabs.Prefab;

public class MapRender extends JPanel{
	public static ArrayList<Drawable> drawable=new ArrayList<Drawable>();

	public static int CurrentCenterX=CommonVariable.InitialMapCenterX;
	public static int CurrentCenterY=CommonVariable.InitialMapCenterY;
	
	private Timer timer;
	public MapRender() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(CommonVariable.WindowWidth,CommonVariable.WindowHeight));
		this.setBorder(new LineBorder(Color.black));
		this.timer=new Timer(CommonVariable.ComponentTimerCalculateRate,refresh());
		this.timer.start();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*
		for(int i=0;i<drawable.size();i++) {
			drawable.get(i).Draw(g);
		}
		*/
		Trigger t=new Trigger(new Prefab());
		t.Draw(g);
		g.drawOval(250-CurrentCenterX, 250-CurrentCenterY, 1000, 1000);
	}
	private void DrawGameMapBorder(Graphics g) {
		
	}
	private ActionListener refresh() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				repaint();
			}
		};
	}
	
	
	//get and set
	
}
