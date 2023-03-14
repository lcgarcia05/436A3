import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Starts the client
 */
public class RunClient {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Client client = new Client();
        Scanner in = new Scanner(System.in);
        client.startClient();

        System.out.println("Enter a username: ");
        client.setName(in.nextLine());
        System.out.println("Welcome " + client.getName() + "!\n");

        String opt = client.showOptions();
        System.out.println(opt);

        while(in.hasNext()) {
            String line = in.nextLine();
            try {
                if(line.equalsIgnoreCase("4")) {
                    System.out.println("Exiting now..");
                    client.removeClient();
                    System.exit(0);
                }
                else if (line.equalsIgnoreCase("1")){
                    System.out.println("Enter room name: ");
                    client.createRoom(in.nextLine());
                    System.out.println("Type /quit to leave the room.");
                }

                else if (line.equalsIgnoreCase("2")){
                    System.out.println("Printing all rooms...");
                    client.showRooms();
                }

                else if (line.equalsIgnoreCase("3")){
                    System.out.println("Enter room #");
                    client.joinRoom(Integer.parseInt(in.nextLine()));
                }
                else if (line.equalsIgnoreCase("/quit")){
                    client.removeClient();
                    System.out.println("User has quit");
                    System.out.println(opt);
                }
                else {
                    client.broadcastMessage(line);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
