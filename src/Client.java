import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	
	public static void main(String[] args) {
		
		try{
	          Socket s = new Socket("localhost", 12348);
	          System.out.println("Client initialized");
	          Hand hand1 = null;
	          Hand hand2 = null;
	          
	          
	          //PrintWriter print = new PrintWriter(s.getOutputStream());
	          //print.println("ready");
	          //print.flush();

	          while(true) {
		          ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		          ObjectInputStream ois = new ObjectInputStream (s.getInputStream());
		          
		          hand1 = new Hand();
		          
		          oos.writeObject(hand1);
		          
		          hand2 = (Hand) ois.readObject();
		          System.out.println("Client hand recived = " + hand2);
		          break;
		          
		          
	          
	          }

	          Registry registry = LocateRegistry.getRegistry("localhost", 12342);
	          
	          DeclareWinnerInterface reference = (DeclareWinnerInterface) registry.lookup("verifyWinner");
	          
	          System.out.println("Winner = " + reference.whoWon(hand1.getHand(), hand2.getHand()));
	          
	        }catch(Exception e){
	        	
	          System.out.println(e);
	}
	}

}
