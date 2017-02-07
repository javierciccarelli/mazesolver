package com.pnbparibas.test.mazesolver.domain;

import com.pnbparibas.test.mazesolver.domain.Maze;
import com.pnbparibas.test.mazesolver.domain.MazeElement;
import com.pnbparibas.test.mazesolver.domain.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void shouldFindNextStepsOnlyOnSpacesAndExit() {
        testMaze.addElement(0, 0, MazeElement.WALL);
        testMaze.addElement(1, 0, MazeElement.START);
        testMaze.addElement(2, 0, MazeElement.SPACE);
        testMaze.addElement(0, 1, MazeElement.WALL);
        testMaze.addElement(1, 1, MazeElement.EXIT);
        testMaze.addElement(2, 1, MazeElement.SPACE);

        assertEquals(2, testMaze.findPossibleNextSteps(new Position(1, 0)).size());

        assertTrue(testMaze.findPossibleNextSteps(new Position(1, 0)).contains(new Position(2, 0)));
        assertTrue(testMaze.findPossibleNextSteps(new Position(1, 0)).contains(new Position(1, 1)));
    }

    @Test
    public void shouldAddSoulutionProperly() {
        testMaze.addElement(0, 0, MazeElement.START);
        testMaze.addElement(1, 0, MazeElement.SPACE);
        testMaze.addElement(2, 0, MazeElement.EXIT);

        Set<Position> solution = new HashSet<>(Arrays.asList(new Position(1, 0)));

        Maze resultMaze = testMaze.addSolution(solution);
        assertTrue(resultMaze.isElement(new Position(1, 0), MazeElement.PATH));
    }

}
