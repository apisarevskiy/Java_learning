import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class VerySimpleChatServer {

    ArrayList clinetOutputStreams;
    JTextArea incoming;
    JTextField outgoing;

    public class ClientHandler implements Runnable {

        BufferedReader reader;
        Socket sock;

        public ClientHandler(Socket clientSoket) {

            try {
                sock = clientSoket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {

            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

        public static void main(String[] args) {
            new VerySimpleChatServer().go();
        }

        public void go() {
            clinetOutputStreams = new ArrayList();
            JFrame frame = new JFrame("Alex Simple Chat Server");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel mainPanel = new JPanel();
            incoming = new JTextArea(15, 40);
            incoming.setLineWrap(true);
            incoming.setWrapStyleWord(true);
            incoming.setEditable(false);
            JScrollPane qScroller = new JScrollPane(incoming);
            qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            mainPanel.add(qScroller);
            outgoing = new JTextField(40);
            mainPanel.add(outgoing);

            frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
            frame.setSize(500, 500);
            frame.setVisible(true);

            try {
                ServerSocket serverSocket = new ServerSocket(5003);
                outgoing.setText(Inet4Address.getLocalHost().getHostAddress() + " / " + serverSocket.getLocalPort());

                while (true) {
                    Socket clientSoket = serverSocket.accept();
                    PrintWriter writer = new PrintWriter(clientSoket.getOutputStream());
                    clinetOutputStreams.add(writer);

                    Thread t = new Thread(new ClientHandler(clientSoket));
                    t.start();
                    System.out.println("got a connection ");
                    incoming.append("got a connection " + clientSoket.toString() + " \n");
                }
            } catch (Exception ex) { ex.printStackTrace(); }
        }

        public void tellEveryone(String message) {

            Iterator it = clinetOutputStreams.iterator();

            while(it.hasNext()) {
                try {
                    PrintWriter writer = (PrintWriter) it.next();
                    writer.println(message);
                    writer.flush();
                    System.out.println("send to client: " + message);
                } catch (Exception ex) { ex.printStackTrace(); }
            }
        }
    }

