package server;

import exceptions.InvalidDensityException;
import exceptions.InvalidDimensionException;
import org.junit.Before;
import org.junit.Test;
import server.application.Cell;
import server.application.WorldGenerator;
import server.application.WorldManager;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author      Danil Platonov <slemonide@gmail.com>
 * @version     0.1
 * @since       0.1
 *
 * Test for WorldGenerator
 */
public class WorldGeneratorTest {
    @Before
    public void runBefore() {
        WorldManager.getInstance().clear();
    }

    @Test
    public void testGenerationNoCells() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0,0,0,0);
        assertTrue(WorldManager.getInstance().getCells().isEmpty());

        WorldGenerator.generate(0.3,0,0,0);
        assertTrue(WorldManager.getInstance().getCells().isEmpty());

        WorldGenerator.generate(0.3,5,0,0);
        assertTrue(WorldManager.getInstance().getCells().isEmpty());

        WorldGenerator.generate(0.4,0,4,0);
        assertTrue(WorldManager.getInstance().getCells().isEmpty());

        WorldGenerator.generate(0.7,0,0,5);
        assertTrue(WorldManager.getInstance().getCells().isEmpty());

        WorldGenerator.generate(0.7,0,3,5);
        assertTrue(WorldManager.getInstance().getCells().isEmpty());

        WorldGenerator.generate(0.7,3,0,5);
        assertTrue(WorldManager.getInstance().getCells().isEmpty());

        WorldGenerator.generate(0.7,90,80,0);
        assertTrue(WorldManager.getInstance().getCells().isEmpty());
    }

    @Test(expected = InvalidDensityException.class)
    public void testInvalidDensityExceptionNegativeDensity() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(-0.1, 0, 0, 0);
        fail();
    }

    @Test(expected = InvalidDimensionException.class)
    public void testInvalidDimensionExceptionX() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.4, -1, 0, 0);
        fail();
    }

    @Test(expected = InvalidDimensionException.class)
    public void testInvalidDimensionExceptionY() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.1, 0, -2, 0);
        fail();
    }

    @Test(expected = InvalidDimensionException.class)
    public void testInvalidDimensionExceptionZ() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.7, 0, 0, -3);
        fail();
    }

    @Test(expected = InvalidDimensionException.class)
    public void testInvalidDimensionExceptionXY() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.4, -1, -3, 0);
        fail();
    }

    @Test(expected = InvalidDimensionException.class)
    public void testInvalidDimensionExceptionXZ() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.4, -1, 0, -3);
        fail();
    }

    @Test(expected = InvalidDimensionException.class)
    public void testInvalidDimensionExceptionYZ() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.4, 0, -1234, -215);
        fail();
    }

    @Test(expected = InvalidDimensionException.class)
    public void testInvalidDimensionExceptionXYZ() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.4, -1, -41, -123);
        fail();
    }

    @Test(expected = InvalidDensityException.class)
    public void testInvalidDensityExceptionLargeDensity() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(1.1, 0, 0, 0);
        fail();
    }

    @Test
    public void testGenerateOneDimensionX() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.5,100,1,1);
        assertEquals(50, WorldManager.getInstance().getCells().size());
    }

    @Test
    public void testGenerateOneDimensionY() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.77,1,100,1);
        assertEquals(WorldManager.getInstance().getCells().size(), 77);
    }

    @Test
    public void testGenerateOneDimensionZ() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.911,1,1,100);
        assertEquals(WorldManager.getInstance().getCells().size(), 91);
    }

    @Test
    public void testGenerateTwoDimensionsXY() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.915,10,10,1);
        assertEquals(WorldManager.getInstance().getCells().size(), 91);
    }

    @Test
    public void testGenerateTwoDimensionsXZ() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.919,10,1,10);
        assertEquals(WorldManager.getInstance().getCells().size(), 91);
    }

    @Test
    public void testGenerateTwoDimensionsYZ() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.01,1,10,10);
        assertEquals(WorldManager.getInstance().getCells().size(), 1);
    }

    @Test
    public void testGenerateThreeDimensionsXYZ() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.9321,100,50,150);
        assertEquals(WorldManager.getInstance().getCells().size(), (int) (0.9321 * 100 * 50 * 150));
    }

    @Test
    public void testGenerateBoundaries() throws InvalidDensityException, InvalidDimensionException {
        WorldGenerator.generate(0.9321,100,50,150);
        int maxX, minX, maxY, minY, maxZ, minZ;
        maxX = minX = maxY = minY = maxZ = minZ = 0;

        Set<Cell> cells = WorldManager.getInstance().getCells();

        for (Cell cell : cells) {
            int x = cell.getPosition().x;
            int y = cell.getPosition().y;
            int z = cell.getPosition().z;

            maxX = (x > maxX) ? x : maxX;
            maxY = (y > maxY) ? y : maxY;
            maxZ = (z > maxZ) ? z : maxZ;

            minX = (x < minX) ? x : minX;
            minY = (y < minY) ? y : minY;
            minZ = (z < minZ) ? z : minZ;
        }

        assertTrue(maxX <= 50);
        assertTrue(maxY <= 25);
        assertTrue(maxZ <= 175);

        assertTrue(minX >= -50);
        assertTrue(minY >= -25);
        assertTrue(minZ >= -175);
    }
}
