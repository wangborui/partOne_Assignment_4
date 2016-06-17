/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partOne_Assignment_4;

/**
 *
 * @author Borui Wang
 */
public class Board {
    private final int N;
    private static final int BLANK = 0;
    private char [] tiles;
    
    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks (where blocks[i][j] = block in row i, column j)
    {
        if(blocks == null) throw new java.lang.NullPointerException("no blocks in the board");
        N = blocks.length;
        
        //make Board class immutable
        for(int i = 0; i < N * N; i++){
            tiles[i]  = (char) blocks[i/N][i%N];
        }
    }
    public int dimension()                 // board dimension N
    {
        return N;
    }
    public int hamming()                   // number of blocks out of place
    {
        int outOfPlace = 0;
        for(int i = 0; i < N * N - 1; i++){
          if(tiles[i] != i + 1){
              outOfPlace++;
          }
        }
        return outOfPlace;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        return -1;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        return false;
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        return null;
    }
    public boolean equals(Object y)        // does this board equal y?
    {
        return false;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        return null;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", (int)tiles[i * N + j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {
        
    }
}
