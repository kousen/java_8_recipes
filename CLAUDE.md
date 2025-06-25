# Claude Development Documentation

This document contains important information for Claude AI assistant when working on this project.

## Project Overview

This is the source code repository for "Modern Java Recipes" (O'Reilly, 2017). It's a Java project showcasing Java 8+ features and patterns, recently modernized with current tooling and testing frameworks.

## Build System & Dependencies

### Core Technologies
- **Language**: Java 17 (using Gradle toolchain)
- **Build Tool**: Gradle 8.14.2 (latest stable)
- **Testing**: JUnit 5.13.2 (fully migrated from JUnit 4)
- **CI**: GitHub Actions

### Key Dependencies
- `org.junit.jupiter:junit-jupiter` - Main testing framework
- `org.junit.platform:junit-platform-launcher` - Required for test execution
- `org.mockito:mockito-junit-jupiter` - Mocking framework
- `org.assertj:assertj-core` - Fluent assertions
- JMH for benchmarking tests

### Dependency Management
- Uses Gradle version catalogs (`gradle/libs.versions.toml`)
- JUnit BOM manages all JUnit component versions consistently
- No JUnit vintage engine (fully migrated to JUnit 5)

## Project Structure

```
src/
├── main/java/          # Main source code with Java 8+ examples
├── test/java/          # JUnit 5 test suite (24+ test files)
gradle/
├── libs.versions.toml  # Version catalog for dependencies
└── wrapper/           # Gradle wrapper files
```

## Recent Modernization Work

### Major Updates Completed
1. **Gradle Upgrade**: 7.5.1 → 8.14.2
2. **Java Version**: 11 → 17 (with toolchain support)
3. **JUnit Migration**: Complete JUnit 4 → JUnit 5 migration
4. **Build Modernization**: Added version catalogs, BOM management

### Testing Framework Migration
- **All test files migrated** (24+ files) from JUnit 4 to JUnit 5
- **Patterns updated**:
  - `@Test(expected = Exception.class)` → `assertThrows(Exception.class, () -> {...})`
  - `@Before/@After` → `@BeforeEach/@AfterEach`
  - `@Ignore` → `@Disabled`
  - `org.junit.Assert.*` → `org.junit.jupiter.api.Assertions.*`
- **Removed dependencies**: junit-vintage-engine (no longer needed)

## Common Development Tasks

### Running Tests
```bash
# All tests
./gradlew test

# Specific test class
./gradlew test --tests "concurrency.CompletableFutureTests"

# With detailed output
./gradlew test --info
```

### Building
```bash
# Clean build
./gradlew clean build

# Just compile
./gradlew compileJava compileTestJava
```

## Important Notes for Claude

### When Working with Tests
- All tests use JUnit 5 - no JUnit 4 imports should be added
- Use `assertThrows()` for exception testing, not `@Test(expected = ...)`
- Use `@BeforeEach/@AfterEach` instead of `@Before/@After`
- Use `@Disabled` instead of `@Ignore`

### Code Patterns to Follow
- Java 17 features are available but code maintains Java 8+ examples
- Use modern testing patterns (JUnit 5, AssertJ)
- Follow existing package structure and naming conventions

### Dependencies
- When adding test dependencies, use the version catalog in `gradle/libs.versions.toml`
- Ensure JUnit BOM manages version consistency
- Avoid adding junit-vintage-engine unless absolutely necessary

### GitHub Actions
- CI runs on Java 17
- Builds with `./gradlew build`
- All tests must pass for successful builds

## Known Issues/Considerations

### Test Execution
- Some tests are `@Disabled` due to CI environment issues
- Concurrency tests may be timing-sensitive
- All core functionality tests pass consistently

### Compatibility
- Project maintains Java 8+ example compatibility while using Java 17 for builds
- Modern Gradle version provides better performance and features
- JUnit 5 provides better test organization and modern features

## Files to Monitor

### Critical Configuration Files
- `build.gradle` - Main build configuration
- `gradle/libs.versions.toml` - Dependency versions
- `gradle/wrapper/gradle-wrapper.properties` - Gradle version
- `.github/workflows/gradle.yml` - CI configuration

### Test Directory Structure
- All test files in `src/test/java/` use JUnit 5
- Test organization follows main source package structure
- Both unit tests and integration examples present

This documentation helps ensure consistent development practices and maintains the project's modernized state.

## Java Project Modernization Migration Guide

This section documents the complete process used to modernize this project, which can be applied to other Java projects.

### Prerequisites
- Existing Java project with Gradle build system
- JUnit 4 test suite to migrate
- Access to modify build configuration

### Step 1: Upgrade Gradle to Latest Version

```bash
# Update gradle wrapper properties
# Edit gradle/wrapper/gradle-wrapper.properties
distributionUrl=https\://services.gradle.org/distributions/gradle-8.14.2-bin.zip

# Update wrapper
./gradlew wrapper --gradle-version=8.14.2
```

### Step 2: Upgrade Java Version with Toolchain Support

In `build.gradle`, replace:
```gradle
sourceCompatibility = JavaVersion.VERSION_11
```

With:
```gradle
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
```

Update GitHub Actions (`.github/workflows/*.yml`):
```yaml
- name: Set up JDK 17
  uses: actions/setup-java@v1
  with:
    java-version: 17
```

### Step 3: Update JUnit Dependencies with BOM

In `gradle/libs.versions.toml` (or create if using traditional dependency management):

```toml
[versions]
junit = "5.13.2"

[libraries]
junit-jupiter = { module = "org.junit.jupiter:junit-jupiter" }
# Remove junit-vintage from bundles

[bundles]
junit = [
    "junit-jupiter",
    # Remove "junit-vintage" line
]
```

In `build.gradle`:
```gradle
dependencies {
    // JUnit BOM for consistent versions
    testImplementation platform("org.junit:junit-bom:${libs.versions.junit.get()}")
    
    // JUnit bundle (without vintage engine)
    testImplementation libs.bundles.junit
    
    // Explicit JUnit Platform Launcher (required for Gradle)
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
    
    // Other test dependencies...
}
```

### Step 4: Identify JUnit 4 Test Files

Find all files needing migration:
```bash
find src/test -name "*.java" -exec grep -l "import org.junit.Test" {} \;
find src/test -name "*.java" -exec grep -l "@Test(expected" {} \;
find src/test -name "*.java" -exec grep -l "@Ignore" {} \;
```

### Step 5: Migrate Test Files - Batch Operations

**Update basic imports:**
```bash
find src/test -name "*.java" -exec sed -i '' 's/import org\.junit\.Test;/import org.junit.jupiter.api.Test;/g' {} \;
find src/test -name "*.java" -exec sed -i '' 's/import org\.junit\.Before;/import org.junit.jupiter.api.BeforeEach;/g' {} \;
find src/test -name "*.java" -exec sed -i '' 's/import org\.junit\.After;/import org.junit.jupiter.api.AfterEach;/g' {} \;
find src/test -name "*.java" -exec sed -i '' 's/import org\.junit\.Ignore;/import org.junit.jupiter.api.Disabled;/g' {} \;
```

**Update assertion imports:**
```bash
find src/test -name "*.java" -exec sed -i '' 's/import static org\.junit\.Assert\.\*/import static org.junit.jupiter.api.Assertions.*;/g' {} \;
find src/test -name "*.java" -exec sed -i '' 's/import static org\.junit\.Assert\./import static org.junit.jupiter.api.Assertions./g' {} \;
```

**Update annotations:**
```bash
find src/test -name "*.java" -exec sed -i '' 's/@Before/@BeforeEach/g' {} \;
find src/test -name "*.java" -exec sed -i '' 's/@After/@AfterEach/g' {} \;
find src/test -name "*.java" -exec sed -i '' 's/@Ignore/@Disabled/g' {} \;
```

### Step 6: Manual Migration for Complex Patterns

For files with `@Test(expected = ...)` patterns, manually update:

**Before (JUnit 4):**
```java
@Test(expected = IllegalArgumentException.class)
public void testInvalidInput() {
    someMethod("invalid");
}
```

**After (JUnit 5):**
```java
@Test
public void testInvalidInput() {
    assertThrows(IllegalArgumentException.class, () -> someMethod("invalid"));
}
```

Add import: `import static org.junit.jupiter.api.Assertions.assertThrows;`

### Step 7: Remove Vintage Engine and Test

Remove vintage engine from dependencies (if present):
```gradle
// Remove this line from bundles or dependencies:
// testImplementation 'org.junit.vintage:junit-vintage-engine'
```

Test the migration:
```bash
./gradlew clean test
```

### Step 8: Verification Checklist

- [ ] All tests compile and run
- [ ] No JUnit 4 imports remain
- [ ] All `@Test(expected = ...)` converted to `assertThrows()`
- [ ] All `@Before/@After` converted to `@BeforeEach/@AfterEach`
- [ ] All `@Ignore` converted to `@Disabled`
- [ ] JUnit vintage engine removed from dependencies
- [ ] GitHub Actions updated for new Java version
- [ ] README.md updated with new requirements

### Common Issues and Solutions

**Issue**: Tests fail with "OutputDirectoryProvider not available"
**Solution**: Add explicit `junit-platform-launcher` dependency

**Issue**: `@BeforeEachEach` instead of `@BeforeEach`
**Solution**: Fix with: `sed -i '' 's/@BeforeEachEach/@BeforeEach/g'`

**Issue**: Double semicolons in imports
**Solution**: Fix with: `sed -i '' 's/;;/;/g'`

### Migration Time Estimate

- Small project (< 10 test files): 30-60 minutes
- Medium project (10-50 test files): 1-3 hours  
- Large project (50+ test files): 3-6 hours

Most time is spent on manual `@Test(expected = ...)` conversions and verification.

### Files to Update After Migration

1. **README.md** - Update Java version requirements
2. **CLAUDE.md** - Document the new tech stack
3. **GitHub Actions** - Update Java version
4. **IDE configs** - Update if project-specific Java version settings exist

This process ensures a complete, modern Java project with the latest testing frameworks while maintaining all existing functionality.