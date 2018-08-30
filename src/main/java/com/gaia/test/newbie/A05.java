package com.gaia.test.newbie;

public class A05 {
/*
Create table Board (
	uid				number(10) primary key,
	title			varchar2(100),
	contents		varchar2(4000),
	created_by		varchar2(20),
	created_date	date
);

Create table Comment (
	uid				number(10) primary key,
	comment			varchar2(400),
	board_uid		number(8),
	created_by		varchar2(20),
	created_date	date
);

Create table Customer (
	uid				number(10) primary key,
	login_id		varchar2(20),
	name			number(50),
	created_by		varchar2(20),
	created_date	date
);
 */
/*
	조회 조건: 게시물 번호(Board.uid)가 1인 경우
	출력 데이터: 게시물 번호 / 게시물 타이틀 / 게시물 내용 / 게시물 작성자 이름 / 게시물 작성일
	             댓글 번호 / 댓글 내용 / 댓글 작성자 이름 / 댓글 작성일
*/
	String answer1 = ""
			+ " SELECT"
			+ "		BORD.uid,"
			+ "		BORD.title,"
			+ "		BORD.contents,"
			+ "		BORD.created_by,"
			+ "		BORD.created_date,"
			+ "		CMMT.uid,"
			+ "		CMMT.comment,"
			+ "		CSTM.name,"
			+ "		CMMN.create_date"
			+ "	FROM Board BORD, Comment CMMT, Customer CSTM "
			+ "	WHERE BORD.uid = CMMT.board_uid AND CMMT.created_by = CSTM.uid"
			+ "		AND BORD.uid = 1";

	
	/*
	(6)	위 테이블에 대해, 댓글을 10개 이상 작성한 사람의 목록을 출력하는 SQL을 작성하라. 
	출력 데이터: 댓글 작성자 이름 / 댓글 개수
	*/
	String answer2 = ""
			+ "	SELECT" + 
			"		CSMR.name," + 
			"		CMMT.cnt" + 
			"	FROM Customer CSMR," + 
			"	(" + 
			"		SELECT" + 
			"			created_by," + 
			"			COUNT(1) AS cnt" + 
			"		FROM comment" + 
			"		GROUP BY created_by" + 
			"	) CMMT" + 
			"	WHERE CMMT.created_by = CSMR.uid";

	
/*
(7)	다음 Oracle 데이터 타입과 관련된 내용에 대해 설명하라.
A.		CHAR와 VARCHAR2 타입의 차이
		고정길이		가변길이
최대길이	2000		4000
	CHAR 데이터가 컬럼길이보다 작을 시 나머지 부분을 공백으로 채움.
B.	DATE 타입을 VARCHAR2 타입으로 변환하기 위해 TO_CHAR 함수를 이용하는 방법
	TO_CHAR(변환 대상) OR TO_CHAR(변환 대상, 포맷 문자열)
C.	TO_CHAR 함수나 TO_DATE 함수를 이용할 때 DATE_FORMAT을 입력하지 않은 경우 처리되는 방식
	변환대상이 Date 인 경우 Default Date format
	TimeStamp와 TimeStamp With Local Time Zon 은 Default TimeStamp with local Time Zone format
	TimeStamp With Time Zone 은 Default TimeStamp with Time Zone format 으로 변환된다.
*/
	String answer10 ="Primary Key 는 레코드의 고유 식별 키이다. 대부분의 Dbms 에서 Null 을 허용치 않는다."
			+ "Unique Key 는 해당 칼럼에 입려되는 데이터가 각각 유일하다는 것을 보장하기 위한 제약조건으로 한 테이블에 여러 개 설정이 가능하다. 기본키는 unique key 에 포함된다.";
	
	/*
	 (11)	0으로 채우려 할 때, 어떤 함수를 이용하면 되나?
	 */
	String answer11 = "Lpad(data, 10, '0')";
	
	/*
	 (12)	 CU_CUSTOMER 테이블에는 LOGIN_ID라는 UNIQUE KEY 컬럼이 존재한다고 하자. 이 때, CU_CUSTOMER 테이블 내에 ‘sys4u’라는 LOGIN_ID를 갖는 행이 있는지 여부를 확인하는 쿼리를 작성하라. 
COUNT 함수를 이용하는 방법과 EXISTS 키워드를 이용하는 방법 두 가지를 각각 사용하고, 그 두 방법의 차이에 대해 설명하라.

	 */
	String answer12 = "Lpad(data, 10, '0')";
	
}
