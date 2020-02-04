# Data-Centric-Project-2019
JSF Web Application with MySQL and MongoDB Databases

JSF dynamic web application that queries and updates a MySQL database and
a MongoDB database.

## Features
### Main Page
The Main page consists of 3 links:
• One to the Manage Stores page
• One to the Manage Products page
• One to the Manage Head offices page

Manage Stores Page
The Manage Stores page:
• Shows details of all Stores
• Has an Add Store button
• Has Show Products and Delete actions for each Store
• Has a link back to the Home page.

The Add Store Page allows the user to enter a Store Name and Founded date.
If an existing Store Name is entered an error should be displayed as shown.

If Store Name or Founded are not specified appropriate error messages are shown:
When a Store has been added successfully, the user is returned to the Manage Stores
Page.

Show Products Details Page
When the Show Products action is pressed on the Manage Stores page, the user is
brought to the Store/Product Details page.
This page shows details of all Stores and the Products in those stores as shown.

If there are no Products in a particular Store, nothing should be show:

Delete Store
When the Delete action is pressed on the Manage Stores page, if the particular Store
to be deleted has no Products, it is deleted from the database and the user remains on
the Manage Stores page which is updated to show the new list of stores.
If the particular Store to be deleted has Products, an error message is shown and the
Store is not deleted from the database.

Manage Products Page
The Manage Products page:
• Shows details of all Products
• Has a Delete action for each Product
• Has a link back to the Home page

Delete Product
When the Delete action is pressed on the Manage Products page, the product is
deleted from the database and the user remains on the Manage Products page which is
updated to show the new list of products.

Manage Head Offices
The Manage Head Offices page:
• Shows the Store ID and Location of the Store’s head office – from the
storeHeadOffice collection in the storeHeadOfficeDB database in MongoDB
• Has an Add Head Office button
• Has a link back to the Home page.

Add Head Office Page
The Add Head Office Page allows the user to enter a Store ID and Location.
If Store ID or Location are not specified appropriate error messages are shown.

If a non-existent Store ID is entered, i.e. one that does not exist in the MySQL
database, an appropriate error message is shown:

