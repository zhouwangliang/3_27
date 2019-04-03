package Depends;

import java.awt.Color;
import java.awt.Graphics;

public interface Drawable {
	public Color getColor();
	public void Draw(Graphics g);
	//All things draw into the map should substract the CurrentCenterX and CurrentCenterY.
}
