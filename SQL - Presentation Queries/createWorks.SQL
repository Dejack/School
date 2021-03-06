DROP TABLE EMPLOYEE CASCADE CONSTRAINTS;
DROP TABLE MANAGER CASCADE CONSTRAINTS;
DROP TABLE OWNER CASCADE CONSTRAINTS;
DROP TABLE DRIVER CASCADE CONSTRAINTS;
DROP TABLE ATTENDANCE CASCADE CONSTRAINTS;
--DROP TABLE SALARY;
DROP TABLE SALARY_HIST CASCADE CONSTRAINTS;
--DROP TABLE BUS_DRIVER;
DROP TABLE BUS CASCADE CONSTRAINTS;
DROP TABLE TICKET CASCADE CONSTRAINTS;
DROP TABLE MAINTENANCE_HIST CASCADE CONSTRAINTS;
DROP TABLE WORKS CASCADE CONSTRAINTS;
DROP TABLE ROUTE CASCADE CONSTRAINTS;
DROP TABLE ROUTE_STOP CASCADE CONSTRAINTS;
DROP TABLE STOP CASCADE CONSTRAINTS;
DROP TABLE ISSUE CASCADE CONSTRAINTS;


CREATE TABLE EMPLOYEE(
    EMP_ID        CHAR(3)    NOT NULL,
    EMP_PHONE_NUMBER    CHAR(10)    NOT NULL,
    EMP_FNAME    VARCHAR(20)    NOT NULL,
	EMP_LNAME    VARCHAR(20)    NOT NULL,
	EMP_HIRE_DATE    DATE    NOT NULL,
	EMP_AGE    NUMBER(2)    NOT NULL,
    PRIMARY KEY (EMP_ID)
	--FOREIGN KEY (EMP_ID) REFERENCES MANAGER ON DELETE CASCADE
); 

