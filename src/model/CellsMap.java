package model;

import javafx.geometry.Pos;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author      Danil Platonov <slemonide@gmail.com>
 * @version     0.1
 * @since       0.1
 *
 * Maps positions to cells
 */
public class CellsMap implements Map<Position, Cell> {
    Map<Position, Cell> staticCells;
    Map<Position, ActiveCell> activeCells;

    public CellsMap() {
        staticCells = Collections.synchronizedMap(new HashMap<>());
        activeCells = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public int size() {
        return (staticCells.size() + activeCells.size());
    }

    @Override
    public boolean isEmpty() {
        return (staticCells.isEmpty() && activeCells.isEmpty());
    }

    @Override
    public boolean containsKey(Object key) {
        return (staticCells.containsKey(key) && activeCells.containsKey(key));
    }

    @Override
    public boolean containsValue(Object value) {
        return (staticCells.containsValue(value) && activeCells.containsValue(value));
    }

    @Override
    public Cell get(Object key) {
        return staticCells.getOrDefault(key, activeCells.get(key));
    }

    @Override
    public Cell put(Position key, Cell value) {
        Cell previousValue = get(key);

        if (value instanceof ActiveCell) {
            activeCells.put(key, (ActiveCell) value);
        } else {
            staticCells.put(key, value);
        }

        return previousValue;
    }

    @Override
    public Cell remove(Object key) {
        Cell previousValue = get(key);

        activeCells.remove(key);
        staticCells.remove(key);

        return previousValue;
    }

    @Override
    public void putAll(Map<? extends Position, ? extends Cell> m) {
        for (Position position : m.keySet()) {
            put(position, m.get(position));
        }
    }

    @Override
    public void clear() {
        staticCells.clear();
        activeCells.clear();
    }

    @Override
    public Set<Position> keySet() {
        Set<Position> keySet = new HashSet<>();
        synchronized (staticCells) {
            keySet.addAll(staticCells.keySet());
        }
        synchronized (activeCells) {
            keySet.addAll(activeCells.keySet());
        }

        return keySet;
    }

    @Override
    public Collection<Cell> values() {
        Collection<Cell> valueSet = new HashSet<>();
        valueSet.addAll(staticCells.values());
        valueSet.addAll(activeCells.values());

        return valueSet;
    }

    @Override
    public Set<Entry<Position, Cell>> entrySet() {
        Set<Entry<Position, Cell>> entrySet = new HashSet<>();
        entrySet.addAll(staticCells.entrySet());

        for (Position position : activeCells.keySet()) {
            entrySet.add(new AbstractMap.SimpleEntry<>(position, activeCells.get(position)));
        }

        return entrySet;
    }

    public void removeAll(Set<Position> toRemove) {
        for (Position position : toRemove) {
            remove(position);
        }
    }

    public Set<Position> activeCellsKeySet() {
        return activeCells.keySet();
    }

    public Set<Position> staticCellsKeySet() {
        return staticCells.keySet();
    }

    public Collection<ActiveCell> activeCellsValues() {
        return activeCells.values();
    }

    public void addAll(Set<Cell> cells) {
        for (Cell cell : cells) {
            put(cell.getPosition(), cell);
        }
    }
}
