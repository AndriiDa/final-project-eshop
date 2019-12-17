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
GET http://localhost:9000/api/v1/categories - retrieve all categories including sub-categories  
(sub-categories have not null parentcategoryid)  
GET http://localhost:9000/api/v1/categories/?parentcategoryid=null - retrieve all top-level categories  
GET http://localhost:9000/api/v1/categories/?isgroup=true  
GET http://localhost:9000/api/v1/categories/?isactine=true  
GET http://localhost:9000/api/v1/categories/?name=string  
GET http://localhost:9000/api/v1/categories/?code=string  
GET http://localhost:9000/api/v1/categories/?isgroup=true&isactive=true&name=Photography   
GET http://localhost:9000/api/v1/categories/{id} - retrieve category with id={id}  
POST http://localhost:9000/api/v1/categories - add a new category  
PUT http://localhost:9000/api/v1/categories/{id} - update category with id={id}  
DELETE http://localhost:9000/api/v1/categories/{id} - delete category with id={id}  

##### PRODUCTS  
GET http://localhost:9000/api/v1/products  
POST http://localhost:9000/api/v1/products  
GET http://localhost:9000/api/v1/products/?categoryid=6  
GET http://localhost:9000/api/v1/products/?brandid=1  
GET http://localhost:9000/api/v1/products/?vendorid=1  
GET http://localhost:9000/api/v1/products/?isoffer=true  
GET http://localhost:9000/api/v1/products/?isrecommended=true  
GET http://localhost:9000/api/v1/products/?isactive=true  
GET http://localhost:9000/api/v1/products/{id}  
PUT http://localhost:9000/api/v1/products/{id}  
PUT http://localhost:9000/api/v1/products/{id}/inactivate  
DELETE http://localhost:9000/api/v1/products/{id}  

##### BRANDS  
GET http://localhost:9000/api/v1/brands/  
POST http://localhost:9000/api/v1/brands/  
GET http://localhost:9000/api/v1/brands/{id}  
GET http://localhost:9000/api/v1/brands/{id}/products  
GET http://localhost:9000/api/v1/brands/{id}/?name=Some_string&isactive=true  
PUT http://localhost:9000/api/v1/brands/{id}  
PUT http://localhost:9000/api/v1/brands/{id}/inactivate  
DELETE http://localhost:9000/api/v1/brands/{id}  

##### VENDORS    
GET http://localhost:9000/api/v1/vendors/  
POST http://localhost:9000/api/v1/vendors/  
GET http://localhost:9000/api/v1/vendors/{id}  
GET http://localhost:9000/api/v1/vendors/{id}/products  
GET http://localhost:9000/api/v1/vendors/{id}/?name=Some_string&isactive=true    
PUT http://localhost:9000/api/v1/vendors/{id}  
PUT http://localhost:9000/api/v1/vendors/{id}/inactivate  
DELETE http://localhost:9000/api/v1/vendors/{id}  

##### PROPERTIES  
GET http://localhost:9000/api/v1/properties/  
POST http://localhost:9000/api/v1/properties/  
GET http://localhost:9000/api/v1/properties/{propertyId}  
GET http://localhost:9000/api/v1/properties/?name=Some_string  
PUT http://localhost:9000/api/v1/properties/{propertyId}  
DELETE http://localhost:9000/api/v1/properties/{propertyId}  

##### PROPERTY_VALUES  
GET http://localhost:9000/api/v1/properties/{propertyId}/propertyvalues/  
POST http://localhost:9000/api/v1/properties/{propertyId}/propertyvalues/  
PUT http://localhost:9000/api/v1/properties/{propertyId}/propertyvalues/{propertyValueId}  
DELETE http://localhost:9000/api/v1/properties/{propertyId}/propertyvalues/{propertyValueId}  

##### USERS  
http://localhost:9000/api/v1/users  

The backend part is deployed to heroku.  
So the backend endpoints above can be accessed using the following links:  
https://fs7-eshop.herokuapp.com/api/v1/categories  
https://fs7-eshop.herokuapp.com/api/v1/products  
https://fs7-eshop.herokuapp.com/api/v1/users  

To be continued soon ...  
