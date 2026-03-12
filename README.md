Student Information System
This project is a Java Swing desktop application designed to manage student records. It facilitates core CRUD (Create, Read, Update, Delete) operations using a database connection.

Features
Create: Add new students to the system with fields for first name, last name, age, and email.

Read: Display all stored students in a JTable for easy viewing.

Update: Modify existing student information by selecting a row from the table.

Delete: Remove student records from the database using their unique student ID.

Data Validation: Includes safeguards to prevent empty fields or invalid data types (such as non-numeric ages) from being submitted.

Prerequisites
IntelliJ IDEA

Java Development Kit (JDK)

MySQL or SQLite Database

JDBC Driver for the respective database

Project Structure
StudentGUI.form / .java: Contains the visual interface design and the event listeners for the buttons.

StudentDAO.java: Manages the communication between the application and the database.

DBConnection.java: Handles the connection string and database authentication.

Student.java: A model class representing the student data structure.

Installation and Setup
Clone the repository or open the project folder in IntelliJ IDEA.

Configure the database credentials in DBConnection.java.

Ensure the necessary JDBC driver is added to the project libraries.

Run the application by navigating to StudentGUI.java and clicking the run icon next to the main method.

Usage
Adding: Fill in the text fields and click the Add button. The table will refresh automatically.

Updating: Select a student row in the table, modify the details in the text fields, and click the Update button.

Deleting: Select a student row in the table and click the Delete button.
