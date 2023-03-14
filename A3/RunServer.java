import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Starts the server
 */
public class RunServer {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        ChatServerInt server = new Server();
        Registry registry = LocateRegistry.getRegistry();
        registry.bind("Server", server);

        System.out.println("Server has started...");
    }
}
