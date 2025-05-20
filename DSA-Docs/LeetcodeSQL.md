**In MySQL**

1. https://leetcode.com/problems/recyclable-and-low-fat-products/description/?envType=study-plan-v2&envId=top-sql-50
```
Products table:
+-------------+----------+------------+
| product_id  | low_fats | recyclable |
+-------------+----------+------------+
| 0           | Y        | N          |
| 1           | Y        | Y          |
| 2           | N        | Y          |
| 3           | Y        | Y          |
| 4           | N        | N          |
+-------------+----------+------------+
```
>Write a solution to find the ids of products that are both low fat and recyclable.

>select product_id from Products where low_fats="Y" and recyclable="Y"

2. https://leetcode.com/problems/find-customer-referee/description/?envType=study-plan-v2&envId=top-sql-50
```
Customer table:
+----+------+------------+
| id | name | referee_id |
+----+------+------------+
| 1  | Will | null       |
| 2  | Jane | null       |
| 3  | Alex | 2          |
| 4  | Bill | null       |
| 5  | Zack | 1          |
| 6  | Mark | 2          |
+----+------+------------+
```
> Find the names of the customer that are not referred by the customer with id = 2.

> Select name from Customer where **referee_id _is_ null** or referee_id!=2;

3. https://leetcode.com/problems/big-countries/description/?envType=study-plan-v2&envId=top-sql-50
```
World table:
+-------------+-----------+---------+------------+--------------+
| name        | continent | area    | population | gdp          |
+-------------+-----------+---------+------------+--------------+
| Afghanistan | Asia      | 652230  | 25500100   | 20343000000  |
| Albania     | Europe    | 28748   | 2831741    | 12960000000  |
| Algeria     | Africa    | 2381741 | 37100000   | 188681000000 |
| Andorra     | Europe    | 468     | 78115      | 3712000000   |
| Angola      | Africa    | 1246700 | 20609294   | 100990000000 |
+-------------+-----------+---------+------------+--------------+
```
> A country is big if:<br>
it has an area of at least three million (i.e., 3000000 km2), or<br>
it has a population of at least twenty-five million (i.e., 25000000). <br>
Write a solution to find the name, population, and area of the big countries.

> select name, population, area from World where area>=3000000 or population>=25000000;

4. https://leetcode.com/problems/article-views-i/description/?envType=study-plan-v2&envId=top-sql-50
```
Views table:
+------------+-----------+-----------+------------+
| article_id | author_id | viewer_id | view_date  |
+------------+-----------+-----------+------------+
| 1          | 3         | 5         | 2019-08-01 |
| 1          | 3         | 6         | 2019-08-02 |
| 2          | 7         | 7         | 2019-08-01 |
| 2          | 7         | 6         | 2019-08-02 |
| 4          | 7         | 1         | 2019-07-22 |
| 3          | 4         | 4         | 2019-07-21 |
| 3          | 4         | 4         | 2019-07-21 |
+------------+-----------+-----------+------------+
```
> Write a solution to find all the authors that viewed at least one of their own articles.<br>
Return the result table sorted by id in ascending order.

> select **distinct** author_id as id from Views where author_id=viewer_id order by author_id;

5. https://leetcode.com/problems/invalid-tweets/description/?envType=study-plan-v2&envId=top-sql-50
```
Tweets table:
+----------+-----------------------------------+
| tweet_id | content                           |
+----------+-----------------------------------+
| 1        | Let us Code                       |
| 2        | More than fifteen chars are here! |
+----------+-----------------------------------+
```
> Write a solution to find the IDs of the invalid tweets. The tweet is invalid if the number of characters used in the content of the tweet is strictly greater than 15.

> select tweet_id from Tweets where **length**(content)>15;

