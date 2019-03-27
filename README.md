#### Spring Boot and React TODO app

An example todo list app using spring boot and react

**Backend port**: 8080  
**Frontend port**: 1024

##### Build Using Maven:

frontend:
    
    cd frontend; mvn clean package 

It starts serving frontend project at port 1024.

Backend:

    cd backend; mvn clean package; mvn spring-boot:run
    
 This command above builds a jar and starts serving backend project at port 8080.
 It expects mysql to be up and running at 3306.  

##### Build Using Docker:

Run command:
    
    docker-compose up

it builds both front and backend
 projects and starts serving them. 
 Additionally, it starts a mysql container.