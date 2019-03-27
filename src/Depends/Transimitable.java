package Depends;


//Message send from server to each client.
public interface Transimitable {
	public void sendMessage(String messageName,String Content);
}
