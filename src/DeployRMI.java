import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DeployRMI {
	
	public static void main(String[] args) throws RemoteException {
		 Registry r = LocateRegistry.createRegistry(12342);
		 r.rebind("verifyWinner", new DeclareWinner());
		 System.out.println("Service is deployed");
	}

}
