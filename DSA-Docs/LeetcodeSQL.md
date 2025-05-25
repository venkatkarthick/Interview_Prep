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

21. https://leetcode.com/problems/immediate-food-delivery-ii/description/?envType=study-plan-v2&envId=top-sql-50
```
Delivery table:
+-------------+-------------+------------+-----------------------------+
| delivery_id | customer_id | order_date | customer_pref_delivery_date |
+-------------+-------------+------------+-----------------------------+
| 1           | 1           | 2019-08-01 | 2019-08-02                  |
| 2           | 2           | 2019-08-02 | 2019-08-02                  |
| 3           | 1           | 2019-08-11 | 2019-08-12                  |
| 4           | 3           | 2019-08-24 | 2019-08-24                  |
| 5           | 3           | 2019-08-21 | 2019-08-22                  |
| 6           | 2           | 2019-08-11 | 2019-08-13                  |
| 7           | 4           | 2019-08-09 | 2019-08-09                  |
+-------------+-------------+------------+-----------------------------+
```
> If the customer's preferred delivery date is the same as the order date, then the order is called immediate; otherwise, it is called scheduled.<br><br>
The first order of a customer is the order with the earliest order date that the customer made. It is guaranteed that a customer has precisely one first order. <br><br>
Write a solution to find the percentage of immediate orders in the first orders of all customers, rounded to 2 decimal places.

> select round(avg(if(order_date=customer_pref_delivery_date,1,0))*100,2) as immediate_percentage<br>
from Delivery<br>
where (customer_id, order_date) IN<br>
(<br>
&nbsp;&nbsp; select customer_id, min(order_date)  as earliest_date<br>
&nbsp;&nbsp; from Delivery<br>
&nbsp;&nbsp; group by customer_id<br>
);

22. https://leetcode.com/problems/game-play-analysis-iv/description/?envType=study-plan-v2&envId=top-sql-50
```
Activity table:
+-----------+-----------+------------+--------------+
| player_id | device_id | event_date | games_played |
+-----------+-----------+------------+--------------+
| 1         | 2         | 2016-03-01 | 5            |
| 1         | 2         | 2016-03-02 | 6            |
| 2         | 3         | 2017-06-25 | 1            |
| 3         | 1         | 2016-03-02 | 0            |
| 3         | 4         | 2018-07-03 | 5            |
+-----------+-----------+------------+--------------+
```
> Write a solution to report the fraction of players that logged in again on the day after the day they first logged in, rounded to 2 decimal places. In other words, you need to count the number of players that logged in for at least two consecutive days starting from their first login date, then divide that number by the total number of players.

> with first_day as ( <br>
&nbsp;&nbsp; select player_id, min(event_date) as first_date <br>
&nbsp;&nbsp; from Activity <br>
&nbsp;&nbsp; group by player_id <br>
), <br>
consecutive_day as ( <br>
&nbsp;&nbsp; select count(*) as immediate_player <br>
&nbsp;&nbsp; from Activity next join first_day cur on next.player_id=cur.player_id <br>
&nbsp;&nbsp; and next.event_date=cur.first_date+INTERVAL 1 day <br>
) <br>
select round(c.immediate_player/count(DISTINCT a.player_id),2) as fraction <br>
from Activity a, consecutive_day c; 

23. https://leetcode.com/problems/number-of-unique-subjects-taught-by-each-teacher/description/?envType=study-plan-v2&envId=top-sql-50
```
Teacher table:
+------------+------------+---------+
| teacher_id | subject_id | dept_id |
+------------+------------+---------+
| 1          | 2          | 3       |
| 1          | 2          | 4       |
| 1          | 3          | 3       |
| 2          | 1          | 1       |
| 2          | 2          | 1       |
| 2          | 3          | 1       |
| 2          | 4          | 1       |
+------------+------------+---------+
```
> Write a solution to calculate the number of unique subjects each teacher teaches in the university.

> select teacher_id, count(DISTINCT subject_id) as cnt <br>
from Teacher <br>
group by teacher_id;

