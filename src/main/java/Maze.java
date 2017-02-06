import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by javier on 04/02/17.
 */
public class Maze {
    private Map<Position, MazeElement> matrix = new HashMap<>();

    private Position startPosition;
    int maxX, maxY, minX, minY = 0;

    public void addElement(Position pos, MazeElement element) {
        if (element == MazeElement.START)
            startPosition = pos;

        if (pos.getX() > maxX) maxX = pos.getX();
        if (pos.getY() > maxY) maxY = pos.getY();

        matrix.put(pos, element);
    }

    public List<Position> findPossibleNextSteps(Position pos) {
        return Arrays.asList(pos.add(1, 0), pos.add(-1, 0), pos.add(0, 1), pos.add(0, -1))
                .stream().filter(p ->!isOutOfBounds(p) && !isWall(p)).collect(Collectors.toList());
    }

    private boolean isOutOfBounds(Position p) {
        return p.getY() > maxY || p.getY() < minY || p.getX() > maxX || p.getX() < minX;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    private boolean isElement(Position pos, MazeElement element) {
        return matrix.get(pos) == element;
    }

    public boolean isFinish(Position pos) {
        return isElement(pos, MazeElement.EXIT);
    }

    public boolean isWall(Position pos) {
        return isElement(pos, MazeElement.WALL);
    }

}
