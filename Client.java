@ -0,0 +1,100 @@
package essaye;

import java.awt.event.*;
import java.io.*;
import java.net.Socket;

import javax.swing.*;

public class Client {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    JFrame f;
    JTextArea txtArea;
    JTextField txtField;
    JButton btn;

    public Client() {
        try {
            socket = new Socket("localhost", 3000);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

        f = new JFrame("Client Chat");
        f.setSize(400, 400);
        f.setLayout(null);

        txtArea = new JTextArea();
        txtArea.setBounds(20, 20, 350, 250);
        txtArea.setEditable(false);
        f.add(txtArea);

        txtField = new JTextField();
        txtField.setBounds(20, 280, 250, 30);
        f.add(txtField);

        btn = new JButton("Envoyer");
        btn.setBounds(280, 280, 90, 30);
        f.add(btn);

        
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String message = txtField.getText().trim();
                    if (!message.isEmpty()) {
                        out.writeUTF(message);
                        txtArea.append("Moi : " + message + "\n");
                        txtField.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

       
       
        txtField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                try {
                    out.writeUTF("__typing__"); 
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

       
        new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (in.available() > 0) {
                        String msg = in.readUTF();

                        if (msg.equals("__typing__")) return; 

                        txtArea.append(msg + "\n");
                    }
                } catch (Exception ex) {
                    
                }
            }
        }).start();

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Client();
    }
}
