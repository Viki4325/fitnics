Iteration 1 Worksheet
=====================

Adding a feature
----------------
In this iteration:
A feature called [Daily Progress in Calories](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/2) was added.
- The domain specific objects and the overall architecture were first designed.
- This feature consists of two user stories.
  - [Viewing progress](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/16) was implemented to show the total amount of calories that the user consumed, the total amount of calories burned through exercise that day, and how many calories left to reach the recommended daily amount.
  - The total calories intake and total calories burned of the day can be calculated and displayed accordingly. ([Counting the calories consumed by food](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/19))
- Related tests (except for domain specific objects) are [AccessUsersTest](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/test/java/com/group12/fitnics/tests/business/AccessUsersTest.java), [AccessFoodLogsTest](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/test/java/com/group12/fitnics/tests/business/AccessFoodLogsTest.java), and [AccessExerciseLogsTest](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/test/java/com/group12/fitnics/tests/business/AccessExerciseLogsTest.java).
- [Merge request](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/merge_requests/3)
- [Merge commit that was used complete the feature](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/commit/ae4fbced1113ee200e85150172fa97547097505d)

A feature called Exercise finder and Logger was partially done.
- The domain specific objects related to this feature was completed first.
- This feature consists of two user stories.
    - [Exercise finder](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/20) was implemented where users can view all the exercises available and even search for them.
    - [Exercise information](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/21) was implemented where users can now select a specific exercise, and view information about the exercise.
- Related tests are [ExerciseTest](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/test/java/com/group12/fitnics/tests/objects/ExerciseTest.java), [AccessExerciseTest](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/test/java/com/group12/fitnics/tests/business/AccessExercisesTest.java).
- This feature was split into two, as we realized it was too lenghty to be implemented as one feature. Namely :
    - Exercise Finder, completed
    - Exercise Logger, moved to Iteration 2

A feature called Food finder and Logger was partially done.
- The domain specific objects related to this feature was completed first.
- This features consist of two user stories.
    -[Food finder](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/17) was implemented where users can view a list of all foods available and even search for the them.
- Related tests are [FoodTest](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/test/java/com/group12/fitnics/tests/objects/FoodTest.java),[AccessFoodTest](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/test/java/com/group12/fitnics/tests/business/AccessFoodsTest.java).
- This feature was split into two, as we realized it was too lengthy to be implemented as one feature. Namely :
    - Food finder, completed
    - Food Logger, moved to Iteration 2

Exceptional code
----------------



Branching
---------
- We use [Github flow](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/docs/Branching%20Strategy.md) as the branching strategy.

- Screen shot of a feature [Daily Progress in Calories](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/2) being added
[screenshot](branching.png)

SOLID
-----
SOLID violation in the project of group 1: [Link](https://code.cs.umanitoba.ca/3350-winter-2021-a01/group-1/-/issues/43)


Agile Planning
--------------
We realized that we had under-estimated the time allocation for feratures and user stories. Some features and user stories were too lengthy to be done as one feature and therefore split into two seperate features, as described below. 
- We changed the description of the [Daily Progress](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/2) feature to be more specific and clear.
- We found that [updating user information](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/14) actually had a relatively low priority compared to other user stories for Iteration 1, so it was pushed to the next iteration.
- There were several design opinions on how to calculate [daily caloric needs](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/15), and we don't reach an agreement yet, so this user story was also pushed to the next iteration.
- The [Food Logger](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/18) and [Exercise Logger](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/22) have not implemented, since we found that each of them was actually too lengthy as a single user story. These loggers will allow users to browse a list of food they ate or exercise they did for the day, and allow new items to be added to the lists. These user stories are expected to be completed in the next iteration.
- One of the features was split into two, [Exercise finder and Logger](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/4) : [Exercise finder](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/4) which is cokmpleted in this iteration and [Exercise Logger](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/24) which is pushed to Iteration 2.
-  We decided we will make a class that makes custom exceptions and will use them effectively from Iteration 2, that is. throwing and handling of custom exceptions.
