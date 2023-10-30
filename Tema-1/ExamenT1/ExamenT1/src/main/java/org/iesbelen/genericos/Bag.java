package org.iesbelen.genericos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Bag<T> {

    private final List<T> values;

    public Bag() {
        this.values = new ArrayList<>();
    }

    public boolean add(T e) {
        return values.add(e);
    }

    public boolean add(T e, Integer n) {
        int iterator = 0;
        while (iterator < n) {
            iterator++;
            values.add(e);
        }
        return true;
    }

    public Long getCount(T e) {
        return values.stream()
                .filter(element -> element.equals(e))
                .count();
    }

    public boolean remove(T e) {
        return values.remove(e);
    }

    public boolean remove(T e, Integer n) {
        long numberOfCopys = getCount(e);
        int iterator = 0;
        long end = n;

        if (numberOfCopys == 0) {
            return false;
        }
        if (numberOfCopys < n) {
            end = numberOfCopys;
        }

        while (iterator < end) {
            values.remove(e);
            iterator++;
        }

        return true;
    }

    public Integer size() {
        return values.size();
    }

    public Set<T> uniqueSet() {
        return new HashSet<>(values);
    }

    @Override
    public String toString() {
        return values.stream()
                .map(T::toString)
                .collect(Collectors
                        .joining("\n", "", values.isEmpty() ? "No hay elementos" : ""));
    }
}
