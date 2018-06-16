package sample;

import javafx.geometry.Rectangle2D;
import org.junit.Test;


import static org.junit.Assert.*;

public class FlagViewTest {

    @Test
    public void setAnimation() {
        FlagView flagView = new FlagView(new Flag(100,100));
        Rectangle2D previousViewPort = flagView.getViewOfMyFlag().getViewport();
        flagView.setAnimation();
        Rectangle2D currViewPort = flagView.getViewOfMyFlag().getViewport();
        assertEquals(previousViewPort,currViewPort);
        assertEquals(flagView.getFlag().getX(),flagView.getFlag().getY());
    }
}