# Frontend GUI for App
The frontend GUI of this application provides a user-friendly interface for interacting with the app's functionalities. 
Users can create a new person, update their information, and delete them. Additionally, users can create new orders by selecting a person and adding items to the order.
## GUI Functionality
The frontend GUI offers the following features:

### 1.Create a New Person: 
Users can create a new person by filling out a form with relevant information. 
The GUI provides input fields for users to enter the required information, and upon submission, sends a request to the backend API to create the person.

### 2.Update Person's Information: 
Users can update a person's information by selecting the person from a list and modifying their details.

### 3.Delete a Person:
Users can delete a person by selecting the person from a list and confirming the deletion. 
The GUI presents a list of existing people, and when a user selects a person and confirms the deletion, it sends a request to the backend API to delete the corresponding person record.

### 4.Create a New Order: 
To create a new order, users need to choose a person from a list to associate the order with. After selecting the person, they can click the "Create Order" button. 
This action redirects the user to a new page specifically designed for order creation.

### 5.Add Items to Order: 
On the order creation page, users can select the items they want to include in the order. 
The GUI provides an intuitive interface where users can choose from a list of available items, specify quantities, and add them to the order. 
Once the user has finished selecting items, can submit the order.

### 6.View Order Details: 
After submitting the order, the GUI displays a notification to the user with the order details. 
This includes information such as the selected items and quantities. 
The user can review the details before proceeding.

### 7.Delete an Order: 
Within the order details view, the GUI provides the user with an option to delete the order if desired. 
If the user chooses to delete the order, they can click a "Delete Order" button. 
This action sends a request to the backend API to delete the corresponding order record, along with all the items associated with that specific order.
