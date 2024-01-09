# Reactive programming

## Prerequisites
1.	Installed database from list of available: https://spring.io/projects/spring-data-r2dbc#overview
2.	Java version 8+
3.	Maven
4.	Java IDE

## Task 1 - ETL process 
### (2 Points)
1. Create data model with following fields:
   ```
   "id": int, // id of employee
   "firstName": string // name of employee
   "lastName": string // surname of employee
   "age": int // age of employee
   ```
2. Create Reactive Repository
3. Create Setup class with following functionality:
    1. Requests employees from https://dummyapis.com/dummy/employee
    2. Parse Json Response using reactive pipelines and save objects using reactive repository
      
## Task 2 - API for Employees 
### (2 Points)
1. Implement the following API methods using Router Functions:
   ```
   - GET /employee?noofRecords=1000&idStarts=1 //get first 1000 records
   ```
2. Implement methods in reactive repository with methods to support API above
      
## Task 3 - Refactor ETL process using backpressure 
### (1 Point)
1. Modify our ETL implementation to apply backpressure. Tell the upstream to only send 20 elements at a time by using `request()`.
2. Log operations to see that `request(20)` is called, followed by 20 `onNext()` calls, then request(20) again and so on.
