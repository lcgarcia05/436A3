import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
    Remote interface for the server
 */
public interface ChatServerInt extends Remote {
    // Broadcasts messages to the current clients inside the chatroom
    void broadcastMessage(String message, ChatClientInt client) throws RemoteException;

    // Removes the chosen client from the list of current clients in the chatroom
    void unregisterClient(ChatClientInt clientToRemove) throws RemoteException;

    // Creates a chatroom
    void addRoom(String name, ChatClientInt client) throws RemoteException;

    // List all chatrooms created by the user/client
    ArrayList<Chatroom> listRooms() throws RemoteException;

    // Assigns a new client to a chatroom
    void addClient(int roomNum, ChatClientInt clientInt) throws RemoteException;

    // Retrieves all previous messages from the chatroom
    ArrayList<String> getMessageHistory(int num) throws RemoteException;
}