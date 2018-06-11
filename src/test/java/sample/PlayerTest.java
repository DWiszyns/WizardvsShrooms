package sample;

import org.junit.Test;

import static org.junit.Assert.*;
import static sample.Collidable.typeOfCollision.SIDE;
import static sample.Collidable.typeOfCollision.UP;

public class PlayerTest {

    @Test
    public void jump() {
        Player player = new Player();
        player.jump();
        assertEquals(player.isInAir(),true);
        player.setJumpingFrameIndex(40);
        player.jump();
        assertEquals(player.isJumping(),false);
        assertEquals(player.isInAir(),true);
    }

    @Test
    public void isColliding() {
        Player player = new Player();
        Platform platform = new Platform(0,405,60,60);
        Enemy enemy = new Enemy(60,375);
        assertEquals(player.isColliding(platform),UP);
        assertEquals(player.isColliding(enemy),SIDE);
    }

    @Test
    public void update() {
        Player player = new Player();
        player.setVelocity(1,1);
        player.update(true,true);
        assertEquals(player.isJumping(),false);
        player.setJumping(true);
        player.setJumpingFrameIndex(7);
        player.update(false,true);
        assertEquals(player.getJumpingFrameIndex(),8);
    }
}