#### Spring Boot and React TODO app

An example todo list app using spring boot and react

**Backend port**: 8080  
**Frontend port**: 1024

It uses spring boot, mysql and node. 
node and npm is provided by maven frontend plugin.

##### Build Using Maven:

frontend:
    
    cd frontend; mvn clean package 

It starts serving frontend project at port 1024.

Backend:

    cd backend; mvn clean package; mvn spring-boot:run
    
 This command above builds a jar and starts serving backend project at port 8080.
 It expects mysql to be up and running at 3306 and have a db called todolist 
 and a user that has access to the db with the credentials defined in application.properties.
 
 To create the user and the database, you can use mysql_init/init.sql
