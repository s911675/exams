SELECT
    (
        CASE
            WHEN OLD.att_code IS NULL		THEN 'C' /* 추가 */
            WHEN OLD.change_type = 'D' AND NEW.att_code IS NOT NULL THEN 'C' /* 추가 */
            WHEN NEW.att_code IS NULL		THEN 'D' /* 삭제 */
            WHEN OLD.attval_rename != NEW.attval_rename THEN 'U' /* 수정 */
            ELSE 'N'	/* 변경 없음 */
        END
    ) AS change_type,
    (
    	CASE
    		WHEN NEW.att_code IS NULL THEN OLD.modelno
    		ELSE NEW.modelno
    	END
    ) AS modelno,
    (
    	CASE
    		WHEN NEW.att_code IS NULL THEN OLD.att_code
    		ELSE NEW.att_code
    	END
    ) AS att_code,
    (
    	CASE
    		WHEN NEW.attval_code IS NULL THEN OLD.attval_code
    		ELSE NEW.attval_code
    	END
    ) AS attval_code,
    (
    	CASE
    		WHEN NEW.att_code IS NULL THEN OLD.attval_rename
    		ELSE NEW.attval_rename
    	END
    ) AS attval_rename,
    (
    	CASE
    		WHEN NEW.att_code IS NULL THEN OLD.date
    		ELSE NEW.date
    	END
    ) AS date,
    (
    	CASE
    		WHEN NEW.att_code IS NULL THEN OLD.moddate
    		ELSE NEW.moddate
    	END
    ) AS moddate,
    (
    	CASE
    		WHEN NEW.att_code IS NULL THEN OLD.attval_del
    		ELSE NEW.attval_del
    	END
    ) AS attval_del
FROM
(
	SELECT
		change_type,
		modelno,
		att_code,
		attval_code,
		attval_rename,
		date,
		moddate,
		attval_del
	FROM at_athena_enuri_catalog_attribute
	WHERE yyyy='2018'
		AND mm = '08'
		AND dd = '16'
		AND type='00'
		AND CONCAT( CAST (modelno AS VARCHAR), '^', CAST (att_code AS VARCHAR), '^', CAST (attval_code AS VARCHAR)) NOT IN
		(
			SELECT CONCAT( CAST (modelno AS VARCHAR), '^', CAST (att_code AS VARCHAR), '^', CAST (attval_code AS VARCHAR))
			FROM at_athena_enuri_catalog_attribute
			WHERE yyyy='2018'
				AND mm = '08'
				AND dd = '16'
				AND type IN ('01','02')
		)
		
	UNION ALL

	SELECT
		change_type,
		modelno,
		att_code,
		attval_code,
		attval_rename,
		date,
		moddate,
		attval_del
	FROM at_athena_enuri_catalog_attribute
	WHERE yyyy='2018'
		AND mm = '08'
		AND dd = '16'
		AND type='01'
		AND CONCAT( CAST (modelno AS VARCHAR), '^', CAST (att_code AS VARCHAR), '^', CAST (attval_code AS VARCHAR)) NOT IN
		(
			SELECT CONCAT( CAST (modelno AS VARCHAR), '^', CAST (att_code AS VARCHAR), '^', CAST (attval_code AS VARCHAR))
			FROM at_athena_enuri_catalog_attribute
			WHERE yyyy='2018'
				AND mm = '08'
				AND dd = '16'
				AND type IN ('02')
		)
		
	UNION ALL

	SELECT
		change_type,
		modelno,
		att_code,
		attval_code,
		attval_rename,
		date,
		moddate,
		attval_del
	FROM at_athena_enuri_catalog_attribute
	WHERE yyyy='2018'
		AND mm = '08'
		AND dd = '16'
		AND type='02'
) OLD

FULL OUTER JOIN

(
	SELECT
		change_type,
		modelno,
		att_code,
		attval_code,
		attval_rename,
		date,
		moddate,
		attval_del
	FROM at_athena_enuri_catalog_attribute
	WHERE yyyy='2018'
		AND mm = '08'
		AND dd = '17'
		AND type='00'
) NEW

ON (OLD.modelno = NEW.modelno AND OLD.att_code = NEW.att_code AND OLD.attval_code = NEW.attval_code)

WHERE OLD.att_code is null
    OR NEW.att_code is null
    OR OLD.attval_rename != NEW.attval_rename
    OR (NEW.change_type IS NOT NULL AND OLD.change_type = 'D')
