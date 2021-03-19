Iteration 2 Worksheet
======================

Paying off Technical Dept
-------------------------
- Deleting MyDate
  - A domain specific object called 'MyDate' has been deleted and replaced by using a java API 'LocalDate', with a help of 'DateHelper' in logic layer.  
  - This dept can be classified into Reckless & Inadvertent type. Despite the existence of an well-known and easy-to-use API, the class was quickly created without researching what were available to us. This unnecessarily increased the complexity of the system, and was not easy to use.
  - Almost every changes made in [this commit](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/commit/159cf9d56c92001e69392d07a297f547c0f72152#34b2e794882555eb575bb7ec71e4e2af5cc0a0ef_0_2) is paying technical debt.
- Not Throwing Exceptions
  - Quite a few methods in the database layer were returning a boolean or String value to indicate whether the execution of the method was successful or not.
  - We had to pay the debt by making a whole exceptions class and throwing the respective exceptions in the required places. And in turn, we had to refactor our code sections with quite a lot of try and catch blocks. 
  - This dept can be classified into Reckless & Deliberate type, since it was a choice we made earlier because of lack of time.
  - Almost every changes made in [This commit](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/commit/3b0b6d8e26c5608d6a7c566f96599634aba2c22c) and [this commit](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/commit/e753b15b8a2f5097a42b5e2d67e0de0064da3a42) are paying technical debt.
  

SOLID
-----
SOLID violation in the project of A02 Group 12: [Link](https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-12/good-habits-a02-12/-/issues/39)


Retrospective
-------------
- In the last iteration, we found that we underestimated the time required to complete the user story. At the end of iteration 1, we had to transfer several important features and user stories to the next iteration. So in this iteration, we slightly increased the estimated time required to complete each user story. [Food logger (#25) · Issues](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/25)
- We also realized that we need to do our best to write neat code. In this way, we can understand more clearly how to increase the readability and maintainability of the code and collaborate in a team setting. *The relevant evidence is at the bottom of gather's shared document*.
- In the last few days of Iteration 1, because of the lack of communication and cooperation, we have to fix a lot of conflicts in the last few days. In order to solve this problem, we will communicate more frequently, and decide to push to the repository immediately after each feature or user story is completed to test and fix the conflicts with other team members.[Commits · master](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/commits/master)

Design Patterns
---------------
- Singleton Pattern
  - In Services class, only one instance is created for each type of persistence across the entire system, and the same instance is provided to all clients.
  - [Link](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/application/Services.java)


Iteration 1 Feedback Fixes
--------------------------
- [Link](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/30) Buttons available does not have any action
  - Buttons that are clickable but don't perform their intended function do not meet user expectations and have no reason to be there. 
  - These buttons were originally supposed to perform features implemented in Iteration 1, but the features were pushed to Iteration 2, so it shouldn't have existed originally. 
  - Those features are now implemented in [this commit](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/commit/a6a149d0c6878ea7d8de3c5ad4e4c3663fcdf07c).
- [Link](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/29) Signup page does not show the height properly
  - The issue was that the UI was working incorrectly, the UI needs to show values properly. 
  - Set limits for the values and completely redesigned the layout of the Page so that the value was stored as a float instead of a double.
  - [Fix](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/commit/417afb867c3013718cc1c52f7b710b84c08f6ed8)
- [Link](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/28) Use an exception, not booleans
  and [Link](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/27) Use an exception, not strings
  - The code should assume it works, and throw an exception if it fails which eventually bubble up into the UI. Instead, a boolean type or String type was used as the return value, which can not take the role of exception.
  - We created customized exception classes to be thrown, removed unnecessary return values and made those problematic methods to throw specific exceptions.
  - [Fix](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/commit/3b0b6d8e26c5608d6a7c566f96599634aba2c22c)
  - [Fix](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/commit/e753b15b8a2f5097a42b5e2d67e0de0064da3a42)
- [Link](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/26) Logic/magic numbers in the UI
  - The issue was that we used constants, this makes it difficult to read and is a bad programing practice. 
  - We created a class and made functions to do the conversions instead of constants. 
  - [Fix](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/commit/cba899ff033062651c7488752891b1df7a1c5a1e).
