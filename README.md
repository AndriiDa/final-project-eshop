# FS7 DAN.IT Final Project
[![Build Status](https://travis-ci.com/AndriiDa/final-project-eshop.svg?branch=master)](https://travis-ci.com/AndriiDa/final-project-eshop)[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=fs7-final-project&metric=alert_status)](https://sonarcloud.io/dashboard?id=fs7-final-project)
[![Coverage Status](https://coveralls.io/repos/github/AndriiDa/final-project-eshop/badge.svg?branch=master)](https://coveralls.io/github/AndriiDa/final-project-eshop?branch=master)

## Run the application

### Run the backend
git clone https://github.com/AndriiDa/final-project-eshop.git  
cd final-project-eshop  
mvn spring-boot:run  

#### mapped endpoints:  
##### CATEGORY
GET http://localhost:9000/api/v1/categories - retrieve all categories
GET http://localhost:9000/api/v1/categories/?isgroup=true
GET http://localhost:9000/api/v1/categories/?isactine=true
GET http://localhost:9000/api/v1/categories/?name=<name>
GET http://localhost:9000/api/v1/categories/?code=<code>
GET http://localhost:9000/api/v1/categories/{id} - retrieve category with id={id}
POST http://localhost:9000/api/v1/categories - add a new category
PUT http://localhost:9000/api/v1/categories/{id} - update category with id={id}
DELETE http://localhost:9000/api/v1/categories/{id} - delete category with id={id}

##### PRODUCTS
http://localhost:9000/api/v1/products  

##### USERS
http://localhost:9000/api/v1/users  

The backend part is deployed to heroku.  
So the backend endpoints above can be accessed using the following links:  
https://fs7-eshop.herokuapp.com/api/v1/categories  
https://fs7-eshop.herokuapp.com/api/v1/products  
https://fs7-eshop.herokuapp.com/api/v1/users  

To be continued soon ...  
