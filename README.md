# Gomoku in Java

A local two-player, five-in-a-row game on a 19 × 19 board. The project evolved from a terminal board printed with symbols into a mouse-driven Swing application.

## Run locally

With JDK 17 or later, compile both versions from the repository root:

```sh
javac -encoding UTF-8 -d out GoBangV1.java GoBangGUI.java
```

| Mode | Launch | Controls |
| --- | --- | --- |
| Swing | `java -cp out GoBangGUI` | Click an empty intersection. The status bar shows whose turn it is. |
| Terminal | `java -cp out GoBangV1` | Enter a zero-based row and column, such as `9 9`. Type `q` to quit. |

Black moves first. Five or more consecutive stones horizontally, vertically, or diagonally win. A full board is a draw if neither player has won.

## Project notes

The console version uses a two-dimensional array for board state. The Swing version maps mouse coordinates to intersections and draws the grid and stones with Java2D. The portfolio revision improves console input handling and adds turn feedback to the Swing version.

Created as an AP Computer Science A team project. Ruoyi Wang was one of the main programmers. The `high-school-original` tag preserves the original terminal and Swing implementations, plus an early board demo.
