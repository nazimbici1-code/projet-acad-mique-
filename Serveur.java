
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

    ServerSocket ss;
    int port = 3000;

    public Serveur() {
        System.out.println("Serveur démarré, j'attends des clients...");

        try {
            ss = new ServerSocket(port);

            while (true) {
                Socket socket = ss.accept();
                System.out.println("Nouveau client connecté : " + socket);

                ClientHandler ch = new ClientHandler(socket);
                ch.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Serveur();
    }
}
