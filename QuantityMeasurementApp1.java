package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * UC5: Extended Unit Support with Explicit Conversion
 * Supports Feet, Inches, Yards, and Centimeters.
 */
public class QuantityMeasurementApp1 {

    // --- Enum with Conversion Factors (Base: Inches) ---
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        // Helper to get value in base unit (Inches)
        public double convertToBase(double value) {
            return value * this.conversionFactor;
        }
    }

    // --- Core Length Class ---
    public static class Length {
        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        /**
         * UC5 Conversion logic:
         * Converts current length to a target unit.
         */
        public double convertTo(LengthUnit targetUnit) {
            double baseValue = this.unit.convertToBase(this.value);
            return baseValue / targetUnit.conversionFactor;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Length other = (Length) o;

            double firstBase = this.unit.convertToBase(this.value);
            double secondBase = other.unit.convertToBase(other.value);

            // Using Epsilon (0.01) to handle floating point precision
            return Math.abs(firstBase - secondBase) < 0.01;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, unit);
        }

        @Override
        public String toString() {
            return String.format("%.2f %s", value, unit);
        }
    }

    // --- API Methods for demonstrate Conversion and Equality ---

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        return new Length(value, source).convertTo(target);
    }

    public static boolean compare(double val1, LengthUnit u1, double val2, LengthUnit u2) {
        return new Length(val1, u1).equals(new Length(val2, u2));
    }

    public static void main(String[] args) {
        System.out.println("--- UC5 Conversion Examples ---");

        // 1.0 Feet to Inches -> Expected 12.0
        System.out.println("1.0 Feet in Inches: " + convert(1.0, LengthUnit.FEET, LengthUnit.INCHES));

        // 3.0 Yards to Feet -> Expected 9.0
        System.out.println("3.0 Yards in Feet: " + convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));

        // 1.0 CM to Inches -> Expected ~0.39
        System.out.println("1.0 CM in Inches: " + convert(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES));

        System.out.println("\n--- Equality Examples ---");
        System.out.println("1.0 Yard == 36.0 Inches: " + compare(1.0, LengthUnit.YARDS, 36.0, LengthUnit.INCHES));
    }
}