6. https://leetcode.com/problems/replace-employee-id-with-the-unique-identifier/description/?envType=study-plan-v2&envId=top-sql-50
```
Employees table:
+----+----------+
| id | name     |
+----+----------+
| 1  | Alice    |
| 7  | Bob      |
| 11 | Meir     |
| 90 | Winston  |
| 3  | Jonathan |
+----+----------+
EmployeeUNI table:
+----+-----------+
| id | unique_id |
+----+-----------+
| 3  | 1         |
| 11 | 2         |
| 90 | 3         |
+----+-----------+
```

> Write a solution to show the unique ID of each user, If a user does not have a unique ID replace just show null.

> select eu.unique_id, e.name <br>
from Employees e **left join** EmployeeUNI eu on e.id=eu.id;

7. https://leetcode.com/problems/product-sales-analysis-i/description/?envType=study-plan-v2&envId=top-sql-50
```
Sales table:
+---------+------------+------+----------+-------+
| sale_id | product_id | year | quantity | price |
+---------+------------+------+----------+-------+ 
| 1       | 100        | 2008 | 10       | 5000  |
| 2       | 100        | 2009 | 12       | 5000  |
| 7       | 200        | 2011 | 15       | 9000  |
+---------+------------+------+----------+-------+
Product table:
+------------+--------------+
| product_id | product_name |
+------------+--------------+
| 100        | Nokia        |
| 200        | Apple        |
| 300        | Samsung      |
+------------+--------------+
```
> Write a solution to report the product_name, year, and price for each sale_id in the Sales table.

>select p.product_name, s.year, s.price<br>
from Sales s join Product p on s.product_id=p.product_id;

8. https://leetcode.com/problems/customer-who-visited-but-did-not-make-any-transactions/description/?envType=study-plan-v2&envId=top-sql-50
```
Visits
+----------+-------------+
| visit_id | customer_id |
+----------+-------------+
| 1        | 23          |
| 2        | 9           |
| 4        | 30          |
| 5        | 54          |
| 6        | 96          |
| 7        | 54          |
| 8        | 54          |
+----------+-------------+
Transactions
+----------------+----------+--------+
| transaction_id | visit_id | amount |
+----------------+----------+--------+
| 2              | 5        | 310    |
| 3              | 5        | 300    |
| 9              | 5        | 200    |
| 12             | 1        | 910    |
| 13             | 2        | 970    |
+----------------+----------+--------+
```
> Write a solution to find the IDs of the users who visited without making any transactions and the number of times they made these types of visits.

> select customer_id, count(*) as count_no_trans <br>
from Visits v left join Transactions t on v.visit_id=t.visit_id <br>
where t.transaction_id is null <br>
group by customer_id;
 
9. https://leetcode.com/problems/rising-temperature/description/?envType=study-plan-v2&envId=top-sql-50
```
Weather table:
+----+------------+-------------+
| id | recordDate | temperature |
+----+------------+-------------+
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |
+----+------------+-------------+
```
> Write a solution to find all dates' id with higher temperatures compared to its previous dates (yesterday).

> _Using SubQuery_ (Not optimal) <br><br>
select w.id <br>
from Weather w<br>
where w.temperature > (<br>
select wa.temperature<br>
from Weather wa<br>
where wa.recordDate=w.recordDate- INTERVAL 1 day<br>
)<br>

> _Using joins_ (optimal) <br><br>
select cur.id<br>
from Weather cur join Weather prev on cur.recordDate=prev.recordDate+INTERVAL 1 day<br>
where cur.temperature>prev.temperature;

