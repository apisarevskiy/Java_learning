import javax.swing.*;
import java.awt.*;

public class MyDrawPanel extends JPanel {

    // Отрисовывает компонени
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Создаю начальный цвет градиента
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        Color startColor = new Color(red, green, blue);

        // Создаю конечный цвет градиента
        red = (int) (Math.random() * 255);
        green = (int) (Math.random() * 255);
        blue = (int) (Math.random() * 255);
        Color endColor = new Color(red, green, blue);

        // Задаю градиент и рисую овал
        GradientPaint gradient = new GradientPaint(70, 70, startColor, 150, 150, endColor);
        g2d.setPaint(gradient);
        g2d.fillOval(70, 70, 100, 100);
    }
}
