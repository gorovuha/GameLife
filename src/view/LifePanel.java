package view;

import model.LifeWorld;

import javax.swing.*;
import java.awt.*;

public class LifePanel extends JPanel {

    public LifeWorld world;
    private int rectSide;

    public LifePanel(LifeWorld world) {
        this.world = world;
        this.rectSide = 17;
        this.world.randomize();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        // TODO 11: нарисуйте прямоугольниками одного цвета - живые клетки, а прямоугольниками другого цвета - мертвые
        int width = world.getWidth();
        int height = world.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (world.isAlive(x, y)){
                    graphics.setColor(Color.red);
                    graphics.fillRect(rectSide * x, rectSide * y, rectSide, rectSide);
                } else {
                    graphics.setColor(Color.blue);
                    graphics.fillRect(rectSide * x, rectSide * y, rectSide, rectSide);
                }

            }
        }
    }
}
