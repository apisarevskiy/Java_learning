import java.io.Serializable;

// Создаём класс для последующе сериализации/десериализации объектов
public class GameCharacter implements Serializable {

    int power;
    String type;
    String[] weapons;
    transient String name;

    public GameCharacter(int p, String t, String[] w, String n) {
        power = p;
        type = t;
        weapons = w;
        name = n;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getWeapons() {
        String weaponList = "";

        for (int i = 0; i < weapons.length; i++) {
            weaponList += weapons[i] + " ";
        }
        return weaponList;
    }
}
