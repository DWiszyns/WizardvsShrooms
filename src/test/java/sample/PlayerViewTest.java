package sample;

import javafx.geometry.Point2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerViewTest {

    @Test
    public void setAnimation() {
        PlayerView playerView = new PlayerView(new Player());
        playerView.setAnimation(false);
        assertEquals(playerView.getViewOfMyPlayer().getViewport(),playerView.getImageCells(0));
        playerView.setVelocityView(new Point2D(0,1));
        playerView.getPlayer().setInAir(true);
        playerView.setAnimation(false);
        assertNotEquals(playerView.getViewOfMyPlayer().getViewport(),playerView.getImageCells(7));//because it should do this, when player.getY()>0
        playerView.getPlayer().setVelocity(1,1);
        playerView.getPlayer().setInAir(true);
        playerView.setAnimation(true);
        assertEquals(playerView.getViewOfMyPlayer().getViewport(),playerView.getImageCells(7));//here we set player.getY so it works
        playerView.getPlayer().setVelocity(1,-1);
        playerView.getPlayer().setInAir(true);
        playerView.setAnimation(true);
        assertEquals(playerView.getViewOfMyPlayer().getViewport(),playerView.getImageCells(5));
    }
}