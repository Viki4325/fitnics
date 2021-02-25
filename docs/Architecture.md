Architecture
============

Iteration 1 Diagram
-------------------
![architecture](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/tree/master/docs/Architecture_i1.png)

Presentation Layer
------------------
[HomeActivity](link)
-
[LogInActivity](link)
-
[SignUpActivity](link)
-
[MainActivity](link)
-
[HomeActivity](link)
-
[SearchFoodActivity](link)
-
[FoodLogActivity](link)
-
[SearchExerciseActivity](link)
-
[ExerciseLogActivity](link)
-

Application Layer
-----------------
[Services](link)
-
[Main](link)
-

Business Layer
--------------
[AccessUsers](link)
- The class the representation layer calls to do users-related things
[AccessFoods](link)
- The class the representation layer calls to do foods-related things
[AccessExercises](link)
- The class the representation layer calls to do exercises-related things
[AccessFoodLogs](link)
- The class the representation layer calls to do food logs-related things
[AccessExerciseLogs](link)
- The class the representation layer calls to do exercise logs-related things

Persistence Layer
-----------------
[UserPersistence](link)
- The interface for the user in the database
[FoodPersistence](link)
- The interface for the set of foods in the database
[ExercisePersistence](link)
- The interface for the set of exercises in the database
[FoodLogPersistence](link)
- The interface for the food log in the database
[ExerciseLogPersistence](link)
- The interface for the exercise log in the database

### Stubs
[UserPersistenceStub](link)
- Current User implementation for the "database" for the app
[FoodPersistenceStub](link)
- Current Food implementation for the "database" for the app
[ExercisePersistenceStub](link)
- Current Exercise implementation for the "database" for the app
[FoodLogPersistenceStub](link)
- Current FoodLog implementation for the "database" for the app
[ExerciseLogPersistenceStub](link)
- Current ExerciseLog implementation for the "database" for the app

Domain Specific Objects
-----------------------
[User](link)
- The user object
[Food](link)
- The object representing one kind of food
[Exercise](link)
- The object representing one kind of exercise
[FoodLog](link)
- The object indicating which user ate what food and how much on what date
[ExerciseLog](link)
- The object indicating which user did what exercise and how many minutes on what date
[MyDate](link)
- The object for representing dates in FoodLog and ExerciseLog
