Architecture
============

Iteration 1 Diagram
-------------------
![architecture](Architecture_i1.png)

Presentation Layer
------------------
[HomeActivity](link)

[LogInActivity](link)

[SignUpActivity](link)

[MainActivity](link)

[HomeActivity](link)

[SearchFoodActivity](link)

[FoodLogActivity](link)

[SearchExerciseActivity](link)

[ExerciseLogActivity](link)


Application Layer
-----------------
[Services](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/application/Services.java)

[Main](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/application/Main.java)


Business Layer
--------------
[AccessUsers](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/AccessUsers.java)
 - The class the representation layer calls to do users-related things

[AccessFoods](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/AccessFoods.java)
 - The class the representation layer calls to do foods-related things

[AccessExercises](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/AccessExercises.java)
 - The class the representation layer calls to do exercises-related things

[AccessFoodLogs](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/AccessFoodLogs.java)
 - The class the representation layer calls to do food logs-related things

[AccessExerciseLogs](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/AccessExerciseLogs.java)
 - The class the representation layer calls to do exercise logs-related things

Persistence Layer
-----------------
[UserPersistence](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/UserPersistence.java)
 - The interface for the user in the database

[FoodPersistence](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/FoodPersistence.java)
 - The interface for the set of foods in the database

[ExercisePersistence](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/ExercisePersistence.java)
 - The interface for the set of exercises in the database

[FoodLogPersistence](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/FoodLogPersistence.java)
 - The interface for the food log in the database

[ExerciseLogPersistence](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/ExerciseLogPersistence.java)
 - The interface for the exercise log in the database

### Stubs
[UserPersistenceStub](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/UserPersistenceStub.java)
 - Current User implementation for the "database" for the app

[FoodPersistenceStub](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/FoodPersistenceStub.java)
 - Current Food implementation for the "database" for the app

[ExercisePersistenceStub](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/ExercisePersistenceStub.java)
 - Current Exercise implementation for the "database" for the app

[FoodLogPersistenceStub](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/FoodLogPersistenceStub.java)
 - Current FoodLog implementation for the "database" for the app

[ExerciseLogPersistenceStub](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/ExerciseLogPersistenceStub.java)
 - Current ExerciseLog implementation for the "database" for the app

Domain Specific Objects
-----------------------
[User](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/objects/User.java)
 - The user object

[Food](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/objects/Food.java)
 - The object representing one kind of food

[Exercise](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/objects/Exercise.java)
 - The object representing one kind of exercise

[FoodLog](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/objects/FoodLog.java)
 - The object indicating which user ate what food and how much on what date

[ExerciseLog](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/objects/ExerciseLog.java)
 - The object indicating which user did what exercise and how many minutes on what date

[MyDate](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/objects/MyDate.java)
 - The object for representing dates in FoodLog and ExerciseLog
