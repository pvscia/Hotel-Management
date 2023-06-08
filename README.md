# Simple Hotel Management System

## A simple application that helps keep track of all your hotel needs, both from a guest perspective and an admin perspective.

This project is built using Java and JavaFX while also integrating a MySQL database, and while remaining simple to navigate, will allow users (both guests and admins) to do the following tasks respectively:

Guests:

* Add ratings to the currently checked-in hotel.
* Book hotel facilities remotely.
* Order additional amenities, such as additional toothbrushes .
* Set a wake-up call time.
* Set room status to do not disturb.

Hotel Admins:

* View room details, such as room capacity, its status (booked or not), etc.
* View reservation details that include the ID of the guest currently checked-in, when they checked-in, and what room they're currently staying at.
* Update and keep track of amenity stocks.
* Update and keep track of the status of hotel facilities, such as the gym or spa.
* View user ratings thus far.

## How to install and use this project
    
1. Clone this project
2. Create a MySQL database named hotel_management using the XAMPP Control Panel following the format of the following database:
   Tables: <br />
   <img width="739" alt="image" src="https://github.com/pvscia/Hotel-Management/assets/126550095/3044f97d-69af-40da-8670-48a2976fd230">
   
   With each table containing the following columns:
   
   Booking: <br />
   <img width="185" alt="image" src="https://github.com/pvscia/Hotel-Management/assets/126550095/ed7b9184-4575-462a-a510-e372fd383a1c">
   
   Facility: <br />
   <img width="467" alt="image" src="https://github.com/pvscia/Hotel-Management/assets/126550095/11f62b40-930b-47bc-9a91-35660b8c0777">
   
   Facility Book: <br />
   <img width="194" alt="image" src="https://github.com/pvscia/Hotel-Management/assets/126550095/62d3c525-7c45-4acc-a2af-765656b2c400">
   
   Inventory: <br />
   <img width="263" alt="image" src="https://github.com/pvscia/Hotel-Management/assets/126550095/09dc58c2-2556-47ef-89b9-3b3f7ba65e88">
   
   Rating: <br />
   <img width="245" alt="image" src="https://github.com/pvscia/Hotel-Management/assets/126550095/c2aece9d-a4bb-4932-848b-3faddc1c90ce">

   Room: <br />
   <img width="850" alt="image" src="https://github.com/pvscia/Hotel-Management/assets/126550095/fc7e7f3e-4194-49ba-beae-2374acd392d2">
   
   User: <br />
   <img width="557" alt="image" src="https://github.com/pvscia/Hotel-Management/assets/126550095/2547bd0e-e074-4ec7-9510-56c497c6a669">
   
   The information shown are examples, feel free to edit the data to your liking.
   
3. Once the dataset is properly set up, import the JavaFX library, JFXtras library and the MySQL Connector for JavaFX into the project build path in your prefered IDE.
4. Run the Main.Java file and enjoy!


