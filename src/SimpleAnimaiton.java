import javax.swing.*;
import java.awt.*;

public class SimpleAnimaiton {

    int x = 70;
    int y = 70;

    public static void main(String[] args) {
        SimpleAnimaiton gui = new SimpleAnimaiton();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        InnerDrawPanel drawPanel = new InnerDrawPanel();

        frame.getContentPane().add(drawPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);

        for (int i = 0; i < 130; i++) {

            x++;
            y++;

            drawPanel.repaint();

            try {
                Thread.sleep(100);
            } catch (Exception ex) { }

        }
    }

    class InnerDrawPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.setColor(Color.GREEN);
            g.fillOval(x, y, 40, 40);
        }
    }

}
