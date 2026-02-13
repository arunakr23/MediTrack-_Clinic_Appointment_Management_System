# JVM Architecture Report

## 1) Class Loader

The Class Loader is responsible for loading `.class` files into memory.

Types:
- Bootstrap ClassLoader
- Extension ClassLoader
- Application ClassLoader

In MediTrack, all compiled classes are loaded by the Application ClassLoader.

---

## 2) Runtime Data Areas

### Heap
Stores objects created using `new`.
Example:
- Doctor objects
- Patient objects
- Appointment objects

### Stack
Stores:
- Method calls
- Local variables
- Primitive data

Each thread has its own stack.

### Method Area
Stores:
- Class metadata
- Static variables
- Runtime constant pool

### PC Register
Stores the address of the current instruction being executed.

---

## 3) Execution Engine

Responsible for executing bytecode.

Components:
- Interpreter
- JIT Compiler

---

## 4) Interpreter vs JIT Compiler

Interpreter:
- Reads bytecode line-by-line
- Slower execution

JIT Compiler:
- Converts bytecode into native machine code
- Faster performance

---

## 5) Write Once, Run Anywhere (WORA)

Java compiles code into bytecode.
JVM interprets bytecode.
Thus, the same `.class` file runs on:
- Windows
- Mac
- Linux

This is why MediTrack runs on any system with Java installed.
