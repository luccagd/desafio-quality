package com.example.desafioquality.unit;

import java.math.BigDecimal;
import java.util.Objects;

public class FakeDistrict {
    private String name;

    private BigDecimal squareMeterPrice;

    public FakeDistrict(String name, BigDecimal squareMeterPrice) {
        this.name = name;
        this.squareMeterPrice = squareMeterPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FakeDistrict that = (FakeDistrict) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
