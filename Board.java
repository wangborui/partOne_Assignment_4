/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package partOne_Assignment_4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * @author Borui Wang
 */
public class Board {

    private final int N;
    private static final int BLANK = 0;
    private int blankIndex;
    private char[] tiles;

    public Board(int[][] blocks) // construct a board from an N-by-N array of blocks (where blocks[i][j] = block in row i, column j)
    {
        if (blocks == null) {
            throw new java.lang.NullPointerException("no blocks in the board");
        }
        this.N = blocks.length;
        this.tiles = new char[N * N];
        //make Board class immutable
        for (int i = 0; i < N * N; i++) {
            if (blocks[i / N][i % N] == BLANK) {
                this.blankIndex = i;
            }
            this.tiles[i] = (char) blocks[i / N][i % N];
        }
    }

    public int dimension() // board dimension N
    {
        return N;
    }

    public int hamming() // number of blocks out of place
    {
        int outOfPlace = 0;
        for (int i = 0; i < N * N - 1; i++) {
            if (tiles[i] != i + 1) {
                outOfPlace++;
            }
        }
        return outOfPlace;
    }

    public int manhattan() // sum of Manhattan distances between blocks and goal
    {
        int distance = 0;
        for (int i = 0; i < N * N; i++) {
            if (tiles[i] == BLANK || tiles[i] == i + 1) {
                continue;
            } else {
                int indexDif = Math.abs(i - (tiles[i] - 1));
                distance += (indexDif / N + indexDif % N);
            }
        }
        return distance;
    }

    public boolean isGoal() // is this board the goal board?
    {
        for (int i = 0; i < N * N - 1; i++) {
            if (tiles[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    public Board twin() // a board that is obtained by exchanging any pair of blocks
    {
        int first = StdRandom.uniform(N * N), second = StdRandom.uniform(N * N);
        while (first == second || tiles[first] == BLANK || tiles[second] == BLANK) {
            first = StdRandom.uniform(N * N);
            second = StdRandom.uniform(N * N);
        }
        int[][] tempTiles = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                int index = i * N + j;
                if (index == first) {
                    tempTiles[i][j] = tiles[second];
                } else if (index == second) {
                    tempTiles[i][j] = tiles[first];
                } else {
                    tempTiles[i][j] = tiles[i * N + j];
                }
            }
        }
        return new Board(tempTiles);
    }

    public boolean equals(Object y) // does this board equal y?
    {
        if (y == null) {
            return false;
        }
        if (y == this) {
            return true;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;
        for (int i = 0; i < N * N; i++) {
            if (this.tiles[i] != that.tiles[i]) {
                return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() // all neighboring boards
    {
        Queue<Board> queue = new Queue<Board>();

        int left = blankIndex - 1;
        // make sure left and blank on same row and in bound
        if ((left >= 0 && left < N * N) && (left / N == blankIndex / N)) {
            int[][] tempTiles = new int[dimension()][dimension()];
            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    int index = i * N + j;
                    if (index == left) {
                        tempTiles[i][j] = tiles[blankIndex];
                    } else if (index == blankIndex) {
                        tempTiles[i][j] = tiles[left];
                    } else {
                        tempTiles[i][j] = tiles[i * N + j];
                    }
                }
            }
            queue.enqueue(new Board(tempTiles));
        }

        int right = blankIndex + 1;
        // make sure right and blank on same row and in bound
        if ((right >= 0 && right < N * N) && (right / N == blankIndex / N)) {
            int[][] tempTiles = new int[dimension()][dimension()];
            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    int index = i * N + j;
                    if (index == right) {
                        tempTiles[i][j] = tiles[blankIndex];
                    } else if (index == blankIndex) {
                        tempTiles[i][j] = tiles[right];
                    } else {
                        tempTiles[i][j] = tiles[i * N + j];
                    }
                }
            }
            queue.enqueue(new Board(tempTiles));
        }
        int top = blankIndex - N;
        if (top >= 0 && top < N * N) {
            int[][] tempTiles = new int[dimension()][dimension()];
            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    int index = i * N + j;
                    if (index == top) {
                        tempTiles[i][j] = tiles[blankIndex];
                    } else if (index == blankIndex) {
                        tempTiles[i][j] = tiles[top];
                    } else {
                        tempTiles[i][j] = tiles[i * N + j];
                    }
                }
            }
            queue.enqueue(new Board(tempTiles));
        }

        int bottom = blankIndex + N;
        if (bottom >= 0 && bottom < N * N) {
            int[][] tempTiles = new int[dimension()][dimension()];
            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    int index = i * N + j;
                    if (index == bottom) {
                        tempTiles[i][j] = tiles[blankIndex];
                    } else if (index == blankIndex) {
                        tempTiles[i][j] = tiles[bottom];
                    } else {
                        tempTiles[i][j] = tiles[i * N + j];
                    }
                }
            }
            queue.enqueue(new Board(tempTiles));
        }

        return queue;
    }

    public String toString() // string representation of this board (in the output format specified below)
    {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", (int) tiles[i * N + j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {

    }
}
