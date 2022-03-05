import javax.swing.*;
import java.awt.event.ActionListener;

public class SimpleGui1 {
    public static void main(String[] args) {

        // Создаём фрейм
        JFrame frame = new JFrame();

        // Создаём кнопку
        JButton button = new JButton("click me");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Добавляем кнопку на панель фрейма
        frame.getContentPane().add(button);
        frame.setSize(300, 300);
        frame.setVisible(true);


    }
}