24. https://leetcode.com/problems/user-activity-for-the-past-30-days-i/?envType=study-plan-v2&envId=top-sql-50
```
Activity table:
+---------+------------+---------------+---------------+
| user_id | session_id | activity_date | activity_type |
+---------+------------+---------------+---------------+
| 1       | 1          | 2019-07-20    | open_session  |
| 1       | 1          | 2019-07-20    | scroll_down   |
| 1       | 1          | 2019-07-20    | end_session   |
| 2       | 4          | 2019-07-20    | open_session  |
| 2       | 4          | 2019-07-21    | send_message  |
| 2       | 4          | 2019-07-21    | end_session   |
| 3       | 2          | 2019-07-21    | open_session  |
| 3       | 2          | 2019-07-21    | send_message  |
| 3       | 2          | 2019-07-21    | end_session   |
| 4       | 3          | 2019-06-25    | open_session  |
| 4       | 3          | 2019-06-25    | end_session   |
+---------+------------+---------------+---------------+
```
> Write a solution to find the daily active user count for a period of 30 days ending 2019-07-27 inclusively. A user was active on someday if they made at least one activity on that day.

> select activity_date as day, count(DISTINCT user_id) as active_users <br>
from Activity <br>
group by activity_date <br>
having activity_date between '2019-07-28'-INTERVAL 30 day and '2019-07-27';

25. https://leetcode.com/problems/product-sales-analysis-iii/?envType=study-plan-v2&envId=top-sql-50
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
> Write a solution to select the product id, year, quantity, and price for the first year of every product sold. If any product is bought multiple times in its first year, return all sales separately.

> select s.product_id, s.year as first_year, s.quantity, s.price <br>
from Sales s join Product p on s.product_id=p.product_id <br>
where (s.product_id, s.year) in <br>
( <br>
select product_id, min(year) as first_year <br>
from Sales <br>
group by product_id <br>
) <br>
//Join Product table as well for faster retrieval since less amount of rows<br> will be passed to the subquery

26. https://leetcode.com/problems/classes-more-than-5-students/submissions/1639563753/?envType=study-plan-v2&envId=top-sql-50
```
Courses table:
+---------+----------+
| student | class    |
+---------+----------+
| A       | Math     |
| B       | English  |
| C       | Math     |
| D       | Biology  |
| E       | Math     |
| F       | Computer |
| G       | Math     |
| H       | Math     |
| I       | Math     |
+---------+----------+
```
> Write a solution to find all the classes that have at least five students.

> select class<br>
from Courses<br>
group by class<br>
having count(class)>=5;

27. https://leetcode.com/problems/find-followers-count/?envType=study-plan-v2&envId=top-sql-50
```
Followers table:
+---------+-------------+
| user_id | follower_id |
+---------+-------------+
| 0       | 1           |
| 1       | 0           |
| 2       | 0           |
| 2       | 1           |
+---------+-------------+
```
> Write a solution that will, for each user, return the number of followers.

> select user_id, count(user_id) as followers_count<br>
from Followers<br>
group by user_id<br>
order by user_id;

28. https://leetcode.com/problems/biggest-single-number/?envType=study-plan-v2&envId=top-sql-50
```
+-----+
| num |
+-----+
| 8   |
| 8   |
| 3   |
| 3   |
| 1   |
| 4   |
| 5   |
| 6   |
+-----+
```
> A single number is a number that appeared only once in the MyNumbers table. <br>
Find the largest single number. If there is no single number, report null.

> with distinctNum as ( <br>
&nbsp;&nbsp; select num<br>
&nbsp;&nbsp; from MyNumbers <br>
&nbsp;&nbsp; group by num <br>
&nbsp;&nbsp; having count(num)=1 <br>
) <br>
select max(num) as num <br>
from distinctNum;

29. https://leetcode.com/problems/customers-who-bought-all-products/?envType=study-plan-v2&envId=top-sql-50
```
Customer table:
+-------------+-------------+
| customer_id | product_key |
+-------------+-------------+
| 1           | 5           |
| 2           | 6           |
| 3           | 5           |
| 3           | 6           |
| 1           | 6           |
+-------------+-------------+
Product table:
+-------------+
| product_key |
+-------------+
| 5           |
| 6           |
+-------------+
```
> Write a solution to report the customer ids from the Customer table that bought all the products in the Product table.

> select c.customer_id <br>
from Customer c <br>
group by c.customer_id <br>
having count(DISTINCT c.product_key) = <br>
(select count(product_key) as total_product from Product);

30. https://leetcode.com/problems/the-number-of-employees-which-report-to-each-employee/submissions/1640982242/?envType=study-plan-v2&envId=top-sql-50
```
Employees table:
+-------------+---------+------------+-----+ 
| employee_id | name    | reports_to | age |
|-------------|---------|------------|-----|
| 1           | Michael | null       | 45  |
| 2           | Alice   | 1          | 38  |
| 3           | Bob     | 1          | 42  |
| 4           | Charlie | 2          | 34  |
| 5           | David   | 2          | 40  |
| 6           | Eve     | 3          | 37  |
| 7           | Frank   | null       | 50  |
| 8           | Grace   | null       | 48  |
+-------------+---------+------------+-----+ 
```
> For this problem, we will consider a manager an employee who has at least 1 other employee reporting to them. <br><br>
Write a solution to report the ids and the names of all managers, the number of employees who report directly to them, and the average age of the reports rounded to the nearest integer. <br><br>
Return the result table ordered by employee_id.

