-- Find all employees whose first names start with a vowel and whose last names end with a consonant.
SELECT *
FROM employees
WHERE LOWER(SUBSTRING(first_name, 1, 1)) IN ('a', 'e', 'i', 'o', 'u')
  AND LOWER(SUBSTRING(last_name, LENGTH(last_name), 1)) NOT IN ('a', 'e', 'i', 'o', 'u');
--  For each department, display the total salary expenditure, the average salary, and the highest salary. Use window functions to calculate the total, average, and max salary, but show each result for all employees in that department
SELECT 
    department_id,
    employee_id,
    salary,
    SUM(salary) OVER (PARTITION BY department_id) AS total_salary,
    AVG(salary) OVER (PARTITION BY department_id) AS avg_salary,
    MAX(salary) OVER (PARTITION BY department_id) AS max_salary
FROM employees;

--  Write a query that fetches all employees, their department name, their manager’s name (if they have one), and their salary
SELECT 
    e.employee_id,
    e.first_name AS employee_name,
    d.department_name,
    m.first_name AS manager_name,
    e.salary
FROM employees e
LEFT JOIN employees m ON e.manager_id = m.employee_id
JOIN departments d ON e.department_id = d.department_id;

-- Create a query using a recursive CTE to list all employees and their respective reporting chains (i.e., list the manager’s manager and so on).
WITH RECURSIVE reporting_chain AS (
    SELECT 
        employee_id, 
        first_name, 
        manager_id,
        CAST(first_name AS VARCHAR(255)) AS reporting_path
    FROM employees
    WHERE manager_id IS NULL

    UNION ALL

    SELECT 
        e.employee_id, 
        e.first_name, 
        e.manager_id,
        CONCAT(rc.reporting_path, ' -> ', e.first_name) AS reporting_path
    FROM employees e
    JOIN reporting_chain rc ON e.manager_id = rc.employee_id
)
SELECT employee_id, reporting_path
FROM reporting_chain;

--  Write a query to fetch the details of employees earning above a certain salary threshold.
SELECT *
FROM employees
WHERE salary > 50000;
