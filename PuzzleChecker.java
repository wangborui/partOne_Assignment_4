/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partOne_Assignment_4;

/******************************************************************************
 *  Compilation:  javac PuzzleChecker.java
 *  Execution:    java PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java
 *
 *  This program creates an initial board from each filename specified
 *  on the command line and finds the minimum number of moves to
 *  reach the goal state.
 *
 *  % java PuzzleChecker puzzle*.txt
 *  puzzle00.txt: 0
 *  puzzle01.txt: 1
 *  puzzle02.txt: 2
 *  puzzle03.txt: 3
 *  puzzle04.txt: 4
 *  puzzle05.txt: 5
 *  puzzle06.txt: 6
 *  ...
 *  puzzle3x3-impossible: -1
 *  ...
 *  puzzle42.txt: 42
 *  puzzle43.txt: 43
 *  puzzle44.txt: 44
 *  puzzle45.txt: 45
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.io.File;

public class PuzzleChecker {

    public static void main(String[] args) {

        // for each command-line argument
//        for (String filename : args) {
            // read in the board specified in the filename
            In in = new In(new File("C:\\Users\\Borui Wang\\Desktop\\8puzzle-testing\\8puzzle\\puzzle2x2-00.txt"));
            int N = in.readInt();
            int[][] tiles = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            StdOut.println("Dimension is: " + initial.dimension());
            StdOut.println(initial);
            StdOut.println("Hamming: " + initial.hamming());
            StdOut.println("isGoal(): " + initial.isGoal());
            StdOut.println("twin(): " + initial.twin());
             
 //           Solver solver = new Solver(initial);
 //           StdOut.println(filename + ": " + solver.moves());
        //}
    }
}