> select manager.employee_id, manager.name, <br>
count(employee.employee_id) as reports_count, round(avg(employee.age),0) as average_age <br>
from Employees employee join Employees manager <br>
on employee.reports_to=manager.employee_id <br>
group by manager.employee_id, manager.name <br>
order by manager.employee_id; 

31. https://leetcode.com/problems/primary-department-for-each-employee/?envType=study-plan-v2&envId=top-sql-50
```
Employee table:
+-------------+---------------+--------------+
| employee_id | department_id | primary_flag |
+-------------+---------------+--------------+
| 1           | 1             | N            |
| 2           | 1             | Y            |
| 2           | 2             | N            |
| 3           | 3             | N            |
| 4           | 2             | N            |
| 4           | 3             | Y            |
| 4           | 4             | N            |
+-------------+---------------+--------------+
```
> Employees can belong to multiple departments. When the employee joins other departments, they need to decide which department is their primary department. Note that when an employee belongs to only one department, their primary column is 'N'. <br><br>
Write a solution to report all the employees with their primary department. For employees who belong to one department, report their only department.

> Alternative approach : Rank each row based on priority and get the first row. But too costly. <br><br>
with rank_table as ( <br>
&nbsp;&nbsp;select *, **row_number() over(** <br>
&nbsp;&nbsp;**partition by** employee_id <br>
&nbsp;&nbsp;**order by** _if(primary_flag="Y", 1, 2)_ <br>
&nbsp;&nbsp;) as rn <br>
&nbsp;&nbsp;from Employee <br>
) <br>
select employee_id, department_id <br>
from rank_table <br>
where rn=1;

> Simply and less costly query: <br><br>
select employee_id, department_id <br>
from Employee<br>
where primary_flag='Y' or employee_id in (<br>
&nbsp;&nbsp;select employee_id<br>
&nbsp;&nbsp;from Employee<br>
&nbsp;&nbsp;group by employee_id<br>
&nbsp;&nbsp;having count(employee_id)=1<br>
);<br><br>
> We can write 2 seperate queries for two or clauses and unite the results through **_union_**

32. https://leetcode.com/problems/triangle-judgement/description/?envType=study-plan-v2&envId=top-sql-50

```
Triangle table:
+----+----+----+
| x  | y  | z  |
+----+----+----+
| 13 | 15 | 30 |
| 10 | 20 | 15 |
+----+----+----+
```
> Report for every three line segments whether they can form a triangle.

> select x, y, z, <br>
if((x+y>z && x+z>y && y+z>x), "Yes", "No") as triangle <br>
from Triangle;

33. https://leetcode.com/problems/consecutive-numbers/?envType=study-plan-v2&envId=top-sql-50
```
Logs table:
+----+-----+
| id | num |
+----+-----+
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |
+----+-----+
```

> Find all numbers that appear at least three times consecutively.

> select DISTINCT one.num as ConsecutiveNums <br>
from Logs one <br>
join Logs two <br>
join Logs three <br>
where one.num=two.num and one.num=three.num <br>
and one.id=two.id+1 and two.id=three.id+1;

34. https://leetcode.com/problems/product-price-at-a-given-date/submissions/1642364701/?envType=study-plan-v2&envId=top-sql-50
```
Products table:
+------------+-----------+-------------+
| product_id | new_price | change_date |
+------------+-----------+-------------+
| 1          | 20        | 2019-08-14  |
| 2          | 50        | 2019-08-14  |
| 1          | 30        | 2019-08-15  |
| 1          | 35        | 2019-08-16  |
| 2          | 65        | 2019-08-17  |
| 3          | 20        | 2019-08-18  |
+------------+-----------+-------------+
```
> Write a solution to find the prices of all products on 2019-08-16. Assume the price of all products before any change is 10.

