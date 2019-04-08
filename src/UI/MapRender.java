package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import Components.Trigger;
import Depends.CommonVariable;
import Depends.Drawable;
import Depends.ReferencableDouble;
import Depends.ReferencableString;
import Depends.SpecialEffect;
import Depends.Transimitable;
import Prefabs.Prefab;

public class MapRender extends JPanel implements Transimitable{
	public static ArrayList<Drawable> drawable=new ArrayList<Drawable>();
	public static ReferencableString borderColor=new ReferencableString("100100100");
	public static int CurrentCenterX=CommonVariable.InitialMapCenterX;
	public static int CurrentCenterY=CommonVariable.InitialMapCenterY;
	
	private Timer timer;
	public MapRender() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(CommonVariable.WindowWidth,CommonVariable.WindowHeight));
		this.setBorder(new LineBorder(Color.black));
		this.timer=new Timer(CommonVariable.FreshingRate,refresh());
		SpecialEffect.SmoothRangdomChangeColor(MapRender.borderColor,1000);
		this.timer.start();
	}
	public void ChangeBorder(int xmin,int xmax,int ymin,int ymax) {
		SpecialEffect.SmoothChanging(CommonVariable.DynamicMapMinX, xmin, (int)(1000/CommonVariable.MapChangingRate*(xmin-CommonVariable.DynamicMapMinX.value)));
		SpecialEffect.SmoothChanging(CommonVariable.DynamicMapMinY, ymin, (int)(1000/CommonVariable.MapChangingRate*(ymin-CommonVariable.DynamicMapMinY.value)));
		SpecialEffect.SmoothChanging(CommonVariable.DynamicMapMaxX, xmax, (int)(1000/CommonVariable.MapChangingRate*(CommonVariable.DynamicMapMaxX.value-xmax)));
		SpecialEffect.SmoothChanging(CommonVariable.DynamicMapMaxY, ymax, (int)(1000/CommonVariable.MapChangingRate*(CommonVariable.DynamicMapMaxY.value-ymax)));
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawString("CenterCoordination:"+CurrentCenterX+","+CurrentCenterY,0, 10);
		g.drawString("MapRange:"+"X("+(int)CommonVariable.DynamicMapMinX.value+"-"+(int)CommonVariable.DynamicMapMaxX.value+")"+" "+"Y("+(int)CommonVariable.DynamicMapMinY.value+"-"+(int)CommonVariable.DynamicMapMaxY.value+")", 200, 10);
		
		for(int i=0;i<drawable.size();i++) {
			g.setColor(drawable.get(i).getColor());
			drawable.get(i).Draw(g);
		}
		DrawGameMapBorder(g);
		
		
		System.out.println(Prefab.prefabs.size());
		
	}
	private void DrawGameMapBorder(Graphics g) {
		g.setColor(Color.decode(borderColor.value));
		//Left border:
		g.fillRect((int)CommonVariable.DynamicMapMinX.value+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX, (int)CommonVariable.DynamicMapMinY.value+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY, CommonVariable.MapBorderWidth, (int)CommonVariable.DynamicMapMaxY.value-(int)CommonVariable.DynamicMapMinY.value);
		//Right border:
		g.fillRect((int)CommonVariable.DynamicMapMaxX.value-CommonVariable.MapBorderWidth+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX, (int)CommonVariable.DynamicMapMinY.value+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY,CommonVariable.MapBorderWidth,(int)CommonVariable.DynamicMapMaxY.value-(int)CommonVariable.DynamicMapMinY.value);
		//Top border:
		g.fillRect((int)CommonVariable.DynamicMapMinX.value+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX, (int)CommonVariable.DynamicMapMinY.value+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY, (int)CommonVariable.DynamicMapMaxX.value-(int)CommonVariable.DynamicMapMinX.value, CommonVariable.MapBorderWidth);
		//Bottom border:
		g.fillRect((int)CommonVariable.DynamicMapMinX.value+CommonVariable.WindowWidth/2-MapRender.CurrentCenterX, (int)CommonVariable.DynamicMapMaxY.value-CommonVariable.MapBorderWidth+CommonVariable.WindowHeight/2-MapRender.CurrentCenterY, (int)CommonVariable.DynamicMapMaxX.value-(int)CommonVariable.DynamicMapMinX.value, CommonVariable.MapBorderWidth);
		
		
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
	
	public void lookAt(Trigger t) {
		this.CurrentCenterX=(int) t.getX();
		this.CurrentCenterY=(int) t.getY();
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
	@Override
	public void sendMessage(LinkedBlockingQueue<String> LBQ,String s) throws InterruptedException {
		//[MapRender|CurrentCenterX,CurrentCenterY,DynamicMapMinX,DynamicMapMaxX,DynamicMapMinY,DynamicMapMaxY,BorderColor]
		LBQ.put("[MapRender|"+this.CurrentCenterX+","+this.CurrentCenterY+","+(int)CommonVariable.DynamicMapMinX.value+","
				+(int)CommonVariable.DynamicMapMaxX.value+","+(int)CommonVariable.DynamicMapMinY.value+","+(int)CommonVariable.DynamicMapMaxY.value+","+this.borderColor.value+"]");
	}
}
