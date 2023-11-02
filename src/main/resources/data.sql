INSERT INTO device (id, system_name, type) VALUES (1, Windows','Windows Workstation' );
INSERT INTO device (id, system_name, type) VALUES (2, Mac OS','Mac' );

INSERT INTO service (id, name, cost) VALUES (1, 'Antivirus for Windows', 5.0);
INSERT INTO service (id, name, cost) VALUES (2, 'Antivirus for Mac', 7.0);
INSERT INTO service (id, name, cost) VALUES (3, 'Backup', 3.0);
INSERT INTO service (id, name, cost) VALUES (4, 'Screen Share', 1.0);


-- Windows device services
INSERT INTO device_service (device_id, service_id) VALUES (1, 1); -- Antivirus for Windows
INSERT INTO device_service (device_id, service_id) VALUES (1, 3); -- Backup
INSERT INTO device_service (device_id, service_id) VALUES (1, 4); -- Screen Share
INSERT INTO device_service (device_id, service_id) VALUES (1, 4); -- Screen Share

-- Mac device services
INSERT INTO device_service (device_id, service_id) VALUES (2, 2); -- Antivirus for Mac
INSERT INTO device_service (device_id, service_id) VALUES (2, 2); -- Antivirus for Mac
INSERT INTO device_service (device_id, service_id) VALUES (2, 2); -- Antivirus for Mac
INSERT INTO device_service (device_id, service_id) VALUES (2, 3); -- Backup
INSERT INTO device_service (device_id, service_id) VALUES (2, 3); -- Backup
INSERT INTO device_service (device_id, service_id) VALUES (2, 4); -- Screen Share
INSERT INTO device_service (device_id, service_id) VALUES (2, 4); -- Screen Share