> with <br>
rankedProducts as( <br>
&nbsp;&nbsp;&nbsp;select product_id, new_price, change_date, row_number() over(<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;partition by product_id<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;order by change_date desc<br>
&nbsp;&nbsp;&nbsp;) as rn<br>
&nbsp;&nbsp;&nbsp;from Products<br>
&nbsp;&nbsp;&nbsp;where change_date<='2019-08-16'<br>
), filteredProducts as (<br>
&nbsp;&nbsp;&nbsp;select p.product_id, rp.new_price, rp.change_date, row_number() over(<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;partition by p.product_id<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;order by if(rp.rn=1, 1, if(rp.rn=null, 1, 2))<br>
&nbsp;&nbsp;&nbsp;) as rnn<br>
&nbsp;&nbsp;&nbsp;from Products p left join rankedProducts rp<br>
&nbsp;&nbsp;&nbsp;on p.product_id=rp.product_id and p.change_date=rp.change_date<br>
)<br>
select product_id, if(new_price is null, 10, new_price) as price<br>
from filteredProducts<br>
where rnn=1;<br><br>

> Rank the rows which has products within the given date. Filter the ranked table for null values in the existing products which might occur for _date>given date_ and also capture the non-ranked products and return the first row.

35. https://leetcode.com/problems/product-price-at-a-given-date/?envType=study-plan-v2&envId=top-sql-50
```
Products table:
+------------+-----------+-------------+
| product_id | new_price | change_date |
+------------+-----------+-------------+
| 1          | 20        | 2019-08-14  |
| 2          | 50        | 2019-08-14  |
| 1          | 30        | 2019-08-15  |
| 1          | 35        | 2019-08-16  |
| 2          | 65        | 2019-08-17  |
| 3          | 20        | 2019-08-18  |
+------------+-----------+-------------+
```

> Write a solution to find the prices of all products on 2019-08-16. Assume the price of all products before any change is 10.

> with <br>
rankedProducts as( <br>
&nbsp;&nbsp;select product_id, new_price, change_date, row_number() over( <br>
&nbsp;&nbsp;partition by product_id<br>
&nbsp;&nbsp;order by change_date desc<br>
&nbsp;&nbsp;) as rn<br>
&nbsp;&nbsp;from Products<br>
&nbsp;&nbsp;where change_date<='2019-08-16'<br>
), filteredProducts as (<br>
&nbsp;&nbsp;select p.product_id, rp.new_price, rp.change_date, row_number() over(<br>
&nbsp;&nbsp;&nbsp;&nbsp;partition by p.product_id<br>
&nbsp;&nbsp;&nbsp;&nbsp;order by if(rp.rn=1, 1, if(rp.rn=null, 1, 2))<br>
&nbsp;&nbsp;) as rnn<br>
&nbsp;&nbsp;from Products p left join rankedProducts rp<br>
&nbsp;&nbsp;on p.product_id=rp.product_id and p.change_date=rp.change_date<br>
)<br>
select product_id, if(new_price is null, 10, new_price) as price<br>
from filteredProducts<br>
where rnn=1;<br><br>
//Select and rank the rows whose _change date<given date_ and then filter out the nulls present by giving priority to rn=1 and then rn=null such that we can effectively handle null cases we may get for the products having _change date > given date_ and also products which only has change date above the given date also can be properly identified by joining both rows with left join and selecting new_price from ranked row where it will be having null and thus properly giving it the default value of 10

36. https://leetcode.com/problems/count-salary-categories/description/?envType=study-plan-v2&envId=top-sql-50
```
Accounts table:
+------------+--------+
| account_id | income |
+------------+--------+
| 3          | 108939 |
| 2          | 12747  |
| 8          | 87709  |
| 6          | 91796  |
+------------+--------+
```
> Write a solution to calculate the number of bank accounts for each salary category. The salary categories are:<br>
"Low Salary": All the salaries strictly less than 20000.<br>
"Average Salary": All the salaries in the inclusive range [20000, 50000].<br>
"High Salary": All the salaries strictly greater than 50000.<br>
The result table must contain all three categories. If there are no accounts in a category, return 0.

> with <br>
accountsCount as (<br>
&nbsp;&nbsp;select account_id, income,<br>
&nbsp;&nbsp;if(income<20000, "Low Salary",<br>
&nbsp;&nbsp;if(income>50000, "High Salary", "Average Salary")) as category<br>
&nbsp;&nbsp;from Accounts<br>
), accountTypes as (<br>
&nbsp;&nbsp;select "Low Salary" as name union all<br>
&nbsp;&nbsp;select "Average Salary" union all<br>
&nbsp;&nbsp;select "High Salary"<br>
)<br>
select at.name as category, count(ac.category) as accounts_count<br>
from accountTypes at left join accountsCount ac<br>
on ac.category=at.name<br>
group by ac.category;

