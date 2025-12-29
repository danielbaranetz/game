package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class AnimationRunner {
    private GUI gui;
    private int framesPerSeconds;
    private Sleeper sleeper;

    public AnimationRunner(GUI gui){
        this.gui = gui;
        this.framesPerSeconds = 60;
        this.sleeper = new Sleeper();
    }

    public void run(Animation animation){
        int millisecondsPerFrame = 1000 / this.framesPerSeconds;

        while (!animation.shouldStop()){
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long miliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (miliSecondLeftToSleep > 0){
                this.sleeper.sleepFor(miliSecondLeftToSleep);
            }
        }
    }


}

