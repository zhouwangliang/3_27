package Depends;

import java.awt.Color;

public class CommonVariable {
	
	public static int WindowWidth=800;
	public static int WindowHeight=800;
	public static int MapWidth=1000;
	public static int MapHeight=1000;
	public static int InitialMapCenterX=WindowWidth/2;
	public static int InitialMapCenterY=WindowHeight/2;
	public static int MapMovingSensitive=20;
	
	public static ReferencableDouble DynamicMapMinX=new ReferencableDouble(0);
	public static ReferencableDouble DynamicMapMaxX=new ReferencableDouble(MapWidth);
	public static ReferencableDouble DynamicMapMinY=new ReferencableDouble(0);
	public static ReferencableDouble DynamicMapMaxY=new ReferencableDouble(MapHeight);
	public static int MapChangingRate=10;
	public static int MapBorderWidth=20;
	
	public static int FreshingRate=10;//Milliseconds
	public static int ComponentTimerCalculateRate=FreshingRate;//Milliseconds.
	
	
	public static boolean TriggerFill=true;
	public static double TriggerRadius=50;
	public static double TriggerX=DynamicMapMinX.value+TriggerRadius/2.0+100;
	public static double TriggerY=DynamicMapMinY.value+TriggerRadius/2.0+100;
	public static String TriggerColorString="000000000";

	public static double TriggerMass=50;
	public static double FrictionRate=0.5;
	public static double AttractionForce=10;
	
	public static int TriggerHP=10;
}
