package com.yoplac.model;

import java.util.Collection;

public class Registro {
    private Collection<Area> areas;

    public Registro(Collection<Area> areas) {
        this.areas = areas;
    }

    public Collection<Area> getAreas() {
        return areas;
    }
}
