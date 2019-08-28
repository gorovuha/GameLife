package model;

import java.util.Random;

public class LifeWorld {

    // private модификатор непозволит случайно напрямую изменить значения этих полей из других классов тем самым снизив риск ошибиться
    private CellState[][] cells;
    private int width;
    private int height;
    public boolean update;

    public LifeWorld(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new CellState[width][height];
        this.update = true;

        // Изначально этот мир мертв внутри на этапе создания
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                this.cells[x][y] = CellState.DEAD;
            }
        }
    }

    public void randomize() {
        Random random = new Random();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // TODO 1: используя random.nextFloat() возвращающий случайное число от 0 до 1
                //  сделайте клетку живой с некоторой вероятностью (потом попробуйте подобрать вероятность так, чтобы мир жил поинтереснее)
                Float a = random.nextFloat() * 10;
                if (a > 7){
                    cells[x][y] = CellState.ALIVE;
                }
            }
        }
    }

    public void updateWorld() throws InterruptedException {
        if (update) {
            CellState[][] nextState = new CellState[width][height];
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int x = 0; x < width/2; ++x) {
                        for (int y = 0; y < height; ++y) {

                            int neighboursAlive = 0;
                            // TODO 2: посчитайте сколько соседей вокруг клетки (x, y) живы
                            for (int dx = -1; dx <= 1; dx++) {
                                for (int dy = -1; dy <= 1; dy++) {
                                    if (x + dx < width && y + dy < height && x + dx >= 0 && y + dy >= 0 && (dx != 0 || dy != 0)) {
                                        int xn = x + dx;
                                        int yn = y + dy;
                                        if (cells[xn][yn] == CellState.ALIVE) {
                                            neighboursAlive += 1;
                                        }
                                    }

                                }
                            }

                            // TODO 3: обновите состояние в НОВЫЙ момент времени (т.е. в nextState) для клетки отталкиваясь от правил игры «Жизнь» (и того сколько соседей были живы, т.е. учитывая neighboursAlive)
                            if (neighboursAlive == 3 && cells[x][y] == CellState.DEAD) {
                                nextState[x][y] = CellState.ALIVE;
                            }
                            if ((neighboursAlive == 2 || neighboursAlive == 3) && cells[x][y] == CellState.ALIVE) {
                                nextState[x][y] = CellState.ALIVE;
                            }
                            if (neighboursAlive < 2 || neighboursAlive > 3) {
                                nextState[x][y] = CellState.DEAD;
                            }
                        }
                    }
                }
            });
            thread1.start();
            thread1.join();

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int x = width/2; x < width; ++x) {
                        for (int y = 0; y < height; ++y) {

                            int neighboursAlive = 0;
                            // TODO 2: посчитайте сколько соседей вокруг клетки (x, y) живы
                            for (int dx = -1; dx <= 1; dx++) {
                                for (int dy = -1; dy <= 1; dy++) {
                                    if (x + dx < width && y + dy < height && x + dx >= 0 && y + dy >= 0 && (dx != 0 || dy != 0)) {
                                        int xn = x + dx;
                                        int yn = y + dy;
                                        if (cells[xn][yn] == CellState.ALIVE) {
                                            neighboursAlive += 1;
                                        }
                                    }

                                }
                            }

                            // TODO 3: обновите состояние в НОВЫЙ момент времени (т.е. в nextState) для клетки отталкиваясь от правил игры «Жизнь» (и того сколько соседей были живы, т.е. учитывая neighboursAlive)
                            if (neighboursAlive == 3 && cells[x][y] == CellState.DEAD) {
                                nextState[x][y] = CellState.ALIVE;
                            }
                            if ((neighboursAlive == 2 || neighboursAlive == 3) && cells[x][y] == CellState.ALIVE) {
                                nextState[x][y] = CellState.ALIVE;
                            }
                            if (neighboursAlive < 2 || neighboursAlive > 3) {
                                nextState[x][y] = CellState.DEAD;
                            }
                        }
                    }
                }
            });
            thread2.start();
            thread2.join();

            // TODO 4: наконец замените старое состояние клеток на новое которое вы только что посчитали
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    cells[x][y] = nextState[x][y];
                    nextState[x][y] = CellState.DEAD;
                }
            }
        }
    }

    public int getWidth() {
        // Это так называемый getter - метод который позволяет узнать значение private-поля.
        // Он нужен т.к. прямого доступа к полю нет из-за модификатора private у поля (чтобы например случайно не изменить размер мира извне, тем самым сломав его).
        // Этот метод не private, а public, чтобы его можно было вызвать отовсюду.
        return this.width;
    }

    public int getHeight() {
        // TODO 5: сделайте возможным извне узнать высоту мира (см. выше как реализован другой getter - getWidth)
        //  для этого нужно изменить следующую строчку:
        return this.height;
    }

    public boolean isAlive(int x, int y) {
        // TODO 6: поправьте этот метод так, чтобы он позволял узнать, жива ли конкретная клетка
        if (cells[x][y] == CellState.ALIVE){
            return true;
        } else{
            return false;
        }
    }

}
