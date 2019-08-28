import model.LifeWorld;
import view.LifePanel;
import controller.PauseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        LifeWorld world = new LifeWorld(30, 20);
        // TODO 12: изначально мир должен быть случайным - вызовите соответствующий метод у world

        // Создаем окно
        JFrame frame = new JFrame();

        // Создаем нашу основную панель, в которой будет нарисовано что-то красивое
        // Чтобы это что-то красивое (наш клеточный мир) могло быть нарисовано - нужно дать возможность панели спросить у мира про каждую клетку "жива ли она?"
        LifePanel panel = new LifePanel(world);

        // Создаем две кнопки: кнопка паузы и кнопка перезапуска
        JButton pauseButton = new JButton("Pause");
        JButton restartButton = new JButton("Restart");

        // Навешиваем обработчики событий на обе кнопки
        // Первая кнопка должна ставить на паузу рисующуюся красоту
        pauseButton.addActionListener(new PauseListener(panel));

        // Вторая кнопка должна все клетки переводить в случайное состояние (перезапускать игру)
        restartButton.addActionListener(new ActionListener() {
            // Таким образом можно реализовать интерфейс ActionListener прямо посреди кода, без необходимости создавать
            // новый класс MyYetAnotherActionListener.
            // Иногда это хорошая идея, например когда в методе делается что-то очень простое и короткое, как здесь.
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 22: добавить возможность перезапускать игру
                panel.world.randomize();
                //System.out.println("Перезапуск еще не поддержан т.к. не выполнено TODO 22!");
            }
        });

        // Создаем панель, в которой будут лежать две кнопки
        JPanel controls = new JPanel();
        // Настраиваем layout этой панели - нам подойдет табличка из двух строчек и одного столбца
        controls.setLayout(new GridLayout(2, 1));
        // Добавляем кнопки
        controls.add(pauseButton);
        controls.add(restartButton);

        // Настраиваем layout всего окна
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);  // по центру будет панель рисующая красоту
        frame.add(controls, BorderLayout.EAST); // справа будет панель с управлением

        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        int n = Runtime.getRuntime().availableProcessors();
        System.out.println(n);

        while (true) {
            panel.repaint();

            // TODO 13: не забудьте вызывать обновление состояния клеток мира раз во сколько-то миллисекунд
            panel.world.updateWorld();
            Thread.sleep(500);
        }
    }

}
