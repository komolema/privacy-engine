INSERT INTO category(name,id)
    VALUES ('Retail',1);
INSERT INTO category(name,id)
    VALUES ('Banking',2);
INSERT INTO category(name,id)
    VALUES ('Telecommunication',3);
INSERT INTO category(name,id)
    VALUES ('Media',4);
INSERT INTO category(name,id)
VALUES ('Social Network',5);

INSERT INTO organisation(name,key,id)
    VALUES('Pick n Pay','pnp123',1);
INSERT INTO organisation(name,key,id)
    VALUES('Markhams','m123',2);
INSERT INTO organisation(name,key,id)
    VALUES('First National Bank','fnb123',3);
INSERT INTO organisation(name,key,id)
    VALUES('Standard Bank','sb23',4);
INSERT INTO organisation(name,key,id)
    VALUES('etv','e123',5);
INSERT INTO organisation(name,key,id)
    VALUES('MultiChoice','dstv123',6);
INSERT INTO organisation(name,key,id)
    VALUES('MTN','m123',7);
INSERT INTO organisation(name,key,id)
    VALUES('Vodacom','v123',8);
INSERT INTO organisation(name,key,id)
    VALUES('Twitter','t123',9);
INSERT INTO organisation(name,key,id)
    VALUES('Facebook','f123',10);

--context
INSERT INTO context(name, id, "organisationId")
    VALUES('Smart Shopper',100,1);
INSERT INTO context(name, id, "organisationId")
    VALUES('Markhams Sharing',200,2);
INSERT INTO context(name, id, "organisationId")
    VALUES('fnbdata',300,3);
INSERT INTO context(name, id, "organisationId")
    VALUES('stddata',400,4);
INSERT INTO context(name, id, "organisationId")
    VALUES('etvdata',500,5);
INSERT INTO context(name, id, "organisationId")
    VALUES('dstv',600,6);
INSERT INTO context(name, id, "organisationId")
    VALUES('MTN-Calls',700,7);
INSERT INTO context(name, id, "organisationId")
    VALUES('Vodacom-Calls',800,8);
INSERT INTO context(name, id, "organisationId")
    VALUES('Twitter-Followers',900,9);
INSERT INTO context(name, id, "organisationId")
    VALUES('Facebook-Graph',1000,10);

--"OrganisationCategory"
INSERT INTO "OrganisationCategory"("categoryId","organisationId")
    VALUES(1,1);
INSERT INTO "OrganisationCategory"("categoryId","organisationId")
    VALUES(1,2);
INSERT INTO "OrganisationCategory"("categoryId","organisationId")
    VALUES(2,3);
INSERT INTO "OrganisationCategory"("categoryId","organisationId")
    VALUES(2,4);
INSERT INTO "OrganisationCategory"("categoryId","organisationId")
    VALUES(3,5);
INSERT INTO "OrganisationCategory"("categoryId","organisationId")
    VALUES(3,6);
INSERT INTO "OrganisationCategory"("categoryId","organisationId")
    VALUES(4,7);
INSERT INTO "OrganisationCategory"("categoryId","organisationId")
    VALUES(4,8);
INSERT INTO "OrganisationCategory"("categoryId","organisationId")
    VALUES(5,9);
INSERT INTO "OrganisationCategory"("categoryId","organisationId")
    VALUES(5,10);

--peuser
INSERT INTO peuser(name,surname,key,alias,id)
    VALUES('Karabo','Molema','km123','karabom',1);
INSERT INTO peuser(name,surname,key,alias,id)
    VALUES('John','Doe','jd123','Johnie300',2);
INSERT INTO peuser(name,surname,key,alias,id)
    VALUES('North','West','nw123','north',3);

--policy
INSERT INTO policy(name,description,handler,id)
    VALUES('Reject','Rejects every request for data sharing','Reject',1000);
INSERT INTO policy(name,description,handler,id)
    VALUES('Allow','Allow every request for data sharing','Allow',2000);

--configuration
INSERT INTO configuration("peuserId", "dateOfConfiguration", "policyId", id, "contextId")
    VALUES(1,CURRENT_TIMESTAMP,2000,112,100);
INSERT INTO configuration("peuserId", "dateOfConfiguration", "policyId", id, "contextId")
    VALUES(1,CURRENT_TIMESTAMP,1000,113,200);
INSERT INTO configuration("peuserId", "dateOfConfiguration", "policyId", id, "contextId")
    VALUES(1,CURRENT_TIMESTAMP,1000,114,300);
INSERT INTO configuration("peuserId", "dateOfConfiguration", "policyId", id, "contextId")
    VALUES(1,CURRENT_TIMESTAMP,2000,115,400);
INSERT INTO configuration("peuserId", "dateOfConfiguration", "policyId", id, "contextId")
    VALUES(1,CURRENT_TIMESTAMP,1000,116,500);
INSERT INTO configuration("peuserId", "dateOfConfiguration", "policyId", id, "contextId")
    VALUES(1,CURRENT_TIMESTAMP,2000,117,600);
INSERT INTO configuration("peuserId", "dateOfConfiguration", "policyId", id, "contextId")
    VALUES(1,CURRENT_TIMESTAMP,1000,118,700);
INSERT INTO configuration("peuserId", "dateOfConfiguration", "policyId", id, "contextId")
    VALUES(1,CURRENT_TIMESTAMP,2000,119,800);
INSERT INTO configuration("peuserId", "dateOfConfiguration", "policyId", id, "contextId")
    VALUES(1,CURRENT_TIMESTAMP,1000,120,900);
INSERT INTO configuration("peuserId", "dateOfConfiguration", "policyId", id, "contextId")
    VALUES(1,CURRENT_TIMESTAMP,2000,121,1000);
INSERT INTO configuration("peuserId", "dateOfConfiguration", "policyId", id, "contextId")
    VALUES(2,CURRENT_TIMESTAMP,2000,211,100);