package Depends;

import java.awt.Color;

public class CommonVariable {
	
	public static int WindowWidth=500;
	public static int WindowHeight=500;
	public static int MapWidth=1000;
	public static int MapHeight=1000;
	public static int InitialMapCenterX=250;
	public static int InitialMapCenterY=250;
	public static int MapMovingSensitive=5;
	
	public static int DynamicMapMinX=0;
	public static int DynamicMapMaxX=MapWidth;
	public static int DynamicMapMinY=0;
	public static int DynamicMapMaxY=MapHeight;
	public static int MapBorderWidth=20;
	
	public static int ComponentTimerCalculateRate=10;//Milliseconds.
	public static boolean TriggerFill=true;
	public static double TriggerRadius=50;
	public static double TriggerX=DynamicMapMinX+TriggerRadius/2.0+100;
	public static double TriggerY=DynamicMapMinY+TriggerRadius/2.0+100;

	public static String TriggerColorString="000000000";
	public static Color TriggerColor=Color.decode(TriggerColorString);
}
