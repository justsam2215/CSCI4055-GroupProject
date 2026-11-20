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

- Implemented the employee search query using JDBC prepared statements.
- Added Department and Project filtering, including both Not checkboxes.
- Multiple selections match any selected department or project.

## How to Run the Current GUI

1. Open the project in Eclipse IDE.
2. Open EmployeeSearchFrame.java.
3. Run it as a Java Application.
4. Enter a database name and click Fill to load department and project names.
5. Select departments or projects.
6. Click Search to display matching employee results.
7. Click Clear to remove results and reset selections.

## Current Project Status

The GUI, Fill, Clear, and database-backed Search methods are implemented.

Search can filter by selected departments and projects. The Not checkboxes exclude employees
from the selected departments or employees who work on the selected projects.

Additional Notes

The project is based on the Java framework provided by Professor Lon Smith.

The original GUI uses fixed component positioning. Responsive resizing is not yet implemented.

The final report and database testing results will be completed after integrating all three members' work.

