package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Components.Trigger;
import Depends.Vector;
import Prefabs.Prefab;

public class test {
	
	public test() {
		Vector vector1=new Vector(0,100);
		Vector vector2=new Vector(0,100);
		double cos=Vector.getCosAngle(vector1, vector2);
		System.out.println(cos);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new test();
	}
	
}

