package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;

public class Validator {
    
    public static void validateName(String name) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Invalid name. Please try again!");
        }
        String trimmedName = name.trim();
        if (trimmedName.matches(".*\\d.*")) {
            throw new InvalidDataException("Invalid name. Name cannot contain numbers!");
        }
        if (!trimmedName.matches("^[A-Za-z]+(?:[\\s'-][A-Za-z]+)*$")) {
            throw new InvalidDataException("Invalid name. Use letters with single spaces, hyphens, or apostrophes only.");
        }
    }

    public static void validateAge(int age) throws InvalidDataException {
        if (age <= 0 || age > 120) {
            throw new InvalidDataException("Invalid age. Please enter a valid age between 1 and 120.");
        
        }
    }

    public static void validateIllness(String illness) throws InvalidDataException {
        if (illness == null || illness.trim().isEmpty()) {
            throw new InvalidDataException("Invalid illness. Please provide a valid illness description.");
        }
    }

   /*  public static void validatePhoneNumber(String phoneNumber) throws InvalidDataException {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new InvalidDataException("Invalid phone number. Please enter a 10-digit phone number.");
        }
    }

    public static void validateAddress(String address) throws InvalidDataException {
        if (address == null || address.trim().isEmpty()) {
            throw new InvalidDataException("Invalid address. Please try again!");
        }
    }*/
}
