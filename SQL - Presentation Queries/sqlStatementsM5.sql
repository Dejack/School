--User story 1: Owners can see which busses have undergone maintenance costs of over $2500:
SELECT mh.bus_id, TO_CHAR(sum(mh.maint_cost), '$9999.99') AS Cost, mh.maint_date 
FROM maintenance_hist mh
HAVING sum(round(mh.maint_cost,0)) > 2500
GROUP BY mh.bus_id, mh.maint_date
ORDER BY mh.bus_id DESC;

--User Story 2: Managers can see which drivers are responsible for each route in case any situation arises:
SELECT e.emp_fname, e.emp_phone_number, (SELECT w.route_num FROM works w WHERE w.emp_id = e.emp_id) AS "Route Number", (SELECT r.route_name FROM route r WHERE r.route_num = w.route_num) AS "Route Name"
FROM employee e
 INNER JOIN works w
 ON w.emp_id = e.emp_id
 
--User Story 3: Managers can see phone number of drivers who are not working that day, so that they can phone them to see if they want to come in and work for a sick driver:
SELECT e.emp_fname, e.emp_phone_number, a.attend_date, a.attend_start_time, a.attend_end_time
FROM employee e, attendance a
WHERE e.emp_id = a.emp_id AND a.attend_start_time is null AND a.attend_date = to_date(sysdate,'mm/dd/yyyy')

-- User story 5: Owners can see how many stops are in each route to make sure no roads are skipped over:
SELECT r.route_name, count(rs.stop_num)
FROM route_stop rs, route r
WHERE rs.route_num = r.route_num
GROUP BY r.route_name
ORDER BY COUNT(rs.stop_num) DESC

-- User story 6: As an owner, I want to be able to derive lots of different financial information using the number of tickets issued:
SELECT   i.issue_date, to_char(sum(ticket_cost) OVER (ORDER BY i.issue_date ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW), '$99.99') AS "Cumulative Total", to_char(ticket_cost, '$9.99') AS "Ticket Cost"
FROM ticket t, issue i
WHERE t.ticket_num = i.ticket_num AND i.issue_date BETWEEN '11/14/2015' AND SYSDATE
