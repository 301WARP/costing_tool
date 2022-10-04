/*
 * Test data for warp database.
 */

-- Switch to warp database
-- USE warp;

-- Researchers
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (1, 'DR', 'First', 'Last');
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (2, 'DR', 'Test', 'User');
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (3, 'PROF', 'Test', 'Professor');
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (4, 'MR', 'Another', 'Last');
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (5, 'MS', 'Another', 'Last');
INSERT INTO `researcher` (`staff_id`, `title`, `first_name`, `last_name`)
VALUES (6, 'MR', 'First', 'Also');