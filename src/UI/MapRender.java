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

import Depends.CommonVariable;
import Depends.Drawable;

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
		g.setColor(Color.black);
		g.drawString("CenterCoordination:"+CurrentCenterX+","+CurrentCenterY,0, 10);
		g.drawString("MapRange:"+"X("+CommonVariable.DynamicMapMinX+"-"+CommonVariable.DynamicMapMaxX+")"+" "+"Y("+CommonVariable.DynamicMapMinY+"-"+CommonVariable.DynamicMapMaxY+")", 200, 10);
		/*
		for(int i=0;i<drawable.size();i++) {
			drawable.get(i).Draw(g);
		}
		*/
		for(int i=0;i<drawable.size();i++) {
			g.setColor(drawable.get(i).getColor());
			drawable.get(i).Draw(g);
		}
		
		g.setColor(RandomColor());
		//Left border:
		g.fillRect(CommonVariable.DynamicMapMinX+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX, CommonVariable.DynamicMapMinY+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY, CommonVariable.MapBorderWidth, CommonVariable.DynamicMapMaxY-CommonVariable.DynamicMapMinY);
		//Right border:
		g.fillRect(CommonVariable.DynamicMapMaxX-CommonVariable.MapBorderWidth+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX, CommonVariable.DynamicMapMinY+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY,CommonVariable.MapBorderWidth,CommonVariable.DynamicMapMaxY-CommonVariable.DynamicMapMinY);
		//Top border:
		g.fillRect(CommonVariable.DynamicMapMinX+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX, CommonVariable.DynamicMapMinY+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY, CommonVariable.DynamicMapMaxX-CommonVariable.DynamicMapMinX, CommonVariable.MapBorderWidth);
		//Bottom border:
		g.fillRect(CommonVariable.DynamicMapMinX+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX, CommonVariable.DynamicMapMaxY-CommonVariable.MapBorderWidth+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY, CommonVariable.DynamicMapMaxX-CommonVariable.DynamicMapMinX, CommonVariable.MapBorderWidth);
		
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
	
	
	
	//static
	public static Color RandomColor() {
		String colorString="";
		int a;
		a=(int)(Math.random()*120+50);
		colorString+=a;
		a=(int)(Math.random()*120+50);
		colorString+=a;
		a=(int)(Math.random()*120+50);
		colorString+=a;
		return Color.decode(colorString);
	}
	
}
