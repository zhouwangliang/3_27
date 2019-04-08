package Depends;

import java.io.PrintWriter;
import java.util.concurrent.LinkedBlockingQueue;

//Message send from server to each client.
public interface Transimitable {
	public void sendMessage(LinkedBlockingQueue<String> LBQ,String s) throws InterruptedException ;
}
