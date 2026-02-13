# Design Decisions

This file matches what is actually implemented in the code.

## 1) Layered Structure

- **Main.java**: console menu and input
- **service**: business logic
- **entity**: data objects
- **util**: helpers
- **exception / enums / interfaces / constants / test**: supporting parts

This ensures separation of concerns.

## 2) OOP Choices

- **Inheritance**: `Person` → `Doctor`, `Patient`
- **Abstraction**: `MedicalEntity` is a base class
- **Encapsulation**: fields are private, access with getters
- **Interface**: `Payable` for bill calculation

## 3) Singleton

- `IdGenerator` is a Singleton to generate unique IDs in one place.

## 4) Generics

- `DataStore<T>` is a generic in‑memory store for different entities.

## 5) Validation

- `Validator` checks name and age in one place.

## 6) Enums

- `Specialization` and `AppointmentStatus` prevent invalid strings.

## 7) Streams & Lambdas

- Used in services for searching and averages.

## 8) Immutability

- `BillSummary` has final fields and no setters.

## 9) Cloning

- `Patient` supports cloning (shallow copy).
- `Appointment` clones its `Patient` to avoid shared reference issues.

## Service Layer Design

### Responsibilities

**DoctorService:**
- Doctor CRUD operations
- Calculate average consultation fee
- Business logic for doctors

**PatientService:**
- Patient CRUD operations
- Search patients by name
- Business logic for patients

**AppointmentService:**
- Appointment lifecycle management
- Confirm/Cancel operations
- Prevent invalid state transitions

## Enums Usage

### AppointmentStatus

```java
public enum AppointmentStatus {
    PENDING, CONFIRMED, CANCELLED
}
```
- **Type safety**: Cannot assign invalid status
- **Readability**: Clear intent in code

### Specialization

```java
public enum Specialization {
    GENERAL, CARDIOLOGY, DERMATOLOGY, ORTHOPEDICS, NEUROLOGY
}
```
- **Predefined options**: Limited valid specializations
- **Consistency**: Same specialization names across application
- **Extensibility**: Easy to add new specializations

## Conclusion

MediTrack is a modular, object-oriented Clinic & Appointment Management System implemented in Core Java. The system models patients, doctors, appointments, and billing; demonstrates strong OOP design, SOLID principles, standard Java features (collections, exceptions, I/O, serialization), and optional advanced topics (concurrency, design patterns, streams, cloning). 

**Key Strengths:**
- Clear layered architecture
- Appropriate design patterns
- Strong encapsulation and validation
- Extensible and maintainable codebase

**Learning Outcomes:**
- Practical application of OOP principles
- Design pattern implementation
- Exception handling best practices
- Clean code organization
