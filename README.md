# User Authentication and Authorization
## Melody Chepkorir

### Technologies
* Java - Springboot
* mySQL database

### Prerequisites
* JDK-17
* mySQL 8

### Setup and Configurations
* Under application.properties file, change the credentials according to you database setup
* N/B: Database used is mySQL. If you have another database, remember to include relevant dependency driver in pom.xml then change the dialect in application.properties file


### Endpoints

*All endpoints requires jwt token except registration and login*

1. *Saving a user*:

*POST*: localhost:8080/api/auth/signup
* to signup as admin indicate role as admin
{
"username":"admin",
"email":"admin@gmail.com",
"password":"12345678",
"role":["admin"]
}

* to signup as user indicate role as user
* {
  "username":"user",
  "email":"user@gmail.com",
  "password":"12345678",
  "role":["user"]
  }
* 
*Note that roles expected are only ADMIN and USER*

*Expected Response*:
{
"message": "User registered successfully!"
}


2. *User Login*:

*POST*: localhost:8080/api/auth/signin
{
"username":"user",
"password":"12345678"
}

* *Expected Response*:
{
"id": 1,
"username": "user",
"email": "user@gmail.com",
"roles": [
"ROLE_ADMIN"
],
"accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZWxvIiwiaWF0IjoxNzA4MzUxODgzLCJleHAiOjE3MDg0MzgyODN9.INuoPLL9QuVfRpvZaaxZvnB-yAkt-_LcInUlqxTsnZQ",
"tokenType": "Bearer"
}

3. *post a blog. This can be done by admin and user*:
  * add token as bearer token to be able to access the page 
*POST* : localhost:8080/api/blog/create
  * Payload
  * {
    "title":"blog 5",
    "description":"this is is the fifth blog"
    }
* Response
    "title":"blog 5",
    "description":"this is is the fifth blog"
4. * Read a blog. also admin and user can read blogs*
* GET : localhost:8080/api/blog/read?page=0&size=10
* Response
* Should return 10 blogs 
5. * Update Blog.
* PUT : localhost:8080/api/blog/updating/2
* payload. ensure to pass blog id in the api.
* {
  "title": "Wakanda",
  "description": "this is an african movie"
  }

6. * Delete Blog ONly Admins can Delete A blog
* DELETE : localhost:8080/api/blog/delete/1
* delete blog by id
* response 
* "blog deleted Successfully"

7. * Search By Title or content
* GET : localhost:8080/api/blog/search?searchTerm=blog
* response. should return all data with blog
* {
  "title":"blog 5",
  "description":"this is is the fifth blog"
  }
8. * POST : add comment to a blog. We are adding by blog id. Both admins and user can add comments
* localhost:8080/api/comment/add/2
* payload 
* {
  "comment":"This was the best movie"
  }
9. * GET : Read comments. This Gets All comments for blog with id 2
* localhost:8080/api/comment/get-all/2?page=0&size=10
10. * Update comment :PUT localhost:8080/api/comment/update/4
* Both admin and user can update a comment.

11. * DELETE: delete comment. Admin can choose to delete on comment 
* localhost:8080/api/comment/delete/2. this will delete comment with id 2

* Admin can also choose to delete all comments belonging to a post
* * localhost:8080/api/comment/delete-by-blog/2
* This take id of a blog. so it will delete all comments for blog with id 2

#### NB:
* Remember to use bearer token.




