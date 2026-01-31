# Developer Guide

## Development Environment Setup

To build and extend FIXMessenger, you need:

*   **Java**: JDK 1.8 or compatible (tested with JDK 1.8, source level 1.8).
*   **Gradle**: A recent version (compatible with the `build.gradle` script).
*   **IDE**: IntelliJ IDEA or Eclipse (project files for both are generated/supported).

## Building from Source

The project uses Gradle for build automation.

### Key Gradle Tasks available

*   `clean`: Removes the `build` directory and other generated artifacts.
*   `xjc`: Generates Java classes from the XML schemas located in `src/main/xsd`. **Note**: This task relies on Ant's XJC and requires the JAXB libraries to be on the classpath.
*   `generateSource`: Depends on `xjc`.
*   `compileJava`: Compiles the source code, including the generated JAXB classes.
*   `build`: Full build including tests and packaging.
*   `installDist`: Creates the application distribution in `build/install`.

### Command to Build
```bash
gradle clean build
```

## Project Structure

The codebase has been refactored to the `com.sachin` package structure.

```text
src/
├── main/
│   ├── java/com/sachin/      # Java source code
│   │   ├── qfixmessenger/    # Main app core
│   │   ├── fix/              # Generated classes (after build)
│   ├── xsd/                  # XML Schemas (message.xsd, project.xsd)
│   ├── cfg/                  # Configuration templates (log4j, app.cfg)
│   ├── dictionary/           # FIX Dictionaries (FIX40.xml, etc.)
│   └── scripts/              # Launch scripts
└── test/                     # Unit tests
```

## Extending the Application

### Adding New Fields/Messages
1.  Navigate to `src/main/xsd`.
2.  Modify `message.xsd` if you need to change the meta-model of the XML templates.
3.  Run `gradle xjc` to regenerate the binding classes.

### Modifying the UI
The UI is built with Swing. Main entry point is `QFixMessengerFrame`. New panels can be added to `com.sachin.qfixmessenger.ui.panels`.

### Troubleshooting Build Issues
If you encounter `sourceCompatibility` errors, ensure your Gradle version matches the syntax in `build.gradle`. The project targets Java 1.8.
