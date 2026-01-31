# Architecture Documentation

## System Overview

FIXMessenger follows a layered architecture integrating a Swing-based user interface with the QuickFIX/J messaging engine. It allows users to interactively control FIX sessions and send messages defined via XML templates.

```mermaid
graph TD
    UI[Swing UI Layer] -->|Controls| App[QFixMessenger Application]
    App -->|Manages| Connector[FIX Connector (Initiator/Acceptor)]
    Connector -->|Uses| QFJ[QuickFIX/J Engine]
    App -->|Parses| Parser[Dictionary & XML Parser]
    Parser -->|Reads| XSD[XML Schemas]
    Parser -->|Reads| DCT[FIX Dictionary]
```

## Core Components

### 1. Main Application (`QFixMessenger`)
Located in `com.sachin.qfixmessenger.QFixMessenger`.
*   **Responsibility**: Bootstraps the application, loads configuration, initializes the Spring context (if applicable) or JAXB context, and launches the UI.
*   **Lifecycle**: Manages the start/stop of the QuickFIX/J connector (`SocketInitiator` or `SocketAcceptor`).

### 2. UI Layer (`com.sachin.qfixmessenger.ui`)
*   **`QFixMessengerFrame`**: The main application window. Contains panels for project management, message viewing, and session logs.
*   **`ProjectDialog`**: Manages the loading/saving of project files which group message templates.

### 3. QuickFIX/J Integration (`com.sachin.qfixmessenger.quickfix`)
*   **`QFixMessengerApplication`**: Implements `quickfix.Application`. Handles callbacks for `onCreate`, `onLogon`, `onLogout`, `toAdmin`, `fromAdmin`, `toApp`, and `fromApp`.
*   **`QFixDictionaryParser`**: Parses the standard FIX data dictionary (e.g., `FIX44.xml`) to understand valid fields and message structures.

### 4. XML Message Templates (`com.sachin.fix.xml`)
The application allows dynamic message creation using XML files validated against `message.xsd`.
*   **Namespace**: `http://xml.fix.sachin.com`
*   **JAXB**: Java Architecture for XML Binding is used to unmarshal these XML templates into Java objects (`MessageType`, `HeaderType`, `BodyType`), which are then converted into QuickFIX `Message` objects.

## Data Flow

1.  **Startup**: App loads `app.cfg` and `quickfix.cfg`. Initializes JAXB context for `com.sachin.fix.xml`.
2.  **Session Start**: Initiator connects to target; Acceptor listens for connections.
3.  **Message Composition**:
    *   User selects a template from the UI.
    *   App reads the XML template.
    *   `QFixMessenger` converts the JAXB object tree into a `quickfix.Message`.
    *   Message is populated with default fields and user-overridden values.
4.  **Sending**: The `quickfix.Session.send()` method is invoked to transmit the message.

## Directory Structure

*   `src/main/java`: Source code.
*   `src/main/xsd`: XML Schemas for message templates (`message.xsd`, `project.xsd`).
*   `src/main/cfg`: Default configurations.
*   `src/main/resources`: Images and icons.
