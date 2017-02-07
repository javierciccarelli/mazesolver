package com.pnbparibas.test.mazesolver.domain;

import com.pnbparibas.test.mazesolver.domain.*;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class MazeSolverTest {

    @Test
    public void shouldSolveBasicMaze() {
        Maze maze = new Maze();
        maze.addElement(0, 0, MazeElement.START);
        maze.addElement(1, 0, MazeElement.EXIT);

        MazeSolver solver = new MazeSolver(maze);

        assertTrue(solver.solve());
    }

    @Test
    public void shouldSolveBasicMazeAndReturnSingleSolution() {
        Maze maze = new Maze();
        maze.addElement(0, 0, MazeElement.START);
        maze.addElement(1, 0, MazeElement.EXIT);

        MazeSolver solver = new MazeSolver(maze);
        solver.solve();

        assertTrue(solver.getSolutions().size() == 1);
    }

    @Test
    public void shouldSolveBasicMazeAndReturnRightSolution() {
        Maze maze = new Maze();
        maze.addElement(0, 0, MazeElement.START);
        maze.addElement(1, 0, MazeElement.EXIT);

        MazeSolver solver = new MazeSolver(maze);
        solver.solve();

        assertTrue(solver.getSolutions().get(0).contains(new Position(0, 0)));
        assertTrue(solver.getSolutions().get(0).contains(new Position(1, 0)));
    }

    @Test
    public void shouldFailToSolveMazeWithNoSolutions() {
        Maze maze = new Maze();
        maze.addElement(0, 0, MazeElement.START);
        maze.addElement(1, 0, MazeElement.WALL);
        maze.addElement(2, 0, MazeElement.EXIT);

        MazeSolver solver = new MazeSolver(maze);
        assertFalse(solver.solve());
    }

    @Test
    public void shouldSolveBasicMazeAndReturnNewMaze() {
        Maze maze = new Maze();
        maze.addElement(0, 0, MazeElement.START);
        maze.addElement(1, 0, MazeElement.SPACE);
        maze.addElement(2, 0, MazeElement.EXIT);

        MazeSolver solver = new MazeSolver(maze);

        assertTrue(solver.solve());
        assertNotNull(solver.getSolvedMaze());
        assertTrue(solver.getSolvedMaze().isElement(new Position(1, 0), MazeElement.PATH));
    }

    @Test
    public void shouldSolveMazeWithManySolutions() {
        Maze maze = new Maze();
        maze.addElement(0, 0, MazeElement.SPACE);
        maze.addElement(1, 0, MazeElement.START);
        maze.addElement(2, 0, MazeElement.SPACE);
        maze.addElement(0, 1, MazeElement.SPACE);
        maze.addElement(1, 1, MazeElement.WALL);
        maze.addElement(2, 1, MazeElement.SPACE);
        maze.addElement(0, 1, MazeElement.SPACE);
        maze.addElement(1, 1, MazeElement.EXIT);
        maze.addElement(2, 1, MazeElement.SPACE);

        MazeSolver solver = new MazeSolver(maze);

        assertTrue(solver.solve());
    }

    @Test
    public void shouldSolveMazeWithManySolutionsAndFindAllOfThem() {
        Maze maze = new Maze();
        maze.addElement(0, 0, MazeElement.SPACE);
        maze.addElement(1, 0, MazeElement.START);
        maze.addElement(2, 0, MazeElement.SPACE);
        maze.addElement(0, 1, MazeElement.SPACE);
        maze.addElement(1, 1, MazeElement.WALL);
        maze.addElement(2, 1, MazeElement.SPACE);
        maze.addElement(0, 2, MazeElement.SPACE);
        maze.addElement(1, 2, MazeElement.EXIT);
        maze.addElement(2, 2, MazeElement.SPACE);

        MazeSolver solver = new MazeSolver(maze);

        solver.solve();
        assertEquals(2, solver.getSolutions().size());
    }

    @Test
    public void shouldSolveMazeWithDeadPaths() throws URISyntaxException {
        Maze maze = new MazeFactory().createMaze(Paths.get(MazeSolver.class.getResource("/maze1.txt").toURI()));

        MazeSolver solver = new MazeSolver(maze);

        assertTrue(solver.solve());
    }

    @Test
    public void shouldSolveMazeWithDeadPathsAndFindRightNumberOfSolutions() throws URISyntaxException {
        Maze maze = new MazeFactory().createMaze(Paths.get(MazeSolver.class.getResource("/maze1.txt").toURI()));

        MazeSolver solver = new MazeSolver(maze);

        solver.solve();
        assertEquals(1, solver.getSolutions().size());
    }

}