36. https://leetcode.com/problems/employees-whose-manager-left-the-company/?envType=study-plan-v2&envId=top-sql-50
```
Employees table:
+-------------+-----------+------------+--------+
| employee_id | name      | manager_id | salary |
+-------------+-----------+------------+--------+
| 3           | Mila      | 9          | 60301  |
| 12          | Antonella | null       | 31000  |
| 13          | Emery     | null       | 67084  |
| 1           | Kalel     | 11         | 21241  |
| 9           | Mikaela   | null       | 50937  |
| 11          | Joziah    | 6          | 28485  |
+-------------+-----------+------------+--------+
```
> Find the IDs of the employees whose salary is strictly less than 30000 and whose manager left the company. When a manager leaves the company, their information is deleted from the Employees table, but the reports still have their manager_id set to the manager that left.<br>
Return the result table ordered by employee_id.

> select o.employee_id<br>
from Employees o<br>
where o.manager_id is not null and o.salary<30000<br>
and(<br>
select e.employee_id<br>
from Employees e<br>
where e.employee_id=o.manager_id<br>
) is null<br>
order by o.employee_id;

37. https://leetcode.com/problems/exchange-seats/submissions/1643396345/?envType=study-plan-v2&envId=top-sql-50
```
Seat table:
+----+---------+
| id | student |
+----+---------+
| 1  | Abbot   |
| 2  | Doris   |
| 3  | Emerson |
| 4  | Green   |
| 5  | Jeames  |
+----+---------+
```
>Write a solution to swap the seat id of every two consecutive students. If the number of students is odd, the id of the last student is not swapped.<br>
Return the result table ordered by id in ascending order.

> with maxId as ( <br>
&nbsp;&nbsp;select max(id) as idMax from Seat<br>
)<br>
select if(id%2=0, id-1,<br>
if(id=(select idMax from maxId), id, id+1))<br>
as id, student<br>
from Seat<br>
order by id;

38. https://leetcode.com/problems/movie-rating/submissions/1643966203/?envType=study-plan-v2&envId=top-sql-50
```
Movies table:
+-------------+--------------+
| movie_id    |  title       |
+-------------+--------------+
| 1           | Avengers     |
| 2           | Frozen 2     |
| 3           | Joker        |
+-------------+--------------+
Users table:
+-------------+--------------+
| user_id     |  name        |
+-------------+--------------+
| 1           | Daniel       |
| 2           | Monica       |
| 3           | Maria        |
| 4           | James        |
+-------------+--------------+
MovieRating table:
+-------------+--------------+--------------+-------------+
| movie_id    | user_id      | rating       | created_at  |
+-------------+--------------+--------------+-------------+
| 1           | 1            | 3            | 2020-01-12  |
| 1           | 2            | 4            | 2020-02-11  |
| 1           | 3            | 2            | 2020-02-12  |
| 1           | 4            | 1            | 2020-01-01  |
| 2           | 1            | 5            | 2020-02-17  | 
| 2           | 2            | 2            | 2020-02-01  | 
| 2           | 3            | 2            | 2020-03-01  |
| 3           | 1            | 3            | 2020-02-22  | 
| 3           | 2            | 4            | 2020-02-25  | 
+-------------+--------------+--------------+-------------+
```
> Find the name of the user who has rated the greatest number of movies. In case of a tie, return the lexicographically smaller user name. <br> <br>
Find the movie name with the highest average rating in February 2020. In case of a tie, return the lexicographically smaller movie name.

> with MovieCount as ( <br>
&nbsp;&nbsp;select u.name, count(mr.user_id) as cnt <br>
&nbsp;&nbsp;from MovieRating mr join Users u<br>
&nbsp;&nbsp;on mr.user_id=u.user_id<br>
&nbsp;&nbsp;group by mr.user_id<br>
&nbsp;&nbsp;order by cnt desc, u.name<br>
&nbsp;&nbsp;#added tiebreaker condition as name with comma separated<br>
&nbsp;&nbsp;limit 1<br>
),<br>
ReviewAvg as (<br>
&nbsp;&nbsp;select m.title, avg(mr.rating) as avgRating<br>
&nbsp;&nbsp;from MovieRating mr join Movies m<br>
&nbsp;&nbsp;on mr.movie_id=m.movie_id<br>
&nbsp;&nbsp;where mr.created_at between '2020-02-01' and '2020-02-28'<br>
&nbsp;&nbsp;group by mr.movie_id<br>
&nbsp;&nbsp;order by avgRating desc, m.title<br>
&nbsp;&nbsp;limit 1<br>
)<br>
select name as results from MovieCount<br>
union all &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;#to include duplicate results as well<br>
select title as results from ReviewAvg;

