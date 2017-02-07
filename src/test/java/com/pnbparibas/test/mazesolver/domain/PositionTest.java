package com.pnbparibas.test.mazesolver.domain;

import com.pnbparibas.test.mazesolver.domain.Position;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by javier.
 */
public class PositionTest {

    @Test
    public void equalsAndHashCodeShouldWorkProperlyForEqualPositions() {

        Position pos1 = new Position(1, 10);
        Position pos2 = new Position(1, 10);

        assertTrue(pos1.equals(pos2));
        assertTrue(pos1.hashCode() == pos2.hashCode());

    }

    @Test
    public void equalsAndHashCodeShouldWorkProperlyForNonEqualPositions() {

        Position pos1 = new Position(1, 10);
        Position pos2 = new Position(1, 12);

        assertFalse(pos1.equals(pos2));
        assertFalse(pos1.hashCode() == pos2.hashCode());

    }
}
