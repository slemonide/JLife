package model;

import java.util.Collection;
import java.util.Set;

/**
 * @author      Danil Platonov <slemonide@gmail.com>
 * @version     0.1
 * @since       0.1
 *
 * Represents a cell that modify world.
 */
public abstract class ActiveCell extends Cell {
    /**
     * Create a cell at the given position
     *
     * @param position position of this node
     */
    ActiveCell(Position position) {
        super(position);
        setName("Active Cell");
    }

    /**
     * Called every new generation
     */
    public abstract void tick();

    /**
     * @return cells to be added in the next tick
     */
    public abstract Collection<? extends Cell> tickToAdd();

    /**
     * @return positions to be cleaned up in the next tick
     */
    public abstract Collection<? extends Position> tickToRemove();
}
