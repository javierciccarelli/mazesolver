package com.pnbparibas.test.mazesolver.domain;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by javier.
 */
public class MazeSolver {

    private Maze maze;
    private List<Set<Position>> solutions;

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public boolean solve() {
        Position startPosition = maze.getStartPosition();
        solutions = findAllPaths(startPosition, new LinkedHashSet<>());

        return solutions.size() > 0;
    }

    private List<Set<Position>> findAllPaths(Position startPosition, Set<Position> path) {
        List<Set<Position>> result = new ArrayList<>();

        if (alreadyBeenInPosition(startPosition, path))
            return result;

        Set<Position> currentPath = new LinkedHashSet<>(path);
        currentPath.add(startPosition);

        if (maze.isFinish(startPosition)) {
            result.add(currentPath);
            return result;
        }

        maze.findPossibleNextSteps(startPosition)
                .forEach(step -> result.addAll(findAllPaths(step, currentPath)));

        return result;
    }

    public Maze getSolvedMaze() { return maze.addSolution(solutions.get(0)); }

    public List<Set<Position>> getSolutions() { return this.solutions; }

    private boolean alreadyBeenInPosition(Position startPosition, Set<Position> path) {
        return path.contains(startPosition);
    }
}
