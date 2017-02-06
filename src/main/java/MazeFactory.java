import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by 43641457 on 06/02/2017.
 */
public class MazeFactory {

    public static Maze createMaze(Path filePath) {

        Maze maze = new Maze();

        AtomicInteger index = new AtomicInteger();

        try(Stream<String> stream = Files.lines(filePath)) {
            stream.forEach(line -> addLineToMaze(maze, index.getAndIncrement(), line));

        } catch (IOException e) {
            throw new RuntimeException("Error reading the file", e);
        }


        return maze;
    }

    private static void addLineToMaze(Maze maze, int fileLine, String line) {
        Map<Character, MazeElement> elements = new HashMap<>();

        elements.put('#', MazeElement.WALL);
        elements.put('s', MazeElement.START);
        elements.put('e', MazeElement.EXIT);
        elements.put(' ', MazeElement.SPACE);


        IntStream.range(0, line.length())
                .forEach(i -> maze.addElement(new Position(i, fileLine), elements.get(line.charAt(i))));

    }
}
