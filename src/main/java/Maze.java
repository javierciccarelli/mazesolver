import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javier on 04/02/17.
 */
public class Maze {
    private ArrayList<ArrayList<MazeElement>> map = new ArrayList<ArrayList<MazeElement>>();

    private Position startPosition;

    public void addElement(Position pos, MazeElement element) {
        if (element == MazeElement.START)
            startPosition = pos;

        while (pos.getY() < map.size())
            map.add(new ArrayList<MazeElement>());

        while (pos.getX() < map.get(pos.getY()).size())
            map.get(pos.getY()).add(MazeElement.SPACE);

        map.get(pos.getY()).set(pos.getX(), element);
    }


    public Position getStartPosition() {
        return startPosition;
    }

    public boolean isFinish(Position pos) {
        return map.get(pos.getY()).get(pos.getX()) == MazeElement.EXIT;
    }
}
