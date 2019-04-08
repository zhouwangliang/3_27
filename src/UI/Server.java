package UI;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
	//Info from client must transimit to client
	public static int PORT=9999;
	
	private static boolean Running=false;
	private static ServerSocket server=null;
	public static LinkedBlockingQueue<String> LBQBroadCast=new LinkedBlockingQueue<String>();
	private static LinkedBlockingQueue<String> LBQFromClients=new LinkedBlockingQueue<String>();
	
	private static ArrayList<Client> clients=new ArrayList<Client>();
	private ExecutorService mExecutorService=Executors.newCachedThreadPool();
	
	public Server() {
		try {
			server=new ServerSocket(PORT);
			
			//BroadCast Thread
			mExecutorService.execute(new Runnable() {
				@Override
				public void run() {
					String OutputMessage;
					try {
						while(true) {
							if((OutputMessage=LBQBroadCast.take())!=null) {
								for(int i=0;i<clients.size();i++){
									clients.get(i).printWriter.println(OutputMessage);
								}
							}
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			
			//Running Server Thread
			mExecutorService.execute(new Runnable() {
				Socket temp;
				Client client;
				@Override
				public void run() {
					Running=true;
					System.out.println("Server is Running");
					try {
						while(Running) {
							temp=server.accept();
							client=new Client(temp);
							clients.add(client);
							mExecutorService.execute(client);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
class Client implements Runnable{
	public PrintWriter printWriter=null;
	
	public Client(Socket s) {
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
