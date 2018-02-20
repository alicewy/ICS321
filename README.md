# ICS321
Assignment 1: Querying Large Files
<br><br>
-------------------------------------------------------
Assignment 2: Write SQL Queries
<br><br>
Questions answered:

1. How much money is spent for what purpose by consumers in the U.S. in the first quarter of 2010 ? Another way to phrase this is : What is the personal consumption expenditure of each function in the first quarter of 2010 ? The result should have the columns: func, pce.

2. Which function, year, and quarter has the smallest PCE of all time? The result should have the columns: func, year, quarter, pce.

3. What is the function (and the corresponding PCE) that consumers spent the most money on in the first quarter of 2010 ? The result should have the columns: func, pce.

4.

5. What is the annual PCE for each function ? The result should have the columns: func, year, annualpce.

6. What is the function, year, and PCE with the most annual PCE ? The result should have the columns: func, year, annualpce.

7. Which year has the largest total PCE ? (Use the pcefunc table for this question). The result should have the columns: year, annualpce.

8. What is the total PCE for each level two product in the year 2009 ? The result should have the columns: prodlevel2, totalpce.

9. What is the annual PCE of “Goods” for each year ? The result should have the columns: year, annualpce.

10.
<br><br><br>
Example to load using docker:
<br><br>
sqlplus (default password: oracle, username not requested)
<br><br>
CREATE USER 1 IDENTIFIED BY 1;
<br><br>
GRANT CONNECT TO 1;
<br><br>
GRANT CREATE SESSION GRANT ANY PRIVILEGE TO 1; (new user login is now username: 1 pass: 1)?
<br><br>
apt wget install (install wget to obtain zip file)
<br><br>
unzip dockerfilename.zip (to unzip zip file on docker)
<br><br>
-------------------------------------------------------
Assignment 3: Web Application using JSF & JDBC
<br><br>
-------------------------------------------------------
Assignment 4: Design a Database
<br><br>