CREATE TABLE MANAGER(
    EMP_ID        CHAR(3)    NOT NULL,
    MAN_EMAIL    VARCHAR(20)    NOT NULL,
    MAN_OFFICE_NUM    CHAR(2)    NOT NULL,
    PRIMARY KEY (EMP_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEE ON DELETE CASCADE
); 

CREATE TABLE OWNER(
    EMP_ID        CHAR(3)    NOT NULL,
    OWN_EMP_SALARY    NUMBER(10)    NOT NULL,
    OWN_REVENUE    NUMBER(10)    NOT NULL,
	OWN_PROFIT    NUMBER(10)    NOT NULL,
    PRIMARY KEY (EMP_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEE ON DELETE CASCADE
); 

CREATE TABLE DRIVER(
    EMP_ID        CHAR(3)    NOT NULL,
    DRIVER_LICENSE_TYPE        VARCHAR(1)    NOT NULL,
    PRIMARY KEY (EMP_ID),
	FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEE ON DELETE CASCADE
); 

CREATE TABLE ATTENDANCE(
    EMP_ID        CHAR(3)    NOT NULL,
    ATTEND_DATE    DATE    NOT NULL,
    ATTEND_START_TIME    CHAR(4),
	ATTEND_END_TIME    CHAR(4),
    PRIMARY KEY (EMP_ID, ATTEND_DATE),
	FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEE ON DELETE CASCADE
); 

CREATE TABLE SALARY_HIST(
   EMP_ID 		  CHAR (3) NOT NULL,
   EMP_HIRE_DATE    DATE	NOT NULL,
   SALARY_YEAR     NUMBER(10) NOT NULL,
   PRIMARY KEY (EMP_ID, EMP_HIRE_DATE, SALARY_YEAR),
   FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEE ON DELETE CASCADE
); 

--CREATE TABLE SALARY(
  -- EMP_ID 		  CHAR (3) NOT NULL,
 --  EMP_HIRE_DATE    DATE	NOT NULL,
 --  SALARY     NUMBER(10) NOT NULL,
 --  PRIMARY KEY (EMP_ID, EMP_HIRE_DATE),
--   FOREIGN KEY (EMP_ID) REFERENCES EMPLOYEE ON DELETE CASCADE
--); 

--CREATE TABLE BUS_DRIVER(
--	EMP_ID        CHAR(3)    NOT NULL,
 --   BUS_ID    CHAR(2)    NOT NULL,
--	PRIMARY KEY (EMP_ID, BUS_ID),
--	FOREIGN KEY (EMP_ID) REFERENCES DRIVER ON DELETE CASCADE,
--	FOREIGN KEY (BUS_ID) REFERENCES BUS ON DELETE CASCADE
--);

CREATE TABLE ROUTE(
	ROUTE_NUM       CHAR(2)    NOT NULL,
    ROUTE_NAME    VARCHAR(20)    NOT NULL,
	PRIMARY KEY (ROUTE_NUM)
);

CREATE TABLE STOP(
    STOP_NUM    CHAR(3)    NOT NULL,
	STOP_ADDRESS    VARCHAR(20)    NOT NULL,
	PRIMARY KEY (STOP_NUM)
);

CREATE TABLE BUS(
    BUS_ID    CHAR(2)    NOT NULL,
	BUS_PASS_COUNT    NUMBER(2)    NOT NULL,
	BUS_SEAT_CAPACITY    NUMBER(2)    NOT NULL,
	BUS_TYPE    CHAR(1)    NOT NULL,
	BUS_AGE    NUMBER(2)    NOT NULL,
	BUS_FARE    NUMBER(3,2)    NOT NULL,
	BUS_LOCATION   VARCHAR(20)    NOT NULL,
	PRIMARY KEY (BUS_ID)
);

CREATE TABLE WORKS(
	EMP_ID        CHAR(3)    NOT NULL,
    ROUTE_NUM    CHAR(2)    NOT NULL,
	BUS_ID       CHAR(2)    NOT NULL,
	PRIMARY KEY (EMP_ID, ROUTE_NUM, BUS_ID),
	FOREIGN KEY (EMP_ID) REFERENCES DRIVER ON DELETE CASCADE,
    FOREIGN KEY (ROUTE_NUM) REFERENCES ROUTE ON DELETE CASCADE,
	FOREIGN KEY (BUS_ID) REFERENCES BUS ON DELETE CASCADE
);

CREATE TABLE ROUTE_STOP(
	ROUTE_NUM        CHAR(2)    NOT NULL,
    STOP_NUM    CHAR(3)    NOT NULL,
	PRIMARY KEY (ROUTE_NUM, STOP_NUM),
	FOREIGN KEY (ROUTE_NUM) REFERENCES ROUTE ON DELETE CASCADE,
    FOREIGN KEY (STOP_NUM) REFERENCES STOP ON DELETE CASCADE
);

CREATE TABLE TICKET(
    TICKET_NUM    CHAR(5)    NOT NULL,
	TICKET_COST    NUMBER(3,2)    NOT NULL,
	PRIMARY KEY (TICKET_NUM)
);

CREATE TABLE ISSUE(
    BUS_ID    CHAR(2)    NOT NULL,
	TICKET_NUM    CHAR(5)    NOT NULL,
	ISSUE_DATE    DATE    NOT NULL,
	
	PRIMARY KEY (BUS_ID, TICKET_NUM),
	FOREIGN KEY (BUS_ID) REFERENCES BUS ON DELETE CASCADE,
	FOREIGN KEY (TICKET_NUM) REFERENCES TICKET ON DELETE CASCADE
);

CREATE TABLE MAINTENANCE_HIST(
    BUS_ID    CHAR(2)    NOT NULL,
	MAINT_DATE    DATE    NOT NULL,
	MAINT_COST NUMBER(7,2)     NOT NULL,
	PRIMARY KEY (BUS_ID, MAINT_DATE),
	FOREIGN KEY (BUS_ID) REFERENCES BUS ON DELETE CASCADE
);



INSERT INTO EMPLOYEE
VALUES ('100', '6045432345', 'Benjamin', 'Yu', to_date('09-09-1985','mm/dd/yyyy'), 54); 
INSERT INTO EMPLOYEE
VALUES ('123', '6042344564', 'Bobby', 'Chan', to_date('10-23-1990','mm/dd/yyyy'), 40);
INSERT INTO EMPLOYEE
VALUES ('127', '7782322342', 'Mary', 'Jane', to_date('07-13-1992','mm/dd/yyyy'), 37); 
INSERT INTO EMPLOYEE
VALUES ('314', '7785451234', 'Richard', 'Smith', to_date('01-01-2001','mm/dd/yyyy'), 27);
INSERT INTO EMPLOYEE
VALUES ('315', '6045452131', 'Tim', 'Thomas', to_date('01-01-2001','mm/dd/yyyy'), 28);
INSERT INTO EMPLOYEE
VALUES ('316', '6043435152', 'Laura', 'Horton', to_date('06-09-2012','mm/dd/yyyy'), 31);

INSERT INTO MANAGER VALUES('123','bobbychan@bus.com',5);
INSERT INTO MANAGER VALUES('127','maryjane@bus.com',12);

INSERT INTO OWNER VALUES('100', 337800, 654000, 290234);

INSERT INTO DRIVER VALUES('314', 4);
INSERT INTO DRIVER VALUES('316', 5);
INSERT INTO DRIVER VALUES('315', 5);


--------------------------------------------------- change this to the day we're presenting!!!!!!!! IMPORTANT ************************
INSERT INTO ATTENDANCE VALUES('100', to_date('01-01-2013','mm/dd/yyyy'), '0600', '2000');
INSERT INTO ATTENDANCE VALUES('123', to_date('01-01-2013','mm/dd/yyyy'), '0600', '1600');
INSERT INTO ATTENDANCE VALUES('314', to_date('01-01-2013','mm/dd/yyyy'), '0600', '1800');
INSERT INTO ATTENDANCE VALUES('314', to_date('12-02-2015','mm/dd/yyyy'), NULL, NULL);
--------------------------------------------------- change this to the day we're presenting!!!!!!!! IMPORTANT ************************


INSERT INTO SALARY_HIST VALUES('314', to_date('09-09-2006','mm/dd/yyyy'), 29000);
INSERT INTO SALARY_HIST VALUES('315', to_date('01-05-2013','mm/dd/yyyy'), 23000);

INSERT INTO BUS VALUES('22', 34, 80, 'L', 4, 3.50, 'Edmonds/Mary');
INSERT INTO BUS VALUES('13', 54, 60, 'M', 2, 4.00, 'Main/12th');
INSERT INTO BUS VALUES('30', 24, 60, 'M', 1, 4.00, 'Hastings/Parker');

INSERT INTO ROUTE VALUES('15', 'Metrotown 1');
INSERT INTO ROUTE VALUES('8', 'Edmonds 2');
INSERT INTO ROUTE VALUES('6', 'Hastings 1');

INSERT INTO WORKS VALUES('314', '15', '22');
INSERT INTO WORKS VALUES('316', '8', '13');
INSERT INTO WORKS VALUES('315', '6', '30');

----------------------------------------------------------------------
INSERT INTO TICKET VALUES('54231', '3.50');
INSERT INTO TICKET VALUES('54232', '4.00');
INSERT INTO TICKET VALUES('54233', '4.00');
INSERT INTO TICKET VALUES('54234', '3.50');
--
INSERT INTO TICKET VALUES('54235', '4.00');
INSERT INTO TICKET VALUES('54236', '4.00');
INSERT INTO TICKET VALUES('54237', '3.50');
INSERT INTO TICKET VALUES('54238', '4.00');
--
INSERT INTO TICKET VALUES('54239', '3.50');
INSERT INTO TICKET VALUES('54240', '4.00');
INSERT INTO TICKET VALUES('54241', '4.00');
INSERT INTO TICKET VALUES('54242', '4.00');
-----------------------------------------------------------------------
INSERT INTO STOP VALUES('312', 'Edmonds/Mary');
INSERT INTO STOP VALUES('125', 'Main/12th');
INSERT INTO STOP VALUES('126', 'Main/13th');
INSERT INTO STOP VALUES('127', 'Main/15th');
INSERT INTO STOP VALUES('128', 'Main/16th');
INSERT INTO STOP VALUES('129', 'Main/17th');
INSERT INTO STOP VALUES('140', 'Hastings/Parker');
INSERT INTO STOP VALUES('141', 'Hastings/Holdom');

INSERT INTO ROUTE_STOP VALUES('15', '312');
INSERT INTO ROUTE_STOP VALUES('8', '125');
INSERT INTO ROUTE_STOP VALUES('8', '126');
INSERT INTO ROUTE_STOP VALUES('8', '127');
INSERT INTO ROUTE_STOP VALUES('8', '128');
INSERT INTO ROUTE_STOP VALUES('8', '129');
INSERT INTO ROUTE_STOP VALUES('6', '140');
INSERT INTO ROUTE_STOP VALUES('6', '141');


INSERT INTO ISSUE VALUES('22', '54231', to_date('11-13-2015','mm/dd/yyyy'));
INSERT INTO ISSUE VALUES('13', '54232', to_date('11-14-2015','mm/dd/yyyy'));
INSERT INTO ISSUE VALUES('13', '54233', to_date('11-15-2015','mm/dd/yyyy'));
INSERT INTO ISSUE VALUES('22', '54234', to_date('11-15-2015','mm/dd/yyyy'));

INSERT INTO ISSUE VALUES('13', '54235', to_date('11-16-2015','mm/dd/yyyy'));
INSERT INTO ISSUE VALUES('30', '54236', to_date('11-17-2015','mm/dd/yyyy'));
INSERT INTO ISSUE VALUES('22', '54237', to_date('11-18-2015','mm/dd/yyyy'));
INSERT INTO ISSUE VALUES('13', '54238', to_date('11-19-2015','mm/dd/yyyy'));

INSERT INTO ISSUE VALUES('22', '54239', to_date('11-20-2015','mm/dd/yyyy'));
INSERT INTO ISSUE VALUES('13', '54240', to_date('11-20-2015','mm/dd/yyyy'));
INSERT INTO ISSUE VALUES('30', '54241', to_date('11-22-2015','mm/dd/yyyy'));
INSERT INTO ISSUE VALUES('13', '54242', to_date('11-24-2015','mm/dd/yyyy'));

INSERT INTO MAINTENANCE_HIST VALUES('22', to_date('07-06-2013','mm/dd/yyyy'), 3400.75);
INSERT INTO MAINTENANCE_HIST VALUES('13', to_date('06-02-2013','mm/dd/yyyy'), 2300.50);
INSERT INTO MAINTENANCE_HIST VALUES('30', to_date('06-05-2013','mm/dd/yyyy'), 2750.50);