39. https://leetcode.com/problems/restaurant-growth/submissions/1644102291/?envType=study-plan-v2&envId=top-sql-50
```
Customer table:
+-------------+--------------+--------------+-------------+
| customer_id | name         | visited_on   | amount      |
+-------------+--------------+--------------+-------------+
| 1           | Jhon         | 2019-01-01   | 100         |
| 2           | Daniel       | 2019-01-02   | 110         |
| 3           | Jade         | 2019-01-03   | 120         |
| 4           | Khaled       | 2019-01-04   | 130         |
| 5           | Winston      | 2019-01-05   | 110         | 
| 6           | Elvis        | 2019-01-06   | 140         | 
| 7           | Anna         | 2019-01-07   | 150         |
| 8           | Maria        | 2019-01-08   | 80          |
| 9           | Jaze         | 2019-01-09   | 110         | 
| 1           | Jhon         | 2019-01-10   | 130         | 
| 3           | Jade         | 2019-01-10   | 150         | 
+-------------+--------------+--------------+-------------+
```

> You are the restaurant owner and you want to analyze a possible expansion (there will be at least one customer every day).<br><br>
Compute the moving average of how much the customer paid in a seven days window (i.e., current day + 6 days before). average_amount should be rounded to two decimal places.<br><br>
Return the result table ordered by visited_on in ascending order.

> with Total as(<br>
&nbsp;&nbsp;select visited_on, sum(amount) as total<br>
&nbsp;&nbsp;from Customer<br>
&nbsp;&nbsp;group by visited_on<br>
), RunningTotal as (<br>
&nbsp;&nbsp;select t.visited_on,<br>
&nbsp;&nbsp;sum(t.total) over w as amount,<br>
&nbsp;&nbsp;round(avg(t.total) over w, 2) as average_amount,<br>
&nbsp;&nbsp;row_number() over w as rn<br>
&nbsp;&nbsp;from Total t<br>
&nbsp;&nbsp;**window** w as (<br>
&nbsp;&nbsp;&nbsp;&nbsp;order by t.visited_on<br>
&nbsp;&nbsp;&nbsp;&nbsp;rows **between** _6 preceding_ **and** _current row_<br>
&nbsp;&nbsp;&nbsp;&nbsp;#defined frame size for the operation to take place<br>
&nbsp;&nbsp;)<br>
)<br>
select visited_on, amount, average_amount<br>
from RunningTotal<br>
where rn>=7;

40. https://leetcode.com/problems/friend-requests-ii-who-has-the-most-friends/?envType=study-plan-v2&envId=top-sql-50
```
RequestAccepted table:
+--------------+-------------+-------------+
| requester_id | accepter_id | accept_date |
+--------------+-------------+-------------+
| 1            | 2           | 2016/06/03  |
| 1            | 3           | 2016/06/08  |
| 2            | 3           | 2016/06/08  |
| 3            | 4           | 2016/06/09  |
+--------------+-------------+-------------+
```

> Write a solution to find the people who have the most friends and the most friends number.<br>
The test cases are generated so that only one person has the most friends.

> with Members as ( <br>
&nbsp;&nbsp;select requester_id as member from RequestAccepted <br>
&nbsp;&nbsp;union all <br>
&nbsp;&nbsp;select accepter_id as member from RequestAccepted<br>
)<br>
select member as id, count(member) as num<br>
from Members<br>
group by member<br>
order by num desc<br>
limit 1;

41. https://leetcode.com/problems/investments-in-2016/submissions/1644132798/?envType=study-plan-v2&envId=top-sql-50
```
Insurance table:
+-----+----------+----------+-----+-----+
| pid | tiv_2015 | tiv_2016 | lat | lon |
+-----+----------+----------+-----+-----+
| 1   | 10       | 5        | 10  | 10  |
| 2   | 20       | 20       | 20  | 20  |
| 3   | 10       | 30       | 20  | 20  |
| 4   | 10       | 40       | 40  | 40  |
+-----+----------+----------+-----+-----+
```

> Write a solution to report the sum of all total investment values in 2016 tiv_2016, for all policyholders who:<br>
have the same tiv_2015 value as one or more other policyholders, and<br><br>
are not located in the same city as any other policyholder (i.e., the (lat, lon) attribute pairs must be unique).<br>
Round tiv_2016 to two decimal places.<br>

