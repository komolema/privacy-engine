--organisation
INSERT INTO organisation (name, key, id)
VALUES ('Walmart', 'w123', 1);
INSERT INTO organisation (name, key, id)
VALUES ('Emirates', 'e123', 2);
INSERT INTO organisation (name, key, id)
VALUES ('HSBC Bank', 'h123', 3);
INSERT INTO organisation (name, key, id)
VALUES ('Facebook', 'f23', 4);
INSERT INTO organisation (name, key, id)
VALUES ('Twitter', 't123', 5);


--context
INSERT INTO context (name, key, id)
VALUES ('Marketing', 'ct1', 100);
INSERT INTO context (name, key, id)
VALUES ('Fraud', 'ct2', 200);
INSERT INTO context (name, key, id)
VALUES ('Analytics', 'ct3', 300);

--data_owner
INSERT INTO data_owner (name, surname, key, id)
VALUES ('Clark', 'Kent','ck1', 1);
INSERT INTO data_owner (name, surname, key, id)
VALUES ('John', 'Doe', 'jd1', 2);
INSERT INTO data_owner (name, surname, key, id)
VALUES ('Mary', 'Jane', 'mj1', 3);

--policy
INSERT INTO policy (name, id)
VALUES ('Reject', 1000);
INSERT INTO policy (name, id)
VALUES ('Allow', 2000);

--policy_context
INSERT INTO policy_context ("dataOwnerId", "contextId", "policyId", id)
VALUES (1, 100, 1000, 1);
INSERT INTO policy_context ("dataOwnerId", "contextId", "policyId", id)
VALUES (1, 200, 2000, 2);
INSERT INTO policy_context ("dataOwnerId", "contextId", "policyId", id)
VALUES (1, 300, 1000, 3);
INSERT INTO policy_context ("dataOwnerId", "contextId", "policyId", id)
VALUES (2, 100, 2000, 4);
INSERT INTO policy_context ("dataOwnerId", "contextId", "policyId", id)
VALUES (2, 200, 2000, 5);
INSERT INTO policy_context ("dataOwnerId", "contextId", "policyId", id)
VALUES (2, 300, 2000, 6);
INSERT INTO policy_context ("dataOwnerId", "contextId", "policyId", id)
VALUES (3, 100, 2000, 7);
INSERT INTO policy_context ("dataOwnerId", "contextId", "policyId", id)
VALUES (3, 200, 1000, 8);
INSERT INTO policy_context ("dataOwnerId", "contextId", "policyId", id)
VALUES (3, 300, 1000, 9);