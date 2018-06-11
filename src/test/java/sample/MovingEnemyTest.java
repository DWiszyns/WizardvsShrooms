package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovingEnemyTest {

    @Test
    public void isCloseEnough() {
        MovingEnemy movingEnemy = new MovingEnemy(100,100);
        Player player= new Player();
        assertEquals(movingEnemy.isCloseEnough(player.getX(),player.getX()+player.getWidth()),true);
        MovingEnemy movingEnemy2 = new MovingEnemy(200,100);
        assertEquals(movingEnemy2.isCloseEnough(player.getX(),player.getX()+player.getWidth()),false);
    }
}