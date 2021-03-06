package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Helps calculating changes of observed sets of E
 */
public class SetObserver<E> implements CollectionObserver<E> {
    private Set<E> currentSet;
    private Set<E> oldSet;

    public SetObserver(Set<E> currentSet) {
        this.currentSet = currentSet;
        oldSet = new HashSet<>(currentSet);
    }

    @Override
    public Difference<E> getDifference() {
        Set<E> added = new HashSet<>();
        Set<E> removed = new HashSet<>();

        added.addAll(currentSet);
        added.removeAll(oldSet);

        removed.addAll(oldSet);
        removed.removeAll(currentSet);

        oldSet.clear();
        oldSet.addAll(currentSet);

        return new Difference<>(added, removed, new HashMap<>());
    }
}