10. https://leetcode.com/problems/average-time-of-process-per-machine?envType=study-plan-v2&envId=top-sql-50
```
Activity table:
+------------+------------+---------------+-----------+
| machine_id | process_id | activity_type | timestamp |
+------------+------------+---------------+-----------+
| 0          | 0          | start         | 0.712     |
| 0          | 0          | end           | 1.520     |
| 0          | 1          | start         | 3.140     |
| 0          | 1          | end           | 4.120     |
| 1          | 0          | start         | 0.550     |
| 1          | 0          | end           | 1.550     |
| 1          | 1          | start         | 0.430     |
| 1          | 1          | end           | 1.420     |
| 2          | 0          | start         | 4.100     |
| 2          | 0          | end           | 4.512     |
| 2          | 1          | start         | 2.500     |
| 2          | 1          | end           | 5.000     |
+------------+------------+---------------+-----------+
```
>There is a factory website that has several machines each running the same number of processes. Write a solution to find the average time each machine takes to complete a process.<br>
The time to complete a process is the 'end' timestamp minus the 'start' timestamp. The average time is calculated by the total time to complete every process on the machine divided by the number of processes that were run.<br>
The resulting table should have the machine_id along with the average time as processing_time, which should be rounded to 3 decimal places.

> select st.machine_id, **round**(**avg**(end.timestamp-st.timestamp)**,3**) as processing_time <br>
from Activity st join Activity end on st.machine_id=end.machine_id <br>
and st.process_id=end.process_id and st.activity_type="start" and end.activity_type="end" <br>
group by machine_id;

11. https://leetcode.com/problems/employee-bonus/submissions/1629982590/?envType=study-plan-v2&envId=top-sql-50
```
Employee table:
+-------+--------+------------+--------+
| empId | name   | supervisor | salary |
+-------+--------+------------+--------+
| 3     | Brad   | null       | 4000   |
| 1     | John   | 3          | 1000   |
| 2     | Dan    | 3          | 2000   |
| 4     | Thomas | 3          | 4000   |
+-------+--------+------------+--------+
Bonus table:
+-------+-------+
| empId | bonus |
+-------+-------+
| 2     | 500   |
| 4     | 2000  |
+-------+-------+
```
> Write a solution to report the name and bonus amount of each employee with a bonus less than 1000.

> select e.name, b.bonus <br>
from Employee e left join Bonus b on e.empId=b.empId <br>
where b.bonus<1000 or b.bonus is null; 

<mark>12.</mark> https://leetcode.com/problems/students-and-examinations/?envType=study-plan-v2&envId=top-sql-50
```
Students table:
+------------+--------------+
| student_id | student_name |
+------------+--------------+
| 1          | Alice        |
| 2          | Bob          |
| 13         | John         |
| 6          | Alex         |
+------------+--------------+
Subjects table:
+--------------+
| subject_name |
+--------------+
| Math         |
| Physics      |
| Programming  |
+--------------+
Examinations table:
+------------+--------------+
| student_id | subject_name |
+------------+--------------+
| 1          | Math         |
| 1          | Physics      |
| 1          | Programming  |
| 2          | Programming  |
| 1          | Physics      |
| 1          | Math         |
| 13         | Math         |
| 13         | Programming  |
| 13         | Physics      |
| 2          | Math         |
| 1          | Math         |
+------------+--------------+
```
> Write a solution to find the number of times each student attended each exam.<br>
Return the result table ordered by student_id and subject_name.

> select s.student_id, s.student_name, sn.subject_name, count(e.student_id) as attended_exams<br>
from Students s join Subjects sn<br>
left join Examinations e on s.student_id=e.student_id and sn.subject_name=e.subject_name<br>
group by 1,2,3<br>
order by 1,2,3;

> //Generate all the combinations of students and subjects and left join the exams to include null value. Refer subject name from subject table for proper results

13. https://leetcode.com/problems/managers-with-at-least-5-direct-reports/?envType=study-plan-v2&envId=top-sql-50
```
Employee table:
+-----+-------+------------+-----------+
| id  | name  | department | managerId |
+-----+-------+------------+-----------+
| 101 | John  | A          | null      |
| 102 | Dan   | A          | 101       |
| 103 | James | A          | 101       |
| 104 | Amy   | A          | 101       |
| 105 | Anne  | A          | 101       |
| 106 | Ron   | B          | 101       |
+-----+-------+------------+-----------+
```
> Write a solution to find managers with at least five direct reports.

> select m.name <br>
from Employee m join Employee e on m.id=e.managerId <br>
group by m.id <br>
having count(m.id)>=5;

