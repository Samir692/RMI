import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	
	public static void main(String[] args) {
		
		 Object lock = new Object();
	      try{
	    	  ServerSocket ss = new ServerSocket(12348);
	    	  System.out.println("Server initcreateRegistry(12344);ialized");

	    	  while (true){
	            Socket s = ss.accept();
	            Socket s2 = ss.accept();
	            new Thread(new ServerThread(s, s2, lock)).start();
	    	  }

	      }catch(Exception e){
	        System.out.println(e);
	      }
		
	}
}


class ServerThread extends Thread{
	    Object lock;
	    Socket s1;
	    Socket s2;

	    public ServerThread(Socket s, Socket s2, Object lock){
	        this.lock = lock;
	        this.s1 = s;
	        this.s2 = s2;
	    }

	    public void run(){
	      try{
	    	  
			  
			  
			  ObjectOutputStream oos1 = new ObjectOutputStream(s1.getOutputStream());
	          ObjectInputStream ois1 = new ObjectInputStream (s1.getInputStream());
	    	  ObjectOutputStream oos2 = new ObjectOutputStream(s2.getOutputStream());
	          ObjectInputStream ois2 = new ObjectInputStream (s2.getInputStream());
	          
	          
	          Hand h1 = (Hand) ois1.readObject();
	          Hand h2 = (Hand) ois2.readObject();
	          
	          System.out.println("Hand1 = " + h1);

	          System.out.println("Hand2 = " + h2);
	          
	          
	          synchronized(lock)
	          {
		          oos1.writeObject(h2);
		          oos2.writeObject(h1);
	          }
	          
	          return;
	          
	        
	      }catch(Exception e){
	        System.out.println(e);
	      }
	}
}


