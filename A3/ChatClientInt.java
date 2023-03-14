import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for the client
 */
public interface ChatClientInt extends Remote {
    // Broadcast messages to the clients from the client side
    void broadcast(String result, ChatClientInt clientInt) throws RemoteException;

    // Prints all available options to the client
    String showOptions() throws RemoteException;

    // Prints all available rooms to the client
    void showRooms() throws RemoteException;

    // Lets clients to join another room
    void joinRoom(int roomNum) throws RemoteException;

    // Get the client's name
    String getName() throws RemoteException;
}