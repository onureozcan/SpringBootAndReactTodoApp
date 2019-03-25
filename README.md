#### Spring Boot and React TODO app

An example todo list app using spring boot and react

**Backend port**:8080  
**Frontend port**: 1024

##### Build Using Maven:
    
    mvn --package

it builds both front and backend
 projects and starts serving them.
 It expects mysql to be up and running at 3306.  
 Uses maven frontend plugin to serve frontend
##### Build Using Docker:

Run command:
    
    docker-compose up

it builds both front and backend
 projects and starts serving them. 
 Additionally, it starts a mysql container.