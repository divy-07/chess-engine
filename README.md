# Hari: The Chess Engine

---

### Jump to:

1. [Contributors](#contributors)
2. [Description](#description)
3. [Using the Engine](#using-the-engine)
4. [Architecture](#architecture)
5. [Contributing](#contributing-to-the-project)
6. [Contact](#contact)

---

## Contributors

1. [Divy Patel](https://github.com/divy-07) (owner)
2. [Arnav Prasad](https://github.com/arnavpd) (owner)

Special mention: Jonathan Warkentin, some code was reused from his chess engine, Orion.

---

## Description

[//]: # "Edit this section when implementation is complete"

Note: Although the engine is currently under construction, a big chunk of the work is done.

Current rating: WIP

The motive behind this project was to create a chess engine that can beat us, the creators, rated around 1200.
We expect the engine to be able to beat us in the near future, once the engine is fully implemented.
Apart from our passion for chess, we also wanted to apply the knowledge we have gained in our Software Engineering courses to a real world application.
This includes the use of object-oriented programming, unit testing, concurrency, and other software engineering principles.

The project is created using Java, as it is a nice mix between C++(speed) and Python(readability).
It is also a language that we are both familiar with.

[Back to top](#hari--the-chess-engine)

---

## Using the Engine

Using the engine currently requires Java 14 and gradle 7.5 or higher.
Steps:
- Clone/download the repository to your local machine.
- Open a terminal in the root directory of the project
- Run `./gradlew clean build` to create the jar file
- Use any UCI-supporting GUI to play against the engine. We recommend [Arena](http://www.playwitharena.de/)
- Register the engine with GUI using the .jar file in the build/libs directory
- Play against the engine!

[Back to top](#hari--the-chess-engine)

---

## Architecture

[//]: # "Edit this section when architecture is changed"

```
             -------
             | GUI |
             -------
                |
             -------
             | UCI |
             -------
                |  
            --------
            | Hari |
            --------
                |
       ------------------
       | MoveGeneration |
       ------------------
             /     \
 --------------   -----------------
 | Evaluation |   | PossibleMoves |
 --------------   -----------------
```

The engine is composed of:
- The UCI interface (communicates with the GUI)
- Hari (the engine, holds the board and the game state)
- MoveGeneration (generates the best next move)
- Evaluation (evaluates the board position)
- PossibleMoves (generates all possible moves for a given board position)

[Back to top](#hari--the-chess-engine)

---

## Contributing to the Project

To contribute to the project, please follow the steps below:
- Browse the issues and self-assign an unassigned issue
  - Ask any questions you may have in the comments
  - Or alternatively, you can ask one of the creators via email/discord to clarify any doubts
- Open a pull request with the changes you have made
  - Make sure to include a description of the changes you have made
  - If you are fixing an issue, make sure to include the issue number in the description
  - The pull request will be reviewed by one of the creators
  - It must be approved by one of the creators and pass CI before it can be merged

Good luck and thank you for contributing to the project!
As a reward, you will be added to the contributors list.

## Contact

Any questions or concerns? Feel free to contact each of us at:

- Divy Patel: www.divy07.com/contact
- Arnav Prasad: [arnavpd@gmail.com](mailto:arnavpd@gmail.com)

[Back to top](#hari--the-chess-engine)
