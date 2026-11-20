# CSCI 4055 — COMPANY Employee Search

## Project Overview

This project is a Java Swing desktop application designed to search employee information from the COMPANY database.

Users can select departments and projects, use Not checkboxes to exclude selected categories, and display matching employees.

The application is being developed by a group of three students.

## Development Tools

- Java
- Eclipse IDE
- Eclipse WindowBuilder
- Git and GitHub
- MySQL and JDBC (planned database integration)

## Group Responsibilities

### Member 1 — GUI and Clear Functionality

- Worked on the graphical user interface using Eclipse WindowBuilder.
- Added JScrollPane components to Department, Project, and Employee Results.
- Enabled multiple selection in the Department and Project lists.
- Connected the Fill, Search, and Clear buttons to separate methods.
- Implemented the clear() method to remove employee results, reset selections, and uncheck both Not checkboxes.
- Tested the GUI and button functionality using sample data.

### Member 2 — Database Connection and Fill

Responsible for establishing the database connection and implementing the fill() method to load actual departments and projects.

### Member 3 — Search and Testing

Responsible for implementing SQL queries in search(), handling the Not filtering logic, and testing employee search results.

## How to Run the Current GUI

1. Open the project in Eclipse IDE.
2. Open EmployeeSearchFrame.java.
3. Run it as a Java Application.
4. Click Fill to load sample department and project names.
5. Select departments or projects.
6. Click Search to display sample employee results.
7. Click Clear to remove results and reset selections.

## Current Project Status

The GUI is functional using sample data.

The Fill and Search methods are currently placeholders for the actual database functionality.

The final application will integrate the database connection and SQL search queries developed by the other group members.

Additional Notes

The project is based on the Java framework provided by Professor Lon Smith.

The original GUI uses fixed component positioning. Responsive resizing is not yet implemented.

The final report and database testing results will be completed after integrating all three members' work.

