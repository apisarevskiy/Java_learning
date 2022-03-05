import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SimpleGui3C {
    JFrame frame;
    JLabel label;

    public static void main (String[] args) {
        SimpleGui3C gui = new SimpleGui3C();
        gui.go();
    }

    public void go() {

        // Создаём новый фрейм
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаём кнопку для изменения надписи на label и назначаю слушателя событий
        JButton labelButton = new JButton("Change Label");
        labelButton.addActionListener(new LabelListener());

        // Создаём кнопку для изменения цвета круга и назначаю слушателя событий
        JButton colorButton = new JButton("Change circle");
        colorButton.addActionListener(new ColorListener());
        colorButton.addMouseListener(new mouseListener());

        // Создаю панель на которой выводится цветной овал
        MyDrawPanel drawPanel = new MyDrawPanel();
        // Создаю label
        label = new JLabel("I'm a label");

        // Добавляю виджеты на фрейм
        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.WEST, label);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    // Реализую слушателя для событий кнопки labelButton
    class LabelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("Change:" + Integer.toString((int) (Math.random() * 10)));
        }
    }

    // Реализую слушателя для событий кнопки colorButton
    class ColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                frame.repaint();
        }
    }

    // Реализую слушателя для событий Мышки
    class mouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            label.setText("Change:" + Integer.toString((int) (Math.random() * 10)));
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }



}

