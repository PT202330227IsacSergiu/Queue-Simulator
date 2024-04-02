# Documentation

## 1. Objective
   The main objective is to design and implement a task management simulator for a number of queues. The number of queues and tasks are input by the user. The simulator displays real-time queue evolution based on user-input data, facilitating simple usage through a graphical interface.  
   
   **Secondary Objectives:**
   - Create a user-friendly graphical interface.
   - Test various simulation scenarios.
   - Develop a simulation object based on GUI data.

## 2. Problem Analysis, Modeling, Use Cases  
   Random clients are generated and distributed to a number of queues based on a specific strategy. These queues are monitored and clients are scheduled by a scheduler, considering each server's wait time. The simulation helps visualize server statuses in real-time. User input via a graphical interface is converted into application variables.
   
## 3. Design  
   - **Model Package**
     - Client Class: Represents task components with ID, arrival time, and service time.
     - Server Class: Implements Runnable for thread usage, manages clients in a queue, and tracks waiting periods.
   - **Logic Package**
     - Scheduler Class: Manages servers, distributes clients based on strategy, and calculates total client count and server wait time.
     - Strategy Interface: Defines client addition method.
     - SelectionPolicy Enumeration: Specifies strategies for client management.
     - ConcreteStrategyQueue and ConcreteStrategyTime Classes: Implement strategies for queue selection.
     - SimulationManager Class: Simulates the application, manages threads, and updates GUI.
   - **GUI Package**
     - SimulationStatusFrame and FrameGetInputData Classes: Handle graphical interfaces for status display and user input.
     - ControllerInputs Class: Manages application flow.
   - **Tests Package**
     - Test Class: Tests simulation with specific parameters and writes results to a file.

## 4. References  
   1. YouTube - BroCode
   3. GeeksforGeeks
   4. w3schools
   5. javatpoint
   6. Baeldung

