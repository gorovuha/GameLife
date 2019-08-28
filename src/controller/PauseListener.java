package controller;

import view.LifePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseListener implements ActionListener {

    LifePanel panel;


    public PauseListener(LifePanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO 21: добавить возможность ставить игру на паузу
        if (panel.world.update){
            panel.world.update = false;
        } else {
            panel.world.update = true;
        }

        //System.out.println("Пауза еще не поддержана т.к. не выполнено TODO 21!");
    }
}
