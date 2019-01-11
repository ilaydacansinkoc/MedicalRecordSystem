--admin

--1 : Lk9eiuR2
--2 : n6AljXN

insert into users (first_name, last_name, birth_date, username, password, is_authorized, role_id) values ('Cassi', 'Coulton', '02/24/1991', 'ccoulton0', '3499B3621624AE5DDA3FFDE039B5E2694E8FF3E7EFB35D79645AD996408568D1', 'true', 1);
insert into users (first_name, last_name, birth_date, username, password, is_authorized, role_id) values ('Myranda', 'Bradder', '02/28/1972', 'mbradder1', 'FE0CB77C8F45DA92C2E2CE0D2915AE4A9AFB901D62EAE0F7C2F507F3995593B8','true', 1);

--doctor

--1 : hn7rb8y
--2 : kYdqc7A6zDmi
insert into users (first_name, last_name, birth_date, username, password, is_authorized, role_id) values ('Brandy', 'Towndrow', '09/13/2000', 'btowndrow2', '0EE1832B98014D0D603AE3E7614DE9EA140FDD50153BD90A70A65E1CAB4A60B3','true', 2);
insert into users (first_name, last_name, birth_date, username, password, is_authorized, role_id) values ('Robenia', 'Hedingham', '06/16/1971', 'rhedingham3', '4DF1864B11FED0033B331DA7F68EF75B709299FD9206B6E69D8409E7CCC32F71','true', 2);


--nurse

--1 : 8htfrSrmtUi
--2 : n1D84m

insert into users (first_name, last_name, birth_date, username, password, is_authorized, role_id) values ('Lurline', 'Falkingham', '06/19/2000', 'lfalkingham4', '84876B7E1FD6562858171CF8029F1656EE429DE17A0BC17A582C72D38FF15297','true', 3);
insert into users (first_name, last_name, birth_date, username, password, is_authorized, role_id) values ('Tallia', 'Mesant', '06/11/1979', 'tmesant5', '449E7D9B882930BD78EC19592083E1DE231B389EA8D0408717933DD4BEAF5E2F','true',3);

--patient

--1 : VGok4i0
--2 : DxpByXOe
insert into users (first_name, last_name, birth_date, username, password, is_authorized, role_id) values ('Loretta', 'Kinastan', '12/21/1972', 'lkinastan6', '911A6F78736A59595320C8844AAAF262C5043321C66BA6FBD07975956594DD14','true', 4);
insert into users (first_name, last_name, birth_date, username, password, is_authorized, role_id) values ('Philippe', 'Gildea', '02/02/1975', 'pgildea7', '8161DEF95F624A7EFABA6B91855278192C820465D6E5CBA3EB7B90BABAD5760C','true', 4);

--relative

--1 : qLjICbAmy62
--2 : HLMH2l3av8
insert into users (first_name, last_name, birth_date, username, password, is_authorized, role_id) values ('Henka', 'Holbury', '04/06/1972', 'hholbury8', 'D4BD4BF77C0AD5CE23FEAF1382D1E0CFD12505BF4CB4C13F749919D68009F571','true', 5);
insert into users (first_name, last_name, birth_date, username, password, is_authorized, role_id) values ( 'Tommi', 'Zecchii', '01/24/1983', 'tzecchii9', 'BBE522162C4B6048FE90B39A9F5D15141A92F684DFA2078563B3D2B097EF26DE','true', 5);



insert into relatives (relative_first_name, relative_last_name,patient_id) values ('Sam','Jones',7);
insert into relatives (relative_first_name, relative_last_name,patient_id) values ('Brigitte','Lindholm',8);


-- test için
 insert into patients (patient_first_name, patient_last_name, birth_date , doctor_id, patient_id) values ('Alara','Koc','11/11/11', 3, 100);
 insert into patients (patient_first_name, patient_last_name, birth_date , doctor_id, patient_id) values ('Dilara','Koc','11/11/11',3, 101);
 insert into patients (patient_first_name, patient_last_name, birth_date , doctor_id, patient_id) values ('İlayda','Koc','11/11/11',3, 102);
 insert into patients (patient_first_name, patient_last_name, birth_date , doctor_id, patient_id) values ('Zela','Koc','11/11/11',  3, 103);

 insert into patient_details (diagnostic, prescription,user_id , doctor_id) values ('Diag1','Pres1',7,3);
 insert into patient_details (diagnostic, prescription,user_id , doctor_id) values ('Diag2','Pres2',7,3);
 insert into patient_details (diagnostic, prescription,user_id , doctor_id) values ('Diag3','Pres3',7,3);
 insert into patient_details (diagnostic, prescription,user_id , doctor_id) values ('Diag4','Pres4',7,3);