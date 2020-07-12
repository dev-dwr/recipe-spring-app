
##conntect to mysql and run as root user
#Create Databases
CREATE DATABASE app_dev;
CREATE DATABASE app_prod;


#Create database service accounts
CREATE USER 'app_dev_user'@'localhost' IDENTIFIED BY 'dwr';
CREATE USER 'app_prod_user'@'localhost' IDENTIFIED BY 'dwr';
#script statements for connecting from any host
CREATE USER 'app_dev_user'@'%' IDENTIFIED  BY 'dwr';
CREATE USER 'app_prod_user'@'%' IDENTIFIED  BY 'dwr';



#Database grants
GRANT SELECT ON app_dev.* to 'app_dev_user'@'localhost';
GRANT INSERT ON app_dev.* to 'app_dev_user'@'localhost';
GRANT DELETE ON app_dev.* to 'app_dev_user'@'localhost';
GRANT UPDATE ON app_dev.* to 'app_dev_user'@'localhost';
GRANT SELECT ON app_prod.* to 'app_prod_user'@'localhost';
GRANT INSERT ON app_prod.* to 'app_prod_user'@'localhost';
GRANT UPDATE ON app_prod.* to 'app_prod_user'@'localhost';
GRANT SELECT ON app_dev.* to 'app_dev_user'@'%';
GRANT INSERT ON app_dev.* to 'app_dev_user'@'%';
GRANT DELETE ON app_dev.* to 'app_dev_user'@'%';
GRANT UPDATE ON app_dev.* to 'app_dev_user'@'%';
GRANT SELECT ON app_prod.* to 'app_prod_user'@'%';
GRANT INSERT ON app_prod.* to 'app_prod_user'@'%';
GRANT DELETE ON app_prod.* to 'app_prod_user'@'%';
GRANT UPDATE ON app_prod.* to 'app_prod_user'@'%';