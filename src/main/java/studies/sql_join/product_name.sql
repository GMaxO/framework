select product_name
from netology.customers A
join netology.orders B
on A.id = B.customer_id
where upper(A.name) = upper('alexey');