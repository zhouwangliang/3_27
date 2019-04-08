package Prefabs;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import Components.Component;
import Components.Trigger;
import Depends.Transimitable;

public class Prefab implements Transimitable{
	public static ArrayList<Prefab> prefabs=new ArrayList<Prefab>();
	private static ArrayList<Integer> UsedCode=new ArrayList<Integer>();
	private static LinkedList<Integer> ReleasedCode=new LinkedList<Integer>(); 
	private static int NextCode=1;
	
	public final int Code;
	protected LinkedList<Component> components=new LinkedList<Component>(); 
	
	public Prefab() {
		// TODO Auto-generated constructor stub
		prefabs.add(this);
		if(ReleasedCode.isEmpty()) {
			this.Code=NextCode++;
			UsedCode.add(this.Code);
		}else {
			this.Code=ReleasedCode.poll();
			UsedCode.add(this.Code);
		}
		
	}
	public void Destory() {
		Component component=null;
		while(!components.isEmpty()) {
			component=components.pollLast();
			System.out.println("Code"+this.Code);
			System.out.println(component.getClass());
			component.Destroy();
		}
		prefabs.remove(this);
		UsedCode.remove((Integer)this.Code);
		ReleasedCode.offer(this.Code);
		
	}
	
	
	//Components operations
	public Component FindComponentByName(String Name) {
		switch(Name) {
		case "Trigger":
			for(int i=0;i<components.size();i++) {
				if(components.get(i) instanceof Trigger) {
					return components.get(i);
				}
			}
			break;
		
		}
		
		return null;
	}
	public void ActivatePrefab(boolean flag) {
		if(flag) {
			for(int i=0;i<components.size();i++) {
				components.get(i).setEnable(true);
			}
		}
		else {
			for(int i=components.size()-1;i>=0;i--) {
				components.get(i).setEnable(false);
			}
		}
	}
	@Override
	public void sendMessage(LinkedBlockingQueue<String> LBQ,String s) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

}
