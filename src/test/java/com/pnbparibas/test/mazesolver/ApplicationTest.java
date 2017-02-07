package com.pnbparibas.test.mazesolver;

import com.pnbparibas.test.mazesolver.domain.MazeFactory;
import com.pnbparibas.test.mazesolver.domain.MazeSolver;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by 43641457 on 07/02/2017.
 */
public class ApplicationTest {

    @Test
    public void shouldSolveMaze1AndReturnRightSolution() throws URISyntaxException {
        Application app = new Application();
        String solvedMaze = app.solveMaze(MazeSolver.class.getResource("/maze1.txt").getFile());
        String expectedSolution = new MazeFactory()
                .createMaze(Paths.get(MazeSolver.class.getResource("/maze1solution.txt").toURI()))
                .toString();

        assertEquals(expectedSolution, solvedMaze);

    }
}
