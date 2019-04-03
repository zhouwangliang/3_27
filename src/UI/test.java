package UI;

import Depends.Vector;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double a=100.;
		System.out.println(a);
		change(a);
		System.out.println(a);
	}
	public static void change(Double d) {
		d+=10;
	}
}
