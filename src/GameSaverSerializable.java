import java.io.*;
import java.util.concurrent.TimeUnit;

public class GameSaverSerializable {

    public static void main(String[] args) {

        GameCharacter one = new GameCharacter(50, "Эльф", new String[] {"лук", "меч", "кастет"}, "Коля");
        GameCharacter two = new GameCharacter(200, "Троль", new String[] {"голые руки", "большой топор"}, "Павел");
        GameCharacter three = new GameCharacter(120, "Маг", new String[] {"заклинания", "невидимость"}, "Антон");

        System.out.println(one.getPower() + " " + one.getType() + " " + one.getWeapons() + " " + one.getName());
        System.out.println(two.getPower() + " " + two.getType() + " " + two.getWeapons() + " " + two.getName());
        System.out.println(three.getPower() + " " + three.getType() + " " + three.getWeapons() + " " + three.getName() );

        // Делаю сериализацию объектов
        try {
            System.out.println("Начинаю делать сериализацию объектов");
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser"));
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        one = null;
        two = null;
        three = null;

        // Делаю небольшую задержку
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Все объекты сериализованны");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        // Делаю десериализацию объектов
        try {
            System.out.println("Начинаю делать десериализацию объектов");
            TimeUnit.SECONDS.sleep(3);
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Game.ser"));
            GameCharacter oneRestore = (GameCharacter) is.readObject();
            GameCharacter twoRestore = (GameCharacter) is.readObject();
            GameCharacter threeRestore = (GameCharacter) is.readObject();

            System.out.println(oneRestore.getPower() + " " + oneRestore.getType() + " " + oneRestore.getWeapons() + " " + oneRestore.getName());
            System.out.println(twoRestore.getPower() + " " + twoRestore.getType() + " " + twoRestore.getWeapons() + " " + twoRestore.getName());
            System.out.println(twoRestore.getPower() + " " + threeRestore.getType() + " " + threeRestore.getWeapons() + " " + threeRestore.getName());

        }
        catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
