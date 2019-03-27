package Depends;

import java.awt.Graphics;

public interface Drawable {
	public void Draw(Graphics g);
	//All things draw into the map should substract the CurrentCenterX and CurrentCenterY.
}