<mark>14.</mark> https://leetcode.com/problems/confirmation-rate/submissions/1631104390/?envType=study-plan-v2&envId=top-sql-50
```
Signups table:
+---------+---------------------+
| user_id | time_stamp          |
+---------+---------------------+
| 3       | 2020-03-21 10:16:13 |
| 7       | 2020-01-04 13:57:59 |
| 2       | 2020-07-29 23:09:44 |
| 6       | 2020-12-09 10:39:37 |
+---------+---------------------+
Confirmations table:
+---------+---------------------+-----------+
| user_id | time_stamp          | action    |
+---------+---------------------+-----------+
| 3       | 2021-01-06 03:30:46 | timeout   |
| 3       | 2021-07-14 14:00:00 | timeout   |
| 7       | 2021-06-12 11:57:29 | confirmed |
| 7       | 2021-06-13 12:58:28 | confirmed |
| 7       | 2021-06-14 13:59:27 | confirmed |
| 2       | 2021-01-22 00:00:00 | confirmed |
| 2       | 2021-02-28 23:59:59 | timeout   |
+---------+---------------------+-----------+
```
> The confirmation rate of a user is the number of 'confirmed' messages divided by the total number of requested confirmation messages. The confirmation rate of a user that did not request any confirmation messages is 0. Round the confirmation rate to two decimal places.<br>
Write a solution to find the confirmation rate of each user.

> select s.user_id, round(avg(**_if(c.action='confirmed',1,0)_**),2) as confirmation_rate <br>
from Signups s left join Confirmations c on s.user_id=c.user_id <br>
group by user_id;

15. https://leetcode.com/problems/not-boring-movies/submissions/1631108247/?envType=study-plan-v2&envId=top-sql-50
```
Cinema table:
+----+------------+-------------+--------+
| id | movie      | description | rating |
+----+------------+-------------+--------+
| 1  | War        | great 3D    | 8.9    |
| 2  | Science    | fiction     | 8.5    |
| 3  | irish      | boring      | 6.2    |
| 4  | Ice song   | Fantacy     | 8.6    |
| 5  | House card | Interesting | 9.1    |
+----+------------+-------------+--------+
```
> Write a solution to report the movies with an odd-numbered ID and a description that is not "boring".
Return the result table ordered by rating in descending order.

> select * <br>
from Cinema <br>
where id%2!=0 and description!='boring' <br>
order by rating desc; 

16. https://leetcode.com/problems/average-selling-price/?envType=study-plan-v2&envId=top-sql-50
```
Prices table:
+------------+------------+------------+--------+
| product_id | start_date | end_date   | price  |
+------------+------------+------------+--------+
| 1          | 2019-02-17 | 2019-02-28 | 5      |
| 1          | 2019-03-01 | 2019-03-22 | 20     |
| 2          | 2019-02-01 | 2019-02-20 | 15     |
| 2          | 2019-02-21 | 2019-03-31 | 30     |
+------------+------------+------------+--------+
UnitsSold table:
+------------+---------------+-------+
| product_id | purchase_date | units |
+------------+---------------+-------+
| 1          | 2019-02-25    | 100   |
| 1          | 2019-03-01    | 15    |
| 2          | 2019-02-10    | 200   |
| 2          | 2019-03-22    | 30    |
+------------+---------------+-------+
```
> Write a solution to find the average selling price for each product. average_price should be rounded to 2 decimal places. If a product does not have any sold units, its average selling price is assumed to be 0.

> select p.product_id, ifnull( round( sum(p.price*u.units)/sum(u.units) ,2) ,0) as average_price <br>
from Prices p left join UnitsSold u <br>
on p.product_id=u.product_id and u.purchase_date between p.start_date and p.end_date <br>
group by p.product_id;

