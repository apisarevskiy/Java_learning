import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClient {

    JTextArea incoming;
    JTextField outgoing;
    JTextField serverIP;
    JTextField serverPort;
    JTextField name;
    JLabel connection;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;

    public static void main(String[] args) {
        SimpleChatClient client = new SimpleChatClient();
        client.go();
    }

    public void go() {
        JFrame frame = new JFrame("Alex Simple Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        JPanel connectPanel = new JPanel();
        incoming = new JTextArea(15, 40);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        serverIP = new JTextField("IP adress", 20);
        serverPort = new JTextField("Port", 10);
        name = new JTextField("Anonymous", 10);
        connection = new JLabel("Not connected");
        connection.setVisible(true);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUpNetworking();
                Thread readerThread = new Thread(new IncomingReader());
                readerThread.start();
            }
        });

        mainPanel.add(qScroller);
        mainPanel.add(name);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);

        connectPanel.add(serverIP);
        connectPanel.add(serverPort);
        connectPanel.add(connectButton);
        connectPanel.add(connection);
        // setUpNetworking();

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.getContentPane().add(BorderLayout.SOUTH,connectPanel);
        frame.setSize(550, 400);
        frame.setVisible(true);
    }

    // Устанавливаем соединение с сервером
    private void setUpNetworking() {
        try {
            sock = new Socket(serverIP.getText(), Integer.parseInt(serverPort.getText()));
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            connection.setText("Connected");
            System.out.println("networking established");
        } catch (IOException ex) {
            connection.setText("Not connected");
            ex.printStackTrace();
        }
    }

    // Отправляем данные на сервер
    public class SendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                writer.println(name.getText() + ": " + outgoing.getText());
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    // Назначаем работу потоку
    public class IncomingReader implements Runnable {

        @Override
        public void run() {
           String message;
           System.out.println("Start Thread");
           incoming.append("Test message " + "\n");

           try{

               while ((message = reader.readLine()) != null) {
                   System.out.println("message from server: " + message);
                   incoming.append(message + "\n");
               }
           } catch (Exception ex) { ex.printStackTrace(); }
        }
    }
}
