INSERT INTO CUSTOMER(id,name,city) VALUES (1, 'Subramanian','Coimbatore');
INSERT INTO CUSTOMER(id,name,city) VALUES (2, 'Murugan','Coimbatore');
INSERT INTO CUSTOMER(id,name,city) VALUES (3, 'Geetha','Chennai');
ALTER SEQUENCE customer_seq RESTART WITH 4;

INSERT INTO EMPLOYEE(id,name,city) VALUES (1, 'Subramanian','Coimbatore');
INSERT INTO EMPLOYEE(id,name,city) VALUES (2, 'Murugan','Coimbatore');
INSERT INTO EMPLOYEE(id,name,city) VALUES (3, 'Geetha','Chennai');
ALTER SEQUENCE employee_seq RESTART WITH 4;
