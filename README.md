# Java 8 Recipes

Source code for Modern Java Recipes (O'Reilly, 2017)

http://shop.oreilly.com/product/0636920056669.do

## Project Status

This project has been modernized and updated with the latest tooling:

- **Java 17** (using Gradle toolchain support)
- **Gradle 8.14.2** (latest stable)
- **JUnit 5.13.2** (migrated from JUnit 4, using BOM management)
- **Modern build configuration** with dependency management

## Requirements

- Java 17 or later (automatically managed by Gradle toolchain)
- No additional setup required - Gradle wrapper handles everything

## Quick Start

```bash
# Clone the repository
git clone <repository-url>
cd java_8_recipes

# Run tests
./gradlew test

# Build the project
./gradlew build
```

## IDE Setup

### IntelliJ IDEA
1. Clone repo or download zip and extract
2. Use `File -> Open` or if no project is open, `Import`
3. Navigate to the `build.gradle` file inside the project
4. Click enter and accept all the defaults
5. IntelliJ will automatically configure the project with Java 17

### Eclipse (and Eclipse-based tools, like STS)
1. Open a command prompt in the root of the project
2. Type `./gradlew cleanEclipse eclipse` (Unix/Mac) or `gradlew cleanEclipse eclipse` (Windows)
3. Wait for the dependencies to be downloaded
4. Choose `File -> Import… -> General -> Existing Projects into Workspace`
5. Navigate to the root of the project and select it

## Testing

All tests have been migrated to JUnit 5 and use modern testing patterns:

```bash
# Run all tests
./gradlew test

# Run tests with detailed output
./gradlew test --info

# Run specific test class
./gradlew test --tests "concurrency.CompletableFutureTests"
```

## Project Structure

The project contains examples and tests for Java 8+ features including:

- **Concurrency**: CompletableFuture, parallel streams
- **Streams**: Operations, collectors, patterns
- **Optional**: Usage patterns and best practices
- **Date/Time**: Modern java.time API
- **Lambdas**: Functional programming patterns
- **Collectors**: Custom and built-in collectors

## Recent Updates

- Upgraded from Gradle 7.5.1 to 8.14.2
- Migrated from Java 11 to Java 17 (using toolchain)
- Complete migration from JUnit 4 to JUnit 5
- Modernized dependency management with version catalogs
- Removed deprecated JUnit vintage engine dependency
