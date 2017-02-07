package com.pnbparibas.test.mazesolver;

import com.pnbparibas.test.mazesolver.domain.Maze;
import com.pnbparibas.test.mazesolver.domain.MazeElement;
import com.pnbparibas.test.mazesolver.domain.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by javier.
 */
public class MazeTest {

    private Maze testMaze = new Maze();

    @Test
    public void shouldAddWallProperly() {
        testMaze.addElement(0, 0, MazeElement.WALL);
        assertTrue(testMaze.isWall(new Position(0, 0)));
    }

    @Test
    public void shouldAddStartProperly() {
        testMaze.addElement(0, 0, MazeElement.START);
        assertTrue(testMaze.isStart(new Position(0, 0)));
        assertEquals(new Position(0, 0), testMaze.getStartPosition());
    }

    @Test
    public void shouldAddExitProperty() {
        testMaze.addElement(0, 0, MazeElement.EXIT);
        assertTrue(testMaze.isFinish(new Position(0, 0)));
    }


}
