# FIXMessenger

FIXMessenger is a robust, Swing-based QuickFIX/J application designed for testing and simulating FIX (Financial Information eXchange) protocol messaging. It supports both **Initiator** and **Acceptor** modes, allowing users to send and receive custom FIX messages using dynamic XML-based templates.

## Key Features

*   **Dual Mode**: Operates as a FIX Initiator (client) or Acceptor (server).
*   **Dynamic Message Templates**: Define FIX messages using XML templates (`*.xml` and `.xsd`).
*   **Swing UI**: Graphical interface for session management, message composition, and log viewing.
*   **Project Management**: Organize your test configurations and message libraries into projects (`.xml` files).
*   **QuickFIX/J Integration**: Built on top of the industry-standard QuickFIX/J engine.

## Quick Start

### Prerequisites
*   Java Development Kit (JDK) 1.8 or higher.
*   Gradle.

### Building the Project
Clone the repository and build using Gradle:

```bash
gradle clean build
```

This will compile the code, generate JAXB classes from the XSD schemas, and create the distribution.

### Running the Application

After building, the application scripts are generated in the `build/install/FIXMessanger/` directory (or similar, depending on Gradle output).

**To run as an Initiator:**
```bash
./bin/FIXMessanger config/initiator.cfg
```

**To run as an Acceptor:**
```bash
./bin/FIXMessanger config/acceptor.cfg
```

(Note: Ensure you point to valid configuration files).

## Architecture

The project is structured around the `com.sachin` package (refactored from `com.jramoyo`).
*   **Core**: `com.sachin.qfixmessenger.QFixMessenger` is the main entry point.
*   **UI**: Swing components in `com.sachin.qfixmessenger.ui`.
*   **FIX Engine**: Wraps QuickFIX/J `SocketInitiator` and `SocketAcceptor`.
*   **XML Parser**: Uses JAXB to parse message templates defined in `src/main/xsd`.

For more details, see [ARCHITECTURE.md](ARCHITECTURE.md).

## Author
Original code copyright Jan Amoyo. Refactored and maintained by Sachin.