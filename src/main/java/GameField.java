import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320; //размер поля
    private final int DOT_SIZE = 16; //размер еденичной точки
    private final int ALL_DOTS = 400; //количество всех точек

    private Image dot;
    private Image apple;

    private int[] x = new int[ALL_DOTS]; //массив всех точек
    private int[] y = new int[ALL_DOTS];

    private int appleX; //координаты яблока
    private int appleY;

    private int dots; //количество звеньев змейки
    private Timer timer; //Количество кадров в секунду(скорость обновления змейки)

    private boolean left = false; //кнопки
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    private boolean inGame = true; //индикатор игры, если false то выходим из игры

    public void loadImage() { //метод присваивает переменным значения картинок
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
    }

    public void createApple() {
        Random random = new Random();
        appleX = random.nextInt(20) * DOT_SIZE;
        appleY = random.nextInt(20) * DOT_SIZE;

    }

    public void initGame() { //инициализация игры
        dots = 3;
        for (int i = 0; i < dots; i++) {
            y[i] = 48;
            x[i] = 48 - i * DOT_SIZE;
        }
        timer = new Timer(150,this);
        timer.start();
        createApple();
    }
    public void checkApple(){ //проверка "съела змейка яблоко?"
        if (x[0]==appleX&&y[0]==appleY){
            dots++;
            createApple();
        }
    }
    @Override  //отрисовать яблоки и саму змейку
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (inGame){
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);

            }
    }else{
        String str = "Game Over";
        g.setColor(Color.CYAN);
        g.drawString(str, SIZE/6, SIZE/2);

    }
   }
   public void checkCollision(){ //если наша змейка врезалась в бортик, то она будет появляться с противоположной стороны.
       for (int i = 0; i < dots; i++) {
           if(x[0]==x[i]&&y[0]==y[i]){
               inGame = false;
           }
       }
       if(x[0]>SIZE)
           x[0]=0;
       if(x[0]< 0)
           x[0]=SIZE;
       if(y[0]>SIZE)
           inGame = false;
       if(y[0]<0)
           inGame = false;

   }
   @Override
    public void actionPerformed(ActionEvent a){
        if(inGame){
            checkApple();
            checkCollision();
        }
        repaint();
   }
   public void move(){
       for (int i = dots; i < 0; i--) {
           x[i] = x[i-1];
           y[i] = y[i-1];
       }
       if(left)
           x[0] -= DOT_SIZE;
       if(right)
           x[0] += DOT_SIZE;
       if(up)
           y[0] -= DOT_SIZE;
       if(down)
           y[0] += DOT_SIZE;
   }
   class FiledKeyListener extends KeyAdapter{
        @Override
       public void keyPressed(KeyEvent k){
            super.keyPressed(k);
            int key = k.getKeyCode();

            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && !down){
                up = true;
                right = false;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                down = true;
                right = false;
                left = false;
            }
        }
   }
}
