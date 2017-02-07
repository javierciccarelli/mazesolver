package com.pnbparibas.test.mazesolver.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by javier.
 */
public class Maze {
    private Map<Position, MazeElement> matrix = new HashMap<>();

    private Position startPosition;
    int maxX, maxY, minX, minY = 0;

    public void addElement(int x, int y, MazeElement element) {
        addElement(new Position(x, y), element);
    }

    public void addElement(Position pos, MazeElement element) {
        if (element == MazeElement.START)
            startPosition = pos;

        if (pos.getX() > maxX) maxX = pos.getX();
        if (pos.getY() > maxY) maxY = pos.getY();

        matrix.put(pos, element);
    }

    public List<Position> findPossibleNextSteps(Position pos) {
        return Arrays.asList(pos.add(1, 0), pos.add(-1, 0), pos.add(0, 1), pos.add(0, -1))
                .stream()
                .filter(p ->!isOutOfBounds(p) && !isWall(p))
                .collect(Collectors.toList());
    }

    private boolean isOutOfBounds(Position p) {
        return p.getY() > maxY || p.getY() < minY || p.getX() > maxX || p.getX() < minX;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public boolean isElement(Position pos, MazeElement element) {
        return matrix.get(pos) == element;
    }

    public boolean isFinish(Position pos) {
        return isElement(pos, MazeElement.EXIT);
    }

    public boolean isStart(Position pos) {
        return isElement(pos, MazeElement.START);
    }


    public boolean isWall(Position pos) {
        return isElement(pos, MazeElement.WALL);
    }

    @Override
    public String toString() {
        IntStream yStream = IntStream.range(0, maxY + 1);

        return yStream.mapToObj(y -> printLine(y))
                .collect(Collectors.joining("\n"));
    }

    private String printLine(int y) {
        IntStream xStream = IntStream.range(0, maxX + 1);
        return xStream.mapToObj(x -> printCharacter(x, y)).collect(Collectors.joining());

    }

    private String printCharacter(int x, int y) {
        return String.valueOf(matrix.get(new Position(x, y)).printChar);
    }

    public Maze addSolution(Set<Position> positions) {
        Maze newMaze = new Maze();
        matrix.forEach((pos, element) -> newMaze.addElement(pos, element));
        positions.stream()
                .filter(pos -> !isStart(pos) && !isFinish(pos))
                .forEach(pos -> newMaze.addElement(pos, MazeElement.PATH));

        return newMaze;
    }
}
