-- Drop user first if they exist
DROP USER if exists 'demo'@'%' ;

-- Now create user with prop privileges
CREATE USER 'demo'@'%' IDENTIFIED BY 'test123';

GRANT ALL PRIVILEGES ON * . * TO 'demo'@'%';