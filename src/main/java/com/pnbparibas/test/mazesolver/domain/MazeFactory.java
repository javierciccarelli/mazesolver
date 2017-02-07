package com.pnbparibas.test.mazesolver.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by javier.
 */
public class MazeFactory {

    private Map<Character, MazeElement> characterMapping;

    public MazeFactory() {
        characterMapping = getMapFromEnum();
    }

    private Map<Character, MazeElement> getMapFromEnum() {
        return Stream.of(MazeElement.values())
                .collect(Collectors.toMap(MazeElement::getPrintChar, Function.identity()));
    }

    public Maze createMaze(Path filePath) {
        Maze maze = new Maze();

        AtomicInteger index = new AtomicInteger();

        try(Stream<String> stream = Files.lines(filePath)) {
            stream.forEach(line -> addLineToMaze(maze, index.getAndIncrement(), line));
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file", e);
        }

        return maze;
    }

    private void addLineToMaze(Maze maze, int fileLine, String line) {
        IntStream.range(0, line.length())
                .forEach(i -> maze.addElement(i, fileLine, elementFor(line.charAt(i))));
    }

    private MazeElement elementFor(char c) {
        return characterMapping.get(c);
    }
}
