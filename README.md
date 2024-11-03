1. Why did I choose this project theme? What inspired me?

The theme of the project is closely related to my passion for football and sports in general, as well as my interest in the evolution of society’s relationship with sports betting. With sports betting gaining popularity,
 I was inspired to create an application that would both facilitate understanding of betting mechanics and provide a responsible approach to tracking matches and bets. This allowed me to merge my interests in sports and software development, creating a project that I was both passionate about and eager to learn from.


2. What were the objectives of the project? What did I want to achieve? What should my application do?

The primary objectives of the project were to:

Develop a functional betting management system that allows users to add, update, and view football matches and bets.
Enable a responsible and organized approach to managing betting information, providing users with options to view bets by sport and adjust odds in real time.
Offer intuitive functionality for users to perform common actions like adding matches, deleting bets, and updating odds, which could be useful for both sports enthusiasts and people interested in sports betting.

The application is intended to serve as a backend for a future sports betting management platform, providing API endpoints for creating and managing matches and bets, which could later be expanded into a fully functional frontend with HTML and CSS.



3. How did I choose the respective technologies? Why did I choose them? What is the purpose of each technology used?

Java & Spring Boot: I chose Java due to its robustness and the vast ecosystem for developing backend applications. Spring Boot, in particular, was ideal for quickly setting up a RESTful API, which allowed me to create and expose endpoints efficiently for match and bet management.
REST API principles: REST was chosen to design the application in a way that makes it easily extensible and accessible for future frontend development.
HTML & CSS (planned): Although the current focus is on the backend, I chose HTML and CSS for future frontend development to create an intuitive, user-friendly interface for the end-users. This will make the platform visually appealing and easy to navigate.

These technologies combined create a reliable, scalable backend with the potential for a polished user interface.



4. How did the development process go? Where did I get my resources from/where did I do my research/who or what inspired me?

The development process involved planning the structure of the application, especially focusing on making it a singleton controller to manage both matches and bets, ensuring consistency and thread safety. 
I researched best practices for REST API design using Spring Boot through online resources, tutorials, and documentation from the official Spring website. I was inspired by existing sports applications and platforms that organize sports data and betting systems. 
Additionally, various developer communities and forums helped answer specific technical questions and provided insights into efficient Spring Boot practices.



5. What difficulties did I encounter during the development process? How did I overcome them? What did I learn from that experience?

Singleton Implementation: Ensuring the singleton pattern was correctly implemented and thread-safe was challenging. I had to ensure the getInstance method was synchronized to avoid any issues with concurrent access. This experience taught me more about the importance of design patterns and thread safety in Java.
Updating and Adding Unique Odds: Preventing duplicate odds required careful validation, which initially led to some challenges in handling list searches and updates. I solved this by implementing a check using Java streams, which allowed me to confirm that an odd was unique before adding it.
Error Handling: Managing error responses in a user-friendly way, especially when dealing with non-existent matches or bets, required handling Optional values effectively. This taught me more about clean and effective error handling in REST APIs.

Through these challenges, I gained deeper insights into effective API design, ensuring data integrity, and improving the user experience.



6. Did I manage to complete the proposed project? If yes, what achievements were obtained?

Yes, I managed to complete the core functionality of the project. The major achievements include:

Implementation of a functional REST API that allows users to manage football matches and associated bets, including unique features like odds updating and validation.
Successful integration of CRUD operations for both matches and bets, enabling future extensibility.
A well-organized backend structure that will serve as a stable foundation for a future frontend development phase with HTML and CSS.

This project has not only fulfilled its technical goals but has also deepened my understanding of backend API design and the Java ecosystem, setting a strong base for further development and enhancements.
