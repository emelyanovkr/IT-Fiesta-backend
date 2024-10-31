Backend part of it-fiesta project. For now it stands as one service only. But I have plans to do things:
+ Create an abstract class for admin and host service to manage events (in progress)
  + Think about managing roles within admin permissions
+ Authentication (for now only JWT authentication supported) 
  + Manage access and refresh tokens
  + Create separate method for token validation and for acquiring username from token
+ Virtualization (for now only database is hosted with docker)
  + Dockerizing the whole backend with it's own internal network
+ Microservice architecture
  + Creating different microservices responsible for some parts in application (like event management, role management, authorization, admin panel, user panel, favourites and likes and etc.)
  + Try to use kafka, if needed, to communicate between services
+ Provide test coverage for critical parts of the application
+ Create readme
