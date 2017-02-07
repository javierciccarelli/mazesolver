package com.pnbparibas.test.mazesolver.domain;

/**
 * Created by javier.
 */
public enum MazeElement {

    WALL('#'), SPACE(' '), START('S'), EXIT('E'), PATH('*');

    public char printChar;

    MazeElement(char printChar) { this.printChar = printChar; }

    public char getPrintChar() { return printChar; }
}
