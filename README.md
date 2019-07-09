[![license](https://img.shields.io/github/license/mashape/apistatus.svg)]()
[![Packagist Pre Release](https://img.shields.io/packagist/vpre/symfony/symfony.svg)]()

RestService demo

required
Mysql
eclipse
jdk 1.7
tomcat 7
helpful
postman

    Running & setup

clone repository 
import it in eclipse
import sql file in sqlyog or other tools located at the root srd.sql
change username and password in WEB-INF/applicationContext.xml
run the project on tomcat 
use postman or other tools for the app


To run unit test localy use junit or maven plugin 
on build push travis ci automaticly tests the repo and depolys it on heruko
in case failure travis sends an email.
	
	Work Flow And Apis
/
instructions 
when project is started in eclipse a pop up will appear which states download json object, you can cancel it. 
it contains a list of api endpoints of the system in json format 
note: when using browser or postman their will be no popup it will display json properly
	
/api/import/{username}

Imports Users From Github. 
Saves Or Updates Data In Mysql From Users,repositories And Languages Of Repositories

/api/show/users

fetches All Users From User Table

/api/show/{id}/repos

fetches Repositories Based On User Id

/api/show/{id}/lang

fetches Languages Of Repository Which Belonged To A Particular User show Languages Used And Percentage Of Contribution In The Particular Repository

/api/show/{username}

shows Languages Used And Percentage Of Contribution In All The Repositories
