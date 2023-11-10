# Devices and Services API

This project is a RESTful API for managing devices and services. With this API, you can add and delete devices and services, assign services to devices, and calculate the total cost of services used by a device.

## Starting the Application

Run the `BackendInterviewProjectApplication` class

## Endpoints
POST /devices: Add a new device. </br>
DELETE /devices/{id}: Delete a device. </br>
POST /services: Add a new service. </br>
DELETE /services/{id}: Delete a service. </br>
POST /devices/{id}/add-service-to-device/: Add a service to a device. </br>
DELETE /devices/{id}/services/{serviceId}: Delete a service from a device. </br>
GET /devices/{id}/totalCost: Get the total cost of services for a device. </br>

you can also use import postman collections:
resources/ninjaone.postman_collection.json

## Testing 
This project includes both unit tests and integration tests. 
To run the testsd:

```
execute task gradle test
```


## API Documentation
The API documentation is generated using OpenAPI 3.0 and can be accessed at http://localhost:8080/swagger-ui.html and http://localhost:8080/v3/api-docs.

## H2 Console 

In order to see and interact with your db, access the h2 console in your browser.
After running the application, go to:

http://localhost:8080/h2-console

Enter the information for the url, username, and password in the application.yml:

```yml
url: jdbc:h2:mem:localdb
username: sa 
password: password
```

You should be able to see a db console now that has 
the Device  and Service Repository in it.




