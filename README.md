# OnlineVotingSystems
Project Proposal
Object Oriented Programming in JAVA
Title: Online Voting System
Date of Submission: 28 May, 2024

Submitted To
Dr. Md. Ezharul Islam
Professor
Department of CSE
Jahangirnagar University

Submitted By
Sabia Akter Trisha
Class Roll: 387
Anika Aktar 
Class Roll :385
Session: 2021-2022
Department of CSE
Jahangirnagar University
 
Jahangirnagar University
Savar, Dhaka-1342


Title
A simple Online Voting System implemented using Object-Oriented Programming principles in Java.

Features
(i)Encapsulation:
Classes and Fields: The User, Candidate, and VotingSystem classes encapsulate data and behavior. Fields like username, hasVoted, name, and voteCount are private and can be accessed or modified only through public methods.
Getters and Setters: Methods like getUsername(), hasVoted(), getVoteCount(), and addVote() provide controlled access to the class fields.
(ii)Inheritance:
Custom Exceptions: The custom exceptions InvalidUserException and InvalidVoteException inherit from the Exception class, demonstrating the use of inheritance to create specialized exception types.
(iii)Polymorphism:
Exception Handling: Polymorphism is illustrated through exception handling. The catch blocks handle both InvalidUserException and InvalidVoteException, which are treated as Exception objects, showcasing method overriding and polymorphism in action.
(iv)Abstraction:
User and Candidate Classes: These classes represent the concept of a user and a candidate abstractly, hiding the implementation details while exposing only necessary behaviors through public methods.
VotingSystem Class: This class abstracts the voting process, providing methods to add users and candidates, authenticate users, cast votes, and display results without exposing the underlying data structures directly.
(v) Interfaces:
Define behaviors that can be implemented by different classes.

Design
(1) User Class:
•	Represents a user with a username and a flag indicating if they have voted.

(2) Candidate Class:
•	Represents a candidate with a name and a count of votes they have received.

(3) Voting System Class:
•	Manages the users and candidates.
•	Provides methods to add users and candidates, authenticate users, cast votes, and display results.

(4) Invalid User Exception Class:
•	Custom exception for invalid users.

(5) Invalid Vote Exception Class: 
•	Custom exception for invalid votes.


(6) Main Class:

•	The entry point of the application.
•	Handles user interaction via the console.
•	Authenticates users, accepts votes, and displays results.
