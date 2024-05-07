package com.yoplac.model;

import java.util.List;

public class Registro {
    private List<Area> areas;

    public Registro(List<Area> areas) {
        this.areas = areas;
    }

    public List<Area> getAreas() {
        return areas;
    }
}
