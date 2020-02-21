create database library;
create user 'root'@'%' identified by 'root';
grant all on library.* to 'root'@'%';
use library
create table books( id INT NOT NULL AUTO_INCREMENT, book_name VARCHAR(20) NOT NULL, author_name VARCHAR(20) NOT NULL, isbn VARCHAR(20) NOT NULL, PRIMARY KEY (id));