> select round(sum(tiv_2016),2) as tiv_2016<br>
from Insurance<br>
where tiv_2015 in (<br>
&nbsp;&nbsp;select tiv_2015 from Insurance group by tiv_2015 <br>
&nbsp;&nbsp;having count(tiv_2015)>1<br>
)<br>
and (lat, lon) in (<br>
&nbsp;&nbsp;select lat, lon from Insurance group by lat, lon<br>
&nbsp;&nbsp;having count(pid)=1<br>
);

42. https://leetcode.com/problems/department-top-three-salaries/?envType=study-plan-v2&envId=top-sql-50
```
Employee table:
+----+-------+--------+--------------+
| id | name  | salary | departmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 85000  | 1            |
| 2  | Henry | 80000  | 2            |
| 3  | Sam   | 60000  | 2            |
| 4  | Max   | 90000  | 1            |
| 5  | Janet | 69000  | 1            |
| 6  | Randy | 85000  | 1            |
| 7  | Will  | 70000  | 1            |
+----+-------+--------+--------------+
Department table:
+----+-------+
| id | name  |
+----+-------+
| 1  | IT    |
| 2  | Sales |
+----+-------+
```
> A company's executives are interested in seeing who earns the most money in each of the company's departments. A high earner in a department is an employee who has a salary in the top three unique salaries for that department.<br><br>
Write a solution to find the employees who are high earners in each of the departments.

>  with RankTable as (<br>
&nbsp;&nbsp;select e.name as Employee, e.salary, d.name as Department,<br>
&nbsp;&nbsp;**_dense_rank_**() over(<br>
&nbsp;&nbsp;&nbsp;&nbsp;partition by e.departmentId<br>
&nbsp;&nbsp;&nbsp;&nbsp;order by e.salary desc<br>
&nbsp;&nbsp;) as rn<br>
&nbsp;&nbsp;from Employee e join Department d<br>
&nbsp;&nbsp;on d.id=e.departmentId<br>
)<br>
select Department, Employee, salary<br>
from RankTable<br>
where rn in (1,2,3);<br><br>
#Normal rank function assings same number if a tie happens and for the next row does not assign the next rank(Assigns like -> 1,2,2,4)<br>
#Dense rank assigns same number for a tie and assigns next rank for next row (Assigns like-> 1,2,2,3)

43. https://leetcode.com/problems/fix-names-in-a-table/?envType=study-plan-v2&envId=top-sql-50
```
Users table:
+---------+-------+
| user_id | name  |
+---------+-------+
| 1       | aLice |
| 2       | bOB   |
+---------+-------+
```
> Write a solution to fix the names so that only the first character is uppercase and the rest are lowercase.<br><br>
Return the result table ordered by user_id.

> select user_id, <br>
**concat**(_upper_(**left**(`name`, 1)), _lower_(**substring**(`name`,2))) as name<br>
from Users<br>
order by user_id;

44. https://leetcode.com/problems/patients-with-a-condition/?envType=study-plan-v2&envId=top-sql-50
```
Patients table:
+------------+--------------+--------------+
| patient_id | patient_name | conditions   |
+------------+--------------+--------------+
| 1          | Daniel       | YFEV COUGH   |
| 2          | Alice        |              |
| 3          | Bob          | DIAB100 MYOP |
| 4          | George       | ACNE DIAB100 |
| 5          | Alain        | DIAB201      |
+------------+--------------+--------------+
```

> Write a solution to find the patient_id, patient_name, and conditions of the patients who have Type I Diabetes. Type I Diabetes always starts with DIAB1 prefix.

> select *<br>
from Patients<br>
where conditions like 'DIAB1%' or conditions like '% DIAB1%';

45. https://leetcode.com/problems/delete-duplicate-emails/?envType=study-plan-v2&envId=top-sql-50
```
Person table:
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
```
> Write a solution to delete all duplicate emails, keeping only one unique email with the smallest id.<br><br>
For SQL users, please note that you are supposed to write a DELETE statement and not a SELECT one.<br><br>
For Pandas users, please note that you are supposed to modify Person in place.<br><br>
After running your script, the answer shown is the Person table. The driver will first compile and run your piece of code and then show the Person table. The final order of the Person table does not matter.

