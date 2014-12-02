TheBlackJack
============

Technologies:

 = Spring MVC
 = Spring Data JPA
 = Hibernate
 = PostgreSQL
 = Jetty
 
 Testing:
  = Spring Test
  = JUnit
  = H2 in memory database
 
To start the application you need:

PostgreSQL database 'blackjack' with schema 'blackjack'. Owner of database must be 'postgres', password: 'root'. For creating database you can use script 'createDatabase.sql'
and 'createSchema.sql', which placed in src/main/resources/sql. 

After that you can just run main class 'com.andrew.safronov.sintez.theblackjack.webserver.Main' , and jetty startup application at localhost:8084.
Database tables would be created automatically.




