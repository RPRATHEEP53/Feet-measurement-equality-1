package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * UC1: Feet measurement equality
 * Demonstrates value-based equality for measurements.
 */
public class QuantityMeasurementApp1 {

    // --- Inner Class Structure ---
    public static class Feet {
        private final double value; // Encapsulation & Immutability

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            // 1. Reference Check (reflexive property)
            if (this == o) return true;

            // 2. Null check & Type safety check
            if (o == null || getClass() != o.getClass()) return false;

            // 3. Value comparison using Double.compare for precision
            Feet feet = (Feet) o;
            return Double.compare(feet.value, value) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return value + " ft";
        }
    }

    // --- Main Method to verify logic ---
    public static void main(String[] args) {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        Feet feet3 = new Feet(2.0);

        System.out.println("Comparing 1.0 ft and 1.0 ft: " + feet1.equals(feet2)); // Expected: true
        System.out.println("Comparing 1.0 ft and 2.0 ft: " + feet1.equals(feet3)); // Expected: false
        System.out.println("Comparing 1.0 ft and null: " + feet1.equals(null));   // Expected: false
    }
}
