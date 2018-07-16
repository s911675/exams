SELECT
	A.uid,
	A.title,
	A.contents,
	A.name,
	A.created_date,
	B.uid,
	B.comment,
	B.name,
	B.created_date
FROM
(
	SELECT
		X.uid,
		X.title,
		X.contents,
		X.created_by,
		X.created_date,
		Y.name
	FROM board X, customer Y
	WHERE X.created_by = Y.login_id
) A
LEFT OUTER JOIN
(
	SELECT
		X.uid,
		X.comment,
		X.created_by,
		X.created_date,
		Y.name
	FROM comment X, customer Y
	WHERE X.created_by = Y.login_id
) B
ON (A.uid = B.board_uid)
WHERE A.uid = 1
;

SELECT
	A.name,
	B.count
FROM customer A,
(
	SELECT
		created_by,
		COUNT(*) AS count
	FROM comment
) B
WHERE A.login_id = B.created_by
	AND B.count >= 10
;