# CSCI 4055 — COMPANY Employee Search

## Project Overview
A Java Swing desktop application that loads the departments and projects of a
COMPANY-schema MySQL database and searches for employees who are in, or not in,
the selected departments and projects.

Built from the Swing template provided by Professor Lon Smith.

## Development Tools
- Java (JDK)
- Eclipse IDE and **Eclipse WindowBuilder** (GUI layout)
- MySQL 8 and JDBC (MySQL Connector/J 8.4.0, included in `lib/`)
- Git and GitHub (one branch per member, merged by pull request)
- Ubuntu on WSL (database and command-line testing)

## Group Members and Contributions

### Samikshya Manandhar (Member 1): GUI and Clear
- Built the interface with Eclipse WindowBuilder.
- Added JScrollPane components to the Department, Project, and Employee lists.
- Enabled multiple selection in the Department and Project lists.
- Connected the Fill, Search, and Clear buttons to their methods.
- Implemented `clear()`: empties the Employee results, clears all selections, and unchecks both Not boxes.
- Created the repository and wrote the initial README.

### Prabin Bk (Member 2): Database Connection and Fill
- Set up MySQL, a `projuser` account, and two test databases (`company`, `company2`) loaded from `buildCOMPANYdb.sql`.
- Added the MySQL JDBC driver to `lib/`.
- Implemented `fill()`: connects to the database named in the text box, loads departments and projects into the lists, and keeps parallel ID lists (`deptIds`, `projIds`) so a selected list item maps to its real department or project number.
- Fill performs a full reset: it reloads both lists and clears selections, the Employee results, and both Not boxes.
- If the name is empty or the database cannot be opened, Fill empties the lists and shows the pop-up "The database could not be opened."
- Database user and password are read from the `DB_USER` and `DB_PASS` environment variables, so no credentials are committed.
- Wrote `SETUP.md`.

### Cecil Pradhan (Member 3): Search and Testing
- Implemented the employee search with JDBC prepared statements.
- Department and Project filtering with multiple selections (matches any selected item in a list).
- Not checkboxes: employees **not** in the selected departments, or **not** working on the selected projects.
- When both lists have selections, an employee must satisfy both filters (AND). Within one list, matching any selected item counts.
- With nothing selected, Search lists all employees. Searching before Fill shows the message "Click Fill to open a database first."
- Wrote the test plan and recorded results (see Testing).

## Setup and Running (Ubuntu / WSL)
1. Install: `sudo apt install default-jdk mysql-server`, then `sudo service mysql start`.
2. In `sudo mysql`, create the databases and a user:
   ```sql
   CREATE DATABASE company;
   CREATE DATABASE company2;
   CREATE USER 'projuser'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON company.*  TO 'projuser'@'localhost';
   GRANT ALL PRIVILEGES ON company2.* TO 'projuser'@'localhost';
   ```
3. Load the data into each database:
   `mysql -u projuser -p company < buildCOMPANYdb.sql` (repeat for `company2`).
4. Set your credentials (never commit them):
   ```
   export DB_PASS='your_password'
   export DB_USER='projuser'    # optional; projuser is the default
   ```
   To use a different MySQL account (for example `root`), set `DB_USER` and `DB_PASS` to that account's values.
5. Compile and run:
   ```
   javac -cp "lib/mysql-connector-j-8.4.0.jar" *.java
   java -cp ".:lib/mysql-connector-j-8.4.0.jar" EmployeeSearchFrame
   ```
   In Eclipse: add `lib/mysql-connector-j-8.4.0.jar` to the build path, set `DB_USER` and `DB_PASS`
   under Run Configurations > Environment, and run `EmployeeSearchFrame`.

## How to Use
1. Enter a database name (for example `company`) and click **Fill**.
2. Select one or more departments and/or projects (Ctrl+click for several).
3. Tick **Not** to list employees outside the selection.
4. Click **Search** to list matching employees.
5. Click **Clear** to empty the results, clear selections, and uncheck Not.

## Testing (database `company`)
| Test | Expected result |
|---|---|
| Fill `company` | 3 departments, 6 projects |
| Fill `company2` | Department "Research2" appears |
| Fill `nosuchdb` or an empty name | Error pop-up, lists emptied |
| Search before Fill | Message "Click Fill to open a database first." |
| Department Research | John, Franklin, Ramesh, Joyce |
| Department Research + Not | Alicia, Jennifer, Ahmad, James |
| Project ProductX | John, Joyce |
| Project Reorganization + Not | John, Alicia, Ramesh, Joyce, Ahmad |
| Department Research + Project ProductY | John, Franklin, Joyce |
| Two departments selected | Employees from both |
| Nothing selected | All 8 employees |
| Clear | Results, selections, and Not boxes reset |
| Fill again after selecting | Selections and Not boxes reset |

## Notes
- The original template uses fixed component positioning, so responsive resizing is not implemented.
- GUI tools used (Eclipse WindowBuilder) are documented above, as the assignment requires.
