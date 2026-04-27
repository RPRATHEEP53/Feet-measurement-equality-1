package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * UC4: Extended Unit Support
 * This class uses an enum-based strategy to handle multiple units
 * (Feet, Inches, Yards, Centimeters) and their conversion logic.
 */
public class QuantityMeasurementApp1 {

    // --- Enum to handle Unit Conversion Factors ---
    public enum Unit {
        FEET(12.0),           // Base unit is Inches (1 Foot = 12 Inches)
        INCHES(1.0),          // 1 Inch = 1 Inch
        YARDS(36.0),          // 1 Yard = 3 Feet = 36 Inches
        CENTIMETERS(0.393701);// 1 cm = 0.393701 Inches

        public final double conversionFactor;

        Unit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }
    }

    // --- Generic Quantity Class ---
    public static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Quantity that = (Quantity) o;

            // Convert both values to a common base unit (Inches) for comparison
            double value1InInches = this.value * this.unit.conversionFactor;
            double value2InInches = that.value * that.unit.conversionFactor;

            // Use a small epsilon for floating point comparison to handle rounding
            return Math.abs(value1InInches - value2InInches) < 0.01;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, unit);
        }
    }

    // --- Static Comparison Method (Main Flow) ---
    public static boolean compare(double val1, Unit unit1, double val2, Unit unit2) {
        Quantity q1 = new Quantity(val1, unit1);
        Quantity q2 = new Quantity(val2, unit2);
        return q1.equals(q2);
    }

    public static void main(String[] args) {
        // Examples from your UC4 requirement sheet
        System.out.println("1.0 Yard == 3.0 Feet: " + compare(1.0, Unit.YARDS, 3.0, Unit.FEET));
        System.out.println("1.0 Yard == 36.0 Inches: " + compare(1.0, Unit.YARDS, 36.0, Unit.INCHES));
        System.out.println("1.0 Foot == 12.0 Inches: " + compare(1.0, Unit.FEET, 12.0, Unit.INCHES));
        System.out.println("1.0 CM == 0.3937 Inches: " + compare(1.0, Unit.CENTIMETERS, 0.393701, Unit.INCHES));
    }
}