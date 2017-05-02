package model.landscape;

import model.ActiveCell;
import model.Cell;
import model.Position;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Generates landscape
 */
public class LandscapeCell extends ActiveCell {
    private Random random = new Random();

    /**
     * Create a cell at the given position
     *
     * @param position position of this node
     */
    public LandscapeCell(Position position) {
        super(position);
    }

    @Override
    public void tick() {

    }

    @Override
    public Collection<? extends Cell> tickToAdd() {
        Set<Cell> nextCells = new HashSet<>();
        nextCells.add(new Cell(position));
        nextCells.add(new LandscapeCell(position.add(nextPosition())));

        return nextCells;
    }

    @Override
    public Collection<? extends Position> tickToRemove() {
        return null;
    }

    private Position nextPosition() {
        switch (random.nextInt(6)) {
            case 0:
                return new Position(1, 0, 0);
            case 1:
                return new Position(-1, 0, 0);
            case 2:
                return new Position(1, 1, 0);
            case 3:
                return new Position(-1, 1, 0);
            case 4:
                return new Position(1, -1, 0);
            default:
                return new Position(-1, -1, 0);
        }
    }
}
