package com.pnbparibas.test.mazesolver;

import com.pnbparibas.test.mazesolver.domain.Maze;
import com.pnbparibas.test.mazesolver.domain.MazeFactory;
import com.pnbparibas.test.mazesolver.domain.MazeSolver;

import java.nio.file.Paths;

/**
 * Created by javier.
 */
public class Application {

    protected String solveMaze(String filePath) {

        Maze mazeToSolve = new MazeFactory().createMaze(Paths.get(filePath));
        MazeSolver solver = new MazeSolver(mazeToSolve);

        if (solver.solve()) {
            return solver.getSolvedMaze().toString();
        }

        return mazeToSolve.toString();
    }

    public void solveMazeAndPrintResult(String filePath) {
        System.out.println(solveMaze(filePath));
    }


}
