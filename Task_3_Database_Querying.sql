Task #3: Database Querying

--Q1 - Given the Contacts table below, write a SQL statement to get all contacts that have both email and phone values populated
--ANSWER :
SELECT * FROM CONTACTS WHERE email IS NOT NULL AND phone IS NOT NULL

--Q2 - Given the Users table below, write a SQL statement to get the count of users per groupId
--ANSWER : EMAIL USED TO AVOID DUPLICATE USERS WITH SAME NAME VALUES
SELECT groupId, COUNT(email) FROM users GROUP BY groupId

--Q3 - Given the Customers and Orders table below, write a SQL statement to get the customerName, orderId and orderDate in a single dataset
--ANSWER : 
SELECT 
CUS.customerName,
ord.orderId,
ord.orderDate
FROM orders ord
LEFT JOIN customers cus
ON ORD.customerId = CUS.id

