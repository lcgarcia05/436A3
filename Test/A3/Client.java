import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * The implementation of the client
 */
public class Client implements ChatClientInt {

    private ChatServerInt server;
    private String name;

    public Client() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
    }

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("192.168.208.1"); // Change this according to the chatroom_server container current IP (check report file for more instruction)
        server = (ChatServerInt)registry.lookup("Server");

    }

    public void createRoom(String name) throws RemoteException {
        server.addRoom(name, this);
    }

    public void setName(String newName){
        name = newName;
    }

    @Override
    public String getName(){
        return this.name;
    }

    // Handles the broadcasting of messages
    public void broadcastMessage(String argument) {
        try {
            server.broadcastMessage(argument, this);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect to server");
        }
    }

    public void removeClient(){
        try {
            server.unregisterClient(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void broadcast(String result, ChatClientInt c) throws RemoteException {
        System.out.println(c.getName() + ":" + result);
    }

    @Override
    public String showOptions() throws RemoteException {
        return "--- OPTIONS --- \n" +
                "1: Create a room \n" +
                "2: List all room \n" +
                "3: Join a room \n" +
                "4: EXIT";
    }

    @Override
    public void showRooms() throws RemoteException {
        try{
            ArrayList<Chatroom> rooms = server.listRooms();
            for (int i=0; i<rooms.size(); i++){
                System.out.println("Room #" + i + " " + rooms.get(i).name);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void joinRoom(int roomNum) throws RemoteException {
        server.addClient(roomNum, this);
        ArrayList<String> hist = server.getMessageHistory(roomNum);
        System.out.println("Previous message" + hist);
    }

}