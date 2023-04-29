# MyKVocs - A Korean Learning Web-App

This application helps Korean learners to extend their Korean vocabulary knowledge.
The plan is to provide the user with the feature to create their vocabulary books.
Also, there will be already set up vocabulary books filled with content from the (in my opinion very good) webpage https://www.howtostudykorean.com/. 
To practice the words, the user can use a 'practice' feature targeting one book. 
This feature hides one side of each word and the user has to fill it in. 
The result (how many correct/incorrect words) are displayed in the book's description so the user knows in which of his books he has deficits.

# Information

This project exists mainly to train my Spring/Java abilities. Therefore, mostly everything happens on the server-side. 
Thymeleaf is used to display the data. For styles Bootstrap 4 is mostly used.


### Requirements

- IDE of choice (I use IntelliJ)
- Java 17

### Install

- Clone (git clone) this repository
- Import the content of the directory 'MyKVocs' as a maven project inside your IDE

### Run

Start the application by running the main method of the class 'MyKVocsApplication'

# Update -2023-

This private Project was not further developed since the start of my full-time job as a Software Developer on 1st July 2021. 
Nearly 2 years have passed since then and I have learned a lot, not only about Java and other technologies, but also about conceptual topics and code quality. 
There are many improvements in the code that I would love to address. But I decided to take another route. 
Instead, I am planning to start a new repository with a new version of the software.
 
Some changes that are planned for the new version:

- Move from Server-Side Application with Thymeleaf to Single Page Application with Vue.js
- Introduce a microservices architecture with the help of technologies like Docker and Kubernetes
-- REST Service for the backend and put it into a container
-- Put Vue.js application into a container that consumes the REST Endpoints of the backend container
-- Put a Database (Until now I only used PostreSQL, therefore I will use something else like MySQL for learning purposes) into a container
- New features like creating own VocBooks

Some decisions maybe make no sense, like using a microservises architecture for a small Application like this.
But, it is important to understand that this project exists mainly for learning purposes. 
