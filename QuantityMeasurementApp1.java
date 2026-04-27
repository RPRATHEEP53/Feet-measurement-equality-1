package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * QuantityMeasurementApp handles equality, conversion, and addition
 * across different categories (Length and Volume).
 */
public class QuantityMeasurementApp1
{

    // --- Enum for Unit Conversion Factors ---
    public enum Unit {
        // Length Units (Base: Inches)
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701),

        // Volume Units (Base: Litres)
        GALLON(3.785),
        LITRE(1.0),
        MILLILITRE(0.001);

        private final double conversionFactor;

        Unit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double convertToBase(double value) {
            return value * this.conversionFactor;
        }
    }

    // --- Core Quantity Class (Value Object) ---
    public static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        /**
         * UC7: Addition Logic
         * Adds another quantity and returns a NEW Quantity object
         * in the unit of the first operand.
         */
        public Quantity add(Quantity that) {
            if (that == null) throw new IllegalArgumentException("Cannot add null value");

            double totalInBase = this.unit.convertToBase(this.value) +
                    that.unit.convertToBase(that.value);

            // Convert back to the unit of the first operand
            double resultValue = totalInBase / this.unit.conversionFactor;
            return new Quantity(resultValue, this.unit);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Quantity quantity = (Quantity) o;

            double v1 = this.unit.convertToBase(this.value);
            double v2 = quantity.unit.convertToBase(quantity.value);

            // Handle precision with epsilon 0.01
            return Math.abs(v1 - v2) < 0.01;
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

    public static void main(String[] args) {
        System.out.println("--- UC7: Volume Addition Results ---");

        // Example: 1.0 Gallon + 3.785 Litres = 2.0 Gallons
        Quantity q1 = new Quantity(1.0, Unit.GALLON);
        Quantity q2 = new Quantity(3.785, Unit.LITRE);
        System.out.println("1.0 Gal + 3.785 L = " + q1.add(q2));

        // Example: 1.0 Litre + 1000 Millilitres = 2.0 Litres
        Quantity q3 = new Quantity(1.0, Unit.LITRE);
        Quantity q4 = new Quantity(1000.0, Unit.MILLILITRE);
        System.out.println("1.0 L + 1000 ml = " + q3.add(q4));
    }
}