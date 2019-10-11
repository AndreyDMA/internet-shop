
# Internet Shop

# Table of Contents
* [Project description](#description)
* [Project structure](#structure)
* [For user, how to start?](#user-start)
* [For developer](#developer-start)
* [Authors](#authors)

# <a name="description"></a>Project description
Internet Shop is a simplified example of real internet shop.
<hr>
You can create your own internet shop with different goods, which you'll be able to add, delete and update.
Internet Shop has registration and login procedures to increase safety on personal data. 
It imitates purchasing of chosen goods by adding to bucket and creating an order.
<hr>

# <a name="structure"></a>Project Structure
* Java 11
* Maven 4.0.0
* MavenCheckstylePlugin 3.1.0
* SevNTU Checkstyle
* Log4j 1.2.17
* Tomcat 8.5.45
* MySQL 8.0.17 
* Hibernate 5.4.5
<hr>

# <a name="user-start"></a>For user. How to start?
* Add new user via Registration form or sign in a persistent user

* Make your shopping.
 
<hr>

# <a name="developer-start"></a>For developer
* Download project from GitHub repository.

* Open it in your IDE as Maven Project.

* Make sure you have installed Tomcat 8.5.45 and MySQL 8.0.17. 
Optionally you can use another Web Container or/and Data Base.

* Make sure your's pom.xml have same settings that project's pom.xml.
If you going to use some other components than described in Project Structure - 
you have to make according changes.

* Attach your Data Base scheme with project and make according changes in
 _**\src\main\java\mateacademy\internetshop\Factory.java**_  by changing existing 
 values with yours (name of scheme, name and password of Data Base user)
 
* Make same changes in **_\src\main\resources\hibernate.cfg.xml_** file.
 
* Use _**src\main\resources\init_db.sql**_ file to generate necessary Data Base. 
By default you have two users: with role USER (login: first, password: 1) 
and role ADMIN (login: admin, password: 111).

* Change path for your .log file in **_\src\main\resources\log4j.properties_**.
<hr>

# <a name="authors"></a>Authors
* https://github.com/AndreyDMA