17. https://leetcode.com/problems/project-employees-i/description/?envType=study-plan-v2&envId=top-sql-50
```
Project table:
+-------------+-------------+
| project_id  | employee_id |
+-------------+-------------+
| 1           | 1           |
| 1           | 2           |
| 1           | 3           |
| 2           | 1           |
| 2           | 4           |
+-------------+-------------+
Employee table:
+-------------+--------+------------------+
| employee_id | name   | experience_years |
+-------------+--------+------------------+
| 1           | Khaled | 3                |
| 2           | Ali    | 2                |
| 3           | John   | 1                |
| 4           | Doe    | 2                |
+-------------+--------+------------------+
```
> Write an SQL query that reports the average experience years of all the employees for each project, rounded to 2 digits.

> select p.project_id, round(avg(e.experience_years),2) as average_years <br>
from Project p join Employee e on p.employee_id=e.employee_id <br>
group by p.project_id;

18. https://leetcode.com/problems/percentage-of-users-attended-a-contest/description/?envType=study-plan-v2&envId=top-sql-50
```
Users table:
+---------+-----------+
| user_id | user_name |
+---------+-----------+
| 6       | Alice     |
| 2       | Bob       |
| 7       | Alex      |
+---------+-----------+
Register table:
+------------+---------+
| contest_id | user_id |
+------------+---------+
| 215        | 6       |
| 209        | 2       |
| 208        | 2       |
| 210        | 6       |
| 208        | 6       |
| 209        | 7       |
| 209        | 6       |
| 215        | 7       |
| 208        | 7       |
| 210        | 2       |
| 207        | 2       |
| 210        | 7       |
+------------+---------+
```
> Write a solution to find the percentage of the users registered in each contest rounded to two decimals.<br>
Return the result table ordered by percentage in descending order. In case of a tie, order it by contest_id in ascending order.

> select contest_id, round(((count(user_id)*100)/(select count(*) from Users)),2) as percentage<br>
from Register<br>
group by contest_id<br>
order by percentage desc, contest_id asc;

19. https://leetcode.com/problems/queries-quality-and-percentage/description/?envType=study-plan-v2&envId=top-sql-50
```
Queries table:
+------------+-------------------+----------+--------+
| query_name | result            | position | rating |
+------------+-------------------+----------+--------+
| Dog        | Golden Retriever  | 1        | 5      |
| Dog        | German Shepherd   | 2        | 5      |
| Dog        | Mule              | 200      | 1      |
| Cat        | Shirazi           | 5        | 2      |
| Cat        | Siamese           | 3        | 3      |
| Cat        | Sphynx            | 7        | 4      |
+------------+-------------------+----------+--------+
```
> We define query quality as:<br>
The average of the ratio between query rating and its position.<br>
We also define poor query percentage as:<br>
The percentage of all queries with rating less than 3.

> select query_name, round(avg(rating/position),2) as quality, <br>
round(avg(if(rating<3,1,0)*100),2) as poor_query_percentage <br>
from Queries <br>
group by query_name;

20. https://leetcode.com/problems/monthly-transactions-i/description/?envType=study-plan-v2&envId=top-sql-50
```
Transactions table:
+------+---------+----------+--------+------------+
| id   | country | state    | amount | trans_date |
+------+---------+----------+--------+------------+
| 121  | US      | approved | 1000   | 2018-12-18 |
| 122  | US      | declined | 2000   | 2018-12-19 |
| 123  | US      | approved | 2000   | 2019-01-01 |
| 124  | DE      | approved | 2000   | 2019-01-07 |
+------+---------+----------+--------+------------+
```
> Write an SQL query to find for each month and country, the number of transactions and their total amount, the number of approved transactions and their total amount.

> select **DATE_FORMAT(trans_date, '%Y-%m')** as month, country, <br>
count(*) as trans_count,<br>
sum(if(state='approved',1,0)) as approved_count,<br>
sum(amount) as trans_total_amount,<br>
sum(if(state='approved',amount,0)) as approved_total_amount<br>
from Transactions<br>
group by DATE_FORMAT(trans_date, '%Y-%m'), country;

21. 
