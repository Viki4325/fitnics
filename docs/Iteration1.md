Iteration 1 Worksheet
=====================

Adding a feature
----------------
In this iteration, a feature called [Daily Progress in Calories](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/2) was added.
- The domain specific objects and the overall architecture were first designed.
- This feature consists of two user stories.
 - [Viewing progress](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/16) was implemented to show the total amount of calories that the user consumed, the total amount of calories burned through exercise that day, and how many calories left to reach the recommended daily amount.
 - The total calories intake and total calories burned of the day can be calculated and displayed accordingly. ([Counting the calories consumed by food](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/19))
- Related tests (except for domain specific objects) are [AccessUsersTest](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/test/java/com/group12/fitnics/tests/business/AccessUsersTest.java), [AccessFoodLogsTest](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/test/java/com/group12/fitnics/tests/business/AccessFoodLogsTest.java), and [AccessExerciseLogsTest](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/blob/master/app/src/test/java/com/group12/fitnics/tests/business/AccessExerciseLogsTest.java).
- [Merge request](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/merge_requests/3)
- [Merge commit that was used complete the feature](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/commit/ae4fbced1113ee200e85150172fa97547097505d)


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
- We changed the description of the [Daily Progress](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/2) feature to be more specific and clear.
- We found that [updating user information](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/14) actually had a relatively low priority compared to other user stories for Iteration 1, so it was pushed to the next iteration.
- There were several design opinions on how to calculate [daily caloric needs](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/15), and we don't reach an agreement yet, so this user story was also pushed to the next iteration.
- The [Food Logger](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/18) and [Exercise Logger](https://code.cs.umanitoba.ca/3350-winter-2021-a01/fitnics-group-12/-/issues/22) have not implemented, since we found that each of them was actually too lengthy as a single user story. These loggers will allow users to browse a list of food they ate or exercise they did for the day, and allow new items to be added to the lists. These user stories are expected to be completed in the next iteration.
