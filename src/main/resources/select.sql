SELECT product_name
FROM public.ORDERS t
         JOIN public.CUSTOMERS c on t.customer_id = c.id
WHERE lower(c.name) = :productName;