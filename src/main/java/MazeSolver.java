import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by javier on 05/02/17.
 */
public class MazeSolver {

    private Maze maze;

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public Set<Position> findSolution() {
        Position startPosition = maze.getStartPosition();

        List<Set<Position>> allPaths = findAllPaths(startPosition, new LinkedHashSet<>());

        return allPaths.get(0);

    }

    private List<Set<Position>> findAllPaths(Position startPosition, Set<Position> path) {
        List<Set<Position>> result = new ArrayList<>();
        Set<Position> currentPath = new LinkedHashSet<>(path);
        currentPath.add(startPosition);

        if (maze.isFinish(startPosition)) {
            result.add(currentPath);
            return result;
        }

        if (path.contains(startPosition)) {
            return result;
        }

        List<Position> nextSteps = maze.findPossibleNextSteps(startPosition);

        nextSteps.forEach(s -> result.addAll(findAllPaths(s, currentPath)));

        return result;
    }


    public static void main(String[] args) throws URISyntaxException {
        Maze maze = new Maze();
        maze.addElement(new Position(0, 0), MazeElement.WALL);
        maze.addElement(new Position(0, 1), MazeElement.WALL);
        maze.addElement(new Position(0, 2), MazeElement.WALL);
        maze.addElement(new Position(0, 3), MazeElement.WALL);

        maze.addElement(new Position(1, 0), MazeElement.WALL);
        maze.addElement(new Position(1, 1), MazeElement.START);
        maze.addElement(new Position(1, 2), MazeElement.SPACE);
        maze.addElement(new Position(1, 3), MazeElement.WALL);

        maze.addElement(new Position(2, 0), MazeElement.WALL);
        maze.addElement(new Position(2, 1), MazeElement.SPACE);
        maze.addElement(new Position(2, 2), MazeElement.EXIT);
        maze.addElement(new Position(2, 3), MazeElement.WALL);

        maze.addElement(new Position(3, 0), MazeElement.WALL);
        maze.addElement(new Position(3, 1), MazeElement.WALL);
        maze.addElement(new Position(3, 2), MazeElement.WALL);
        maze.addElement(new Position(3, 3), MazeElement.WALL);

       maze = MazeFactory.createMaze(Paths.get(MazeSolver.class.getResource("/Test.txt").toURI()));

        MazeSolver solver = new MazeSolver(maze);

        System.out.println(solver.findSolution());


    }


}
