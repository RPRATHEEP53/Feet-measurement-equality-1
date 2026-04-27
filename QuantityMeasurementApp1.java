package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * UC2: Feet and Inches measurement equality
 * This class handles the validation and comparison of two separate units.
 */
public class QuantityMeasurementApp1 {

    // --- Inner Class for Feet ---
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Feet feet = (Feet) o;
            return Double.compare(feet.value, value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    // --- Inner Class for Inches ---
    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Inches inches = (Inches) o;
            return Double.compare(inches.value, value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    /**
     * Static method to compare two Feet values as per UC2 Main Flow.
     */
    public static boolean compareFeet(double val1, double val2) {
        Feet f1 = new Feet(val1);
        Feet f2 = new Feet(val2);
        return f1.equals(f2);
    }

    /**
     * Static method to compare two Inches values as per UC2 Main Flow.
     */
    public static boolean compareInches(double val1, double val2) {
        Inches i1 = new Inches(val1);
        Inches i2 = new Inches(val2);
        return i1.equals(i2);
    }

    public static void main(String[] args) {
        // Output for Feet
        System.out.println("Input 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + compareFeet(1.0, 1.0) + ")");

        // Output for Inches
        System.out.println("\nInput 1.0 in and 1.0 in");
        System.out.println("Output: Equal (" + compareInches(1.0, 1.0) + ")");

        // Type Safety Check: Comparing Feet object to Inches object
        Feet feet = new Feet(1.0);
        Inches inches = new Inches(1.0);
        System.out.println("\nComparing Feet object to Inches object: " + feet.equals(inches));
    }
}