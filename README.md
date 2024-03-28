## About

This is a program that visualizes the Knight's Tour problem by using a combination of backtracking and a heuristic called Warnsdorf's Rule for the algorithm, and Java Swing for the GUI. 
If you're not familiar with the Knight's Tour problem, you can read about it on [Wikipedia](https://en.wikipedia.org/wiki/Knight%27s_tour) or watch an interesting video about it on [YouTube](https://www.youtube.com/watch?v=ab_dY3dZFHM).

## Approach

I intially tried implementing an algorithm based solely on a brute-force approach with backtracking, but the program would never finish, even after letting it run for a few hours. I then learned about Warnsdorf's heuristic, in which the knight moves to the square that has the fewest possible number of subsequent moves. That alone was not enough to solve the problem accurately from every starting square, although it was very fast. I ended up using a combination of both approaches, which actually turned out to be so efficient that it completed in less than a second and was hard to visualize. I've added in a 100 millisecond delay between iterations of the main loop in order to make the process easier to visualize. Feel free to change the delay yourself if you want; I used the Thread.sleep() method.

## Get Started

Start by downloading the files to your machine.
```bash
git clone https://github.com/aidan-palmer/knights-tour.git
```
Navigate into the knights-tour directory, then run the script to compile and start the program.
```bash
./build/compile-and-run.sh
```
The program will start up, now simply click on any square to start it.