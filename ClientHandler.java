
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ClientHandler extends Thread {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    static JFrame f = null;
    static JTextArea texte;

    static List<ClientHandler> clients = new ArrayList<>();
    static int clientCount = 0;

    int clientId;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        synchronized (ClientHandler.class) {
            clients.add(this);
            clientCount++;
            clientId = clientCount;
            if (f == null) creerGUI();
        }
    }

    private void creerGUI() {
        f = new JFrame("Serveur");
        f.setBounds(0,0,600,400);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        texte = new JTextArea();
        texte.setBounds(100,10,400,300);
        texte.setEditable(false);
        f.add(texte);

        f.setVisible(true);
    }

    public void sendClient(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (Exception e) {}
    }

    private void sendClientNumber() {
        try {
            out.writeUTF("Vous êtes le Client " + clientId);
            out.flush();
        } catch (Exception e) {}
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            sendClientNumber();

            while (true) {
                String recu = in.readUTF();
                if (recu.isEmpty()) continue;

                if (recu.startsWith("[En cours]")) {
                   
                    String enCours = recu.replace("[En cours]", "").trim();
                    texte.append("Client " + clientId + " est en train d'écrire: " + enCours + "\n");
                } else {
                    
                    texte.append("Client " + clientId + ": " + recu + "\n");
                    
                    synchronized (ClientHandler.class) {
                        for (ClientHandler ch : clients) {
                            if (ch != this) {
                                ch.sendClient("Client " + clientId + ": " + recu);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            texte.append("Client déconnecté: " + socket + "\n");
            clients.remove(this);
        }
    }
}
