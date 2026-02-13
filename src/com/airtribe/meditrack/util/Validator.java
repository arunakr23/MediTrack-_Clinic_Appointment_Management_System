package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;

public class Validator {
    
    public static void validateName(String  name) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Invalid name. Please try again!");
        }
        if (name.matches(".*\\d.*")) {
            throw new InvalidDataException("Invalid name. Name cannot contain numbers!");
        }
    }

    public static void validateAge(int age) throws InvalidDataException {
        if (age <= 0 || age > 120) {
            throw new InvalidDataException("Invalid age. Please enter a valid age between 1 and 120.");
        
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
