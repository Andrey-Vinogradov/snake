import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameField extends JPanel {
    private final int SIZE = 320; //размер поля
    private final int DOT_SIZE = 16; //размер еденичной точки
    private final int ALL_DOTS =400; //количество всех точек

    private Image dot;
    private Image apple;

    private int[] x = new int[ALL_DOTS]; //массив всех точек
    private int[] y = new int[ALL_DOTS];

    private int appleX; //координаты яблока
    private int appleY;

    private int dots; //количество звеньев змейки
    private Timer timer; //Количество кадров в секунду(скорость обновления змейки)

    private boolean inGame = true; //индикатор игры, если false то выходим из игры

    public void loadImage(){ //метод присваивает переменным значения картинок
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
    }
    public void createApple(){
        Random random = new Random();
        appleX = random.nextInt(20)*DOT_SIZE;
        appleY = random.nextInt(20)*DOT_SIZE;

    }
}
