package server.rulesets;

import server.model.Cell;
import server.model.Vector3;

import java.util.Set;

/**
 * @author      Danil Platonov <slemonide@gmail.com>
 * @version     0.1
 * @since       0.1
 *
 * Interface for all rule sets
 */
public interface RuleSet {

    public void tick();

    Set<Cell> getCells();

    void add(Cell cell);

    Set<Vector3> getNeighbourhood();

    void setLowerBound(int i);

    void setUpperBound(int i);

    void clear();

    void remove(Cell cell);

    void setNeighbourhood(Set<Vector3> neighbourhood);
}