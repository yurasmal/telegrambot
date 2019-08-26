Simple Telegram bot for receiving information about cities

Project deployed at Heroku and available most of the time. You can test it by adding @CitiesGuideBot in Telegram.

Bot username - CitiesGuideBot;

Token - 644363276:AAEoo14lQkNJ1goND-tC14WpPXhNgmLl4qA;

Access service using next REST requests:

GET: https://guidetelegrambot.herokuapp.com/api/v1/cities/1 - get city with ID 1

GET: https://guidetelegrambot.herokuapp.com/api/v1/cities/ - get all cities

POST: https://guidetelegrambot.herokuapp.com/api/v1/cities 

      Request body: { "cityName": "City Name", "message": "some message" } - save city
      
PUT: https://guidetelegrambot.herokuapp.com/api/v1/cities

      Request body: { "id": 3, "cityName": "City Name", "message": "new message" } - update city with ID 3    
      
DELETE: https://guidetelegrambot.herokuapp.com/api/v1/cities/1 - delete city with ID 1      
