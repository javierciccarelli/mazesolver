import java.util.ArrayList;
import java.util.List;

/**
 * Created by javier on 05/02/17.
 */
public class MazeSolver {

    private Maze maze;

    public List<Position> findSolution() {
        Position startPosition = maze.getStartPosition();

        List<List<Position>> allPaths = findAllPaths(startPosition, new ArrayList<Position>());

        return allPaths.get(0);

    }

    private List<List<Position>> findAllPaths(Position startPosition, List<Position> path) {
        List<List<Position>> result = new ArrayList<List<Position>>();
        List<Position> currentPath = new ArrayList<Position>();
        currentPath.add(startPosition);

        if (maze.isFinish(startPosition)) {
            result.add(currentPath);
            return result;
        }

        List<Position> nextSteps = findPossibleNextStep(startPosition);

        nextSteps.forEach(s -> result.addAll(findAllPaths(s, currentPath)));

        return result;
    }

    private List<Position> findPossibleNextStep(Position startPosition) {
    }
}
