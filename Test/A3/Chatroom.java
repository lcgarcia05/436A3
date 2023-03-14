import java.io.Serializable;
import java.util.ArrayList;

/**
 * An object to store the clients for the chatroom
 */
public class Chatroom implements Serializable {
    String name;
    ArrayList<ChatClientInt> clients;
    ArrayList<String> messages;

    public Chatroom(String newName){
        name = newName;
        clients = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public boolean contains(ChatClientInt c){
        return clients.contains(c);
    }

    public void registerClient(ChatClientInt clientToRegister) {
        clients.add(clientToRegister);
    }

    public void unregisterClient(ChatClientInt clientToRemove){
        clients.remove(clientToRemove);
    }

    public void addMessageHistory(String message){
        messages.add(message);
    }

    public ArrayList<String> getMessageHistory(){
        return this.messages;
    }
}