> with NumberedRows as (<br>
&nbsp;&nbsp;select id, email,<br>
&nbsp;&nbsp;row_number() over(<br>
&nbsp;&nbsp;partition by email<br>
&nbsp;&nbsp;order by id<br>
&nbsp;&nbsp;) as rn<br>
&nbsp;&nbsp;from Person<br>
)<br>
delete p<br>
from person p join NumberedRows n on p.id=n.id<br>
where n.rn>1;

46. https://leetcode.com/problems/second-highest-salary/?envType=study-plan-v2&envId=top-sql-50
```
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
```
> Write a solution to find the second highest distinct salary from the Employee table. If there is no second highest salary, return null

> select max(salary) as SecondHighestSalary<br>
from Employee where salary not in (<br>
&nbsp;&nbsp;select max(salary) from Employee<br>
);

47. https://leetcode.com/problems/group-sold-products-by-the-date/?envType=study-plan-v2&envId=top-sql-50
```
Activities table:
+------------+------------+
| sell_date  | product     |
+------------+------------+
| 2020-05-30 | Headphone  |
| 2020-06-01 | Pencil     |
| 2020-06-02 | Mask       |
| 2020-05-30 | Basketball |
| 2020-06-01 | Bible      |
| 2020-06-02 | Mask       |
| 2020-05-30 | T-Shirt    |
+------------+------------+
```
> Write a solution to find for each date the number of different products sold and their names.<br><br>
The sold products names for each date should be sorted lexicographically.<br><br>
Return the result table ordered by sell_date.<br><br>

> select o.sell_date, count(DISTINCT o.product) as num_sold,<br>
**GROUP_CONCAT**(DISTINCT o.product _order by_ o.product) as products<br>
from Activities o<br>
group by o.sell_date;

48. https://leetcode.com/problems/list-the-products-ordered-in-a-period/description/?envType=study-plan-v2&envId=top-sql-50
```
Products table:
+-------------+-----------------------+------------------+
| product_id  | product_name          | product_category |
+-------------+-----------------------+------------------+
| 1           | Leetcode Solutions    | Book             |
| 2           | Jewels of Stringology | Book             |
| 3           | HP                    | Laptop           |
| 4           | Lenovo                | Laptop           |
| 5           | Leetcode Kit          | T-shirt          |
+-------------+-----------------------+------------------+
Orders table:
+--------------+--------------+----------+
| product_id   | order_date   | unit     |
+--------------+--------------+----------+
| 1            | 2020-02-05   | 60       |
| 1            | 2020-02-10   | 70       |
| 2            | 2020-01-18   | 30       |
| 2            | 2020-02-11   | 80       |
| 3            | 2020-02-17   | 2        |
| 3            | 2020-02-24   | 3        |
| 4            | 2020-03-01   | 20       |
| 4            | 2020-03-04   | 30       |
| 4            | 2020-03-04   | 60       |
| 5            | 2020-02-25   | 50       |
| 5            | 2020-02-27   | 50       |
| 5            | 2020-03-01   | 50       |
+--------------+--------------+----------+
```
> Write a solution to get the names of products that have at least 100 units ordered in February 2020 and their amount.

> select p.product_name, sum(o.unit) as unit<br>
from Orders o join Products p on o.product_id=p.product_id<br>
where o.order_date between '2020-02-01' and '2020-02-29'<br>
group by p.product_name<br>
having sum(o.unit)>=100;

49. https://leetcode.com/problems/find-users-with-valid-e-mails/description/?envType=study-plan-v2&envId=top-sql-50
```
Users table:
+---------+-----------+-------------------------+
| user_id | name      | mail                    |
+---------+-----------+-------------------------+
| 1       | Winston   | winston@leetcode.com    |
| 2       | Jonathan  | jonathanisgreat         |
| 3       | Annabelle | bella-@leetcode.com     |
| 4       | Sally     | sally.come@leetcode.com |
| 5       | Marwan    | quarz#2020@leetcode.com |
| 6       | David     | david69@gmail.com       |
| 7       | Shapiro   | .shapo@leetcode.com     |
+---------+-----------+-------------------------+
```
> Write a solution to find the users who have valid emails.<br><br>
A valid e-mail has a prefix name and a domain where:<br><br>
The prefix name is a string that may contain letters (upper or lower case), digits, underscore '_', period '.', and/or dash '-'. The prefix name must start with a letter.<br><br>
The domain is '@leetcode.com'.

```
select * from Users 
where mail regexp '^[a-zA-Z][a-zA-Z0-9_.-]*@leetcode\\.com$';
```

```
^ - start of the string
[] - contains single character of the gn symbols
[]* - contains 0 to many characters
\\ - escape character for reserved symbols
$ - end of the string
```
