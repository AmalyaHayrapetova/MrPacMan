package Engine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class GameComponent extends JComponent {

    class GameFpsPainter implements  Runnable{
        int fps;

        public GameFpsPainter(int fps){
            this.fps = fps;
        }

        @Override
        public void run() {
            while(true){

                repaint();
                try{
                    wait(1000/fps);
                }
                catch(Exception e)
                {
                    // TODO: Do something
                }
            }
        }
    }

    class GameUpdater implements Runnable{
        private int updateInterval;
        private GameComponent gameComponent;

        public GameUpdater(GameComponent component, int updateInterval){
            this.updateInterval = updateInterval;
            this.gameComponent = component;
        }

        @Override
        public void run() {
            while(!game.isGameEnded()){
                for (var obj: gameComponent.game.objects  ) {
                    if(obj.alive())
                        obj.update();

                }
                try{
                    Thread.sleep(updateInterval);
                }
                catch(Exception e)
                {
                    // TODO: Do something
                }
            }
        }
    }

    private JFrame frame;
    private Game game;
    public static final int GV_WIDTH=112;
    public static final int GV_HEIGHT=124;


    public GameComponent(Game game){
        this.game = game;
    }

    public void start(){
        frame = new GameFrame(this, game.getKeyBoardInput());
        GameUpdater gameUpdater = new GameUpdater(this,30);
        Thread gameUpdaterThread = new Thread(gameUpdater);
        gameUpdaterThread.start();
        GameFpsPainter gameFpsPainter = new GameFpsPainter(30);
        gameFpsPainter.run();
    }

    public Dimension getPreferredSize()
    {
        return new Dimension((int)(GV_WIDTH*game.getScale()),(int)(GV_HEIGHT*game.getScale()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(game.getImageRepository().Get(game.getMaze().getImage()),0,0,null);
        for (var obj: this.game.objects  ) {
            if(obj.alive())
                obj.render(g);
        }
    }
}
