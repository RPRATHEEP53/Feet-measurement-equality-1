package com.bridgelabz.quantitymeasurement; // Make sure this matches your folder!

import java.util.Objects;

public class QuantityMeasurementApp1 {

    // 1. Define the Unit Types
    public enum UnitType { LENGTH, VOLUME, WEIGHT, TEMPERATURE }

    // 2. Define the Units (Added 'static' to ensure it's resolvable)
    public enum Unit {
        FEET(12.0, 0.0, UnitType.LENGTH),
        INCHES(1.0, 0.0, UnitType.LENGTH),
        YARDS(36.0, 0.0, UnitType.LENGTH),
        CENTIMETERS(0.4, 0.0, UnitType.LENGTH),
        GALLON(3.78, 0.0, UnitType.VOLUME),
        LITRE(1.0, 0.0, UnitType.VOLUME),
        KILOGRAM(1.0, 0.0, UnitType.WEIGHT),
        GRAM(0.001, 0.0, UnitType.WEIGHT),
        CELSIUS(1.0, 0.0, UnitType.TEMPERATURE),
        FAHRENHEIT(1.0, 32.0, UnitType.TEMPERATURE);

        public final double factor;
        public final double offset;
        public final UnitType type;

        Unit(double factor, double offset, UnitType type) {
            this.factor = factor;
            this.offset = offset;
            this.type = type;
        }
    }

    // 3. Define the Quantity Class
    public static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double getBaseValue() {
            if (unit == Unit.FAHRENHEIT) {
                return (value - unit.offset) * 5.0 / 9.0;
            }
            return (value * unit.factor);
        }

        public boolean compare(Quantity that) {
            if (this.unit.type != that.unit.type) return false;
            return Math.abs(this.getBaseValue() - that.getBaseValue()) < 0.1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return compare((Quantity) o);
        }
    }

    public static void main(String[] args) {
        // Test logic
        Quantity feet = new Quantity(1.0, Unit.FEET);
        Quantity inches = new Quantity(12.0, Unit.INCHES);
        System.out.println("Check 1ft == 12in: " + feet.equals(inches));
    }
}