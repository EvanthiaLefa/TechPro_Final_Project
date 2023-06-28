# TechPro_Final_Project

## Final_Project_TechPro
Is a full stack project that consists of three services a database, the back-end and the font-end part.
In this application you can create persons as users and edit them. Also can choose items from a list and add orders per user.

The project is designed to run on a virtual machine as three different microservices (of course it can also run locally).
Prerequisites:
you must have a virtual machine with the docker daemon installed the maven build tool, jvm ,nodejs and npm.
## install-and-run-project
1. git clone url-repository
2. go to the .env file and change the variables with the right values such the ip of you machine and the rigght passwords
3. cd frontend/
4. npm install (to install the node_modules and use required dependencies)
5. cd .. (to the root directory)
6. cd backend/
7. mvn clean install (to build the backend)
8. cd .. (to the root directory)
9. sudo docker-compose up --build -d
Also instead of doing the steps 6-9 you can just run the script using the command bash go.sh  
     The microservices are up and running 
 Open the fontend in browser http://ip:3000/

## perfomance-of-controllers


|People Controller        |Item Controller        |Order Controller       |Order_Details Controller     |
|-------------------------|:---------------------:|----------------------:|-----------------------------|
|findAll() Avg:31ms       |findAll() Avg:30.6ms   |findAll() Avg:31.9ms   |findAll() Avg:31.2ms         |                                                                   
|createPeople() Avg:30.2ms|createItem() Avg:30ms  |create_order() Avg:29ms|createOrderDetails Avg:32.4ms|
|findById() Avg:29 ms     |findById() Avg:29.6ms  |deleteById() Avg:28.2ms|  -------------------------  |
|updatePeople() Avg:28.2ms|updateItem() Avg:29ms  |  ------------------   |  -------------------------  |
|deleteById() Avg:28ms    |deleteById() Avg:28.8ms|  ------------------   |  -------------------------  |
