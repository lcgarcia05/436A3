import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/*
    Implementation of the Chat server, where all the functionality of the server starts
 */
public class Server implements ChatServerInt {

    private final ArrayList<Chatroom> chatrooms;

    public Server() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        chatrooms = new ArrayList<>();
    }

    private void updateClients(String message, ChatClientInt c){
        for (Chatroom cr : chatrooms){
            if (cr.contains(c)){
                cr.clients.forEach(clientInt -> {
                    try {
                        cr.addMessageHistory(message);
                        clientInt.broadcast(message, c);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    @Override
    public void addRoom(String name, ChatClientInt client) throws RemoteException {
        Chatroom newChat = new Chatroom(name);
        chatrooms.add(newChat);
        newChat.registerClient(client);
    }

    @Override
    public ArrayList<Chatroom> listRooms() throws RemoteException {
        return this.chatrooms;
    }

    @Override
    public void addClient(int room, ChatClientInt client) throws RemoteException {
        Chatroom chosenRoom = chatrooms.get(room);
        chosenRoom.registerClient(client);
    }

    @Override
    public ArrayList<String> getMessageHistory(int num) throws RemoteException {
        return chatrooms.get(num).getMessageHistory();
    }

    @Override
    public void broadcastMessage(String str, ChatClientInt client) {
        updateClients(str, client);

    }

    @Override
    public void unregisterClient(ChatClientInt clientToRemove){
        for (Chatroom cr: chatrooms){
            if (cr.contains(clientToRemove)){
                cr.unregisterClient(clientToRemove);
            }
        }
    }
}