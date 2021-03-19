Architecture
============

Iteration 2 Diagram
-------------------
![architecture](Architecture_i2.png)

Iteration 1 Diagram
-------------------
![architecture](Architecture_i1.png)

Presentation Layer
------------------
[MainActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/presentation/MainActivity.java)
 - An entrance page that allows users to sign up or log in to go to the home screen

[LogInActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/presentation/LogInActivity.java)
 - A screen that allows you to log in by entering a user name

[SignUpActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/presentation/SignUpActivity.java)
 - A screen that asks for necessary information to register a user

[HomeActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/presentation/HomeActivity.java)
 - A home screen where user can check calorie intake and calorie consumption during the day

[SearchFoodActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/presentation/SearchFoodActivity.java)
 - A screen where to search for food to get information

[FoodLogActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/presentation/FoodLogActivity.java)
 - A place where the types and amounts of foods eaten during the day are listed

[SearchExerciseActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/presentation/SearchExerciseActivity.java)
 -  A screen where to search for exercise to get information

[ExerciseLogActivity](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/presentation/ExerciseLogActivity.java)
 - A place where the types and amounts of exercises done during the day are listed


Application Layer
-----------------
[Main](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/application/Main.java)
 - The class that sets up database

[Services](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/application/Services.java)
 - The class that provides persistence objects

Logic Layer
--------------
[AccessUsers](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/AccessUsers.java)
 - The class the representation layer calls to do users-related things

[AccessFood](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/AccessFood.java)
 - The class the representation layer calls to do foods-related things

[AccessExercises](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/AccessExercises.java)
 - The class the representation layer calls to do exercises-related things

[AccessFoodLogs](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/AccessFoodLogs.java)
 - The class the representation layer calls to do food logs-related things

[AccessExerciseLogs](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/AccessExerciseLogs.java)
 - The class the representation layer calls to do exercise logs-related things

[DailyCaloricNeeds](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/DailyCaloricNeeds.java)
 - The helper class to calculate daily calorie needs based on a user information
 
[DateHelper](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/DateHelper.java)
 - The helper class that converts LocalDate objects into strings or strings representing dates into LocalDate objects.

[UnitConverter](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/business/UnitConverter.java)
 - The helper class responsible for unit conversion of weight(lbs, kg) and height(cm, ft). 
 

### Exceptions

#### User-related 
[InvalidUserException]()
 - The exception class for invalid User object (null)
 
[InvalidUserInfoException]()
 - The exception class 
 
[InvalidUserNameException]()
 - The exception class for checking that the user name is empty or too long
 
[InvalidSignUpDateException]()
 - The exception class for invalid date
 
[UserNotFoundException]()
 - The exception class for when a User object requested was not found

#### Food-related 
[InvalidFoodException]()
 - The exception class for invalid Food object (null)
 
[InvalidFdNameException]()
 - The exception class for checking that the food name is too long
 
[FoodNotFoundException]()
 - The exception class for when a Food object requested was not found

#### Exercise-related 
[InvalidExerciseException]()
 - The exception class for invalid Food object (null)
 
[InvalidExNameException]()
 - The exception class for checking that the title of the exercise is too long
 
[InvalidExDescException]()
 - The exception class for checking that the description of the exercise is too long
 
[InvalidExCategoryException]()
 - The exception class for checking that the category name of the exercise is too long
 
[InvalidExLevelException]()
 - The exception class for checking that the level of the exercise is too long
 
[ExerciseNotFoundException]()
 - The exception class for when an Exercise object requested was not found

#### FoodLog-related 
[InvalidFoodLogException]()
 - The exception class for invalid FoodLog object (null)
 
[FoodLogNotFoundException]()
 - The exception class for when an FoodLog object requested was not found

#### ExerciseLog-related 
[InvalidExerciseLogException]()
 - The exception class for invalid ExerciseLog object (null)
 
[ExerciseLogNotFoundException]()
 - The exception class for when an ExerciseLog object requested was not found
 
#### Other
[InvalidUnitsException]()
 - The exception class for
 
 
Persistence Layer
-----------------
[IUserPersistence](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/IUserPersistence.java)
 - The interface for handling the user in the database

[IFoodPersistence](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/IFoodPersistence.java)
 - The interface for handling the set of foods in the database

[IExercisePersistence](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/IExercisePersistence.java)
 - The interface for handling the set of exercises in the database

[IFoodLogPersistence](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/IFoodLogPersistence.java)
 - The interface for handling the food logs in the database

[IExerciseLogPersistence](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/main/java/com/group12/fitnics/persistence/IExerciseLogPersistence.java)
 - The interface for the exercise logs in the database

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
 
### hsqldb
[UserPersistenceHSQLDB]()
 - Current User database implementation

[FoodPersistenceHSQLDB]()
 - Current Food database implementation

[ExercisePersistenceHSQLDB]()
 - Current Exercise database implementation

[FoodLogPersistenceHSQLDB]()
 - Current FoodLog database implementation
 
[ExerciseLogPersistenceHSQLDB]()
 - Current ExecriceLog database implementation

   
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
