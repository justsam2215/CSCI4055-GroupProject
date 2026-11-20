# Setup (Ubuntu / WSL)

1. Install: sudo apt install default-jdk mysql-server
2. Start MySQL: sudo service mysql start
3. In `sudo mysql`: CREATE DATABASE company; CREATE DATABASE company2;
   CREATE USER 'projuser'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON company.* TO 'projuser'@'localhost';
   GRANT ALL PRIVILEGES ON company2.* TO 'projuser'@'localhost';
4. Load data: mysql -u projuser -p company < buildCOMPANYdb.sql  (repeat for company2)
5. Put mysql-connector-j-8.4.0.jar in the lib folder.
6. Set your password (do not commit it): export DB_PASS='your_password'
7. Compile: javac -cp "lib/mysql-connector-j-8.4.0.jar" EmployeeSearchFrame.java
8. Run: java -cp ".:lib/mysql-connector-j-8.4.0.jar" EmployeeSearchFrame
