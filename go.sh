#!/bin/bash

# Change directory to the backend folder
cd /home/user/TechPro_Final_Project/backend

# Run Maven clean and install
mvn clean install

# Move back to the parent directory
cd ..

# Run Docker Compose with sudo to ensure proper permissions
sudo docker-compose up --build -d
