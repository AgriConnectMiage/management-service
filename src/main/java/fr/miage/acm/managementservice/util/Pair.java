package fr.miage.acm.managementservice.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair<X, Y> {
    public final X first;
    public final Y second;

    public Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }
}
