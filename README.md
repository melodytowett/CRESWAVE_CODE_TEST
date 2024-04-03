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

{
"username":"melo",
"email":"melo@gmail.com",
"password":"12345678",
"role":["admin"]
}

*Note that roles expected are only ADMIN and USER*

*Expected Response*:
{
"message": "User registered successfully!"
}


2. *User Login*:

*POST*: localhost:8080/api/auth/signin
{
"username":"melo",
"password":"12345678"
}

* *Expected Response*:
{
"id": 1,
"username": "melo",
"email": "melo@gmail.com",
"roles": [
"ROLE_ADMIN"
],
"accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZWxvIiwiaWF0IjoxNzA4MzUxODgzLCJleHAiOjE3MDg0MzgyODN9.INuoPLL9QuVfRpvZaaxZvnB-yAkt-_LcInUlqxTsnZQ",
"tokenType": "Bearer"
}


3. *view page viewed by both user and admin*:
  * add token as bearer token to be able to access the page 
*GET* : localhost:8080/api/test/all

Response
Public Content.


#### NB:
* Remember to use bearer token.
* Only users with admin privileges can access this route

4. *Page viewed by admin only*:

*PATCH*:localhost:8080/api/test/admin




