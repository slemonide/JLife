package cells;

import geometry.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Cell class
 */
public class CellTest {
    private Cell testCell;

    @Before
    public void runBefore() {
        testCell = new Cell(new Position(1, 2, 0));
    }

    @Test
    public void testConstructor() {
        assertEquals(new Position(1, 2, 0),
                testCell.getPosition());
    }

    @Test
    public void testSetName() {
        testCell.setName("Rem");
        assertEquals("Rem", testCell.getName());
    }

}