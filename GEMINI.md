# GEMINI.md - Project Context

## Project Overview

This is a Java project for practicing and storing solutions for [AtCoder](https://atcoder.jp/) competitive programming problems. The project is configured for the IntelliJ IDEA IDE.

The architecture is designed for clear organization of solutions. Code is stored in the `src` directory and organized hierarchically by contest type (e.g., `abc`, `arc`) and then by contest session number (e.g., `abc419`).

A system of `README.md` files provides a navigable structure, allowing for easy browsing from the project root down to specific contest folders.

## Building and Running

This project is managed by IntelliJ IDEA, and the intended workflow is to build and run files directly within the IDE.

For command-line operations, the following process can be used:

1.  **Navigate to the problem:** Place your `Main.java` file in the appropriate directory, for example: `src/contests/abc/abc419/A/Main.java`.

2.  **Compile the code:** From the project root, compile the Java file into the `out` directory.
    ```bash
    javac -d out src/contests/abc/abc419/A/Main.java
    ```

3.  **Run the solution:** Execute the compiled `Main` class from the `out` directory.
    ```bash
    java -cp out Main
    ```

## Development Conventions

- **Directory Structure:** All solutions should be placed in `src/contests` following the `[type]/[contest_number]/[problem_letter]/Main.java` pattern.
- **Templates:** A template `Main.java` file is located in `src/templates` and should be used as a starting point for new solutions.
- **Navigation:** The `README.md` files in the root, `src/contests`, and `src/contests/[type]` directories should be updated as new contests are added to maintain easy navigation.
