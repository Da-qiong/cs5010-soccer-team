# Soccer Team Management System

A simple yet extensible Java-based soccer team management system that simulates building a team, assigning jersey numbers, managing starting lineups, and player substitutions.

##  Features

- Define and manage soccer players with position, skill, and personal info
- Enforce team size constraints (min 10, max 20)
- Automatically remove weakest players if the team exceeds size
- Assign unique, random jersey numbers
- Sort and manage starting lineups based on position and player name
- Support player substitutions between active and bench players
- JUnit test cases included for robustness

##  Project Structure

```
│   .gitignore
│   README.md
│   uml.mmd
│
├───res
│       cs5010-soccer-team.jar
│
├───src
│   └───model
│           ActiveTeam.java
│           Main.java
│           Person.java
│           Player.java
│           Position.java
│           Team.java
│
└───test
    └───model
            ActiveTeamTest.java
```

##  How to Run

### Run from `.jar`

You can also run the compiled jar file directly:

```bash
java -cp ./res/cs5010-soccer-team.jar model.Main
```

---

##  Testing

JUnit tests are included and cover major functionalities:

- Team construction and size validation
- Jersey number assignment
- Starting lineup logic
- Substitution logic
- Player object behavior

You can run tests using any IDE that supports JUnit.

---

##  Sample Output

```
=== Running Demo ===
All Players:
Player0 LastName0 (Jersey: 3, Skill: 4)
...
Starting Lineup:
Player0 LastName0 (Jersey: 3, Skill: 4)
...
After Substitution:
Player7 LastName7 (Jersey: 12, Skill: 3)
...
```

---

##  Author

> *Lingsong Qin*  
> *Yibo Wang*  
> *Zuobo Yang*  