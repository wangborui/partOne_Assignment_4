/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package partOne_Assignment_4;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 *
 * @author Borui Wang
 */
public class Solver {

    private SearchNode goal;
    private final Board initial;
    private final Stack<Board> solution;
    private boolean isSolvable;

    private class SearchNode implements Comparable<SearchNode> {

        private Board board;
        private int moves;
        private final int priority;
        private final int manhattan;
        private SearchNode prev;

        private SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.manhattan = this.board.manhattan();
            this.priority = moves + manhattan;
        }

        @Override
        public int compareTo(SearchNode that) {
            Integer thisPri = this.priority;
	    Integer thatPri = that.priority;
	    return thisPri.compareTo(thatPri);
        }

        @Override
        public String toString() {
            String result = "moves: " + this.moves
                    + "\n" + "Hamming(): " + this.manhattan
                    + "\n" + "priority(): " + this.priority
                    + "\n" + this.board.toString();
            return result;
        }
    }

    public Solver(Board initial) // find a solution to the initial board (using the A* algorithm)
    {
        if (initial == null) {
            throw new java.lang.NullPointerException("Initial board is null");
        }
        //make Solver immutable by copying initial board
        this.initial = initial;
        this.solution = new Stack<Board>();
        this.isSolvable = false;

        //start best-first search
        SearchNode initialSN = new SearchNode(initial, 0, null);
        SearchNode initialTwin = new SearchNode(initial.twin(), 0, null);
        MinPQ<Solver.SearchNode> minPQ = new MinPQ<Solver.SearchNode>();
        minPQ.insert(initialSN);
        minPQ.insert(initialTwin);

        //delete from the priority queue with min priority search node
        while (!minPQ.isEmpty()) {
            SearchNode minP = minPQ.delMin();
            //if a goal is found
            if (minP.board.isGoal()) {
                this.goal = minP;

                //populate iterable for solutions
                SearchNode walker = goal;
                while (walker != null) {
                    this.solution.push(walker.board);
                    walker = walker.prev;
                }
                if (this.solution.iterator().next().equals(this.initial)) {
                    this.isSolvable = true;
                }
                break;
            }

            for (Board b : minP.board.neighbors()) {
                //check for duplicates from minP.prev
                if (minP.prev == null) {
                    minPQ.insert(new SearchNode(b, minP.moves + 1, minP));
                } else if (!minP.prev.board.equals(b)) {
                    minPQ.insert(new SearchNode(b, minP.moves + 1, minP));
                }
            }
        }
    }

    public boolean isSolvable() // is the initial board solvable?
    {
        return isSolvable;
    }

    public int moves() // min number of moves to solve initial board; -1 if unsolvable
    {
        int moves = 0;
        if (!isSolvable()) {
            moves = -1;
        } else {
            moves = goal.moves;
        }
        return moves;
    }

    public Iterable<Board> solution() // sequence of boards in a shortest solution; null if unsolvable
    {
        if (isSolvable()) {
            return solution;
        } else {
            return null;
        }
    }

    public static void main(String[] args) // solve a slider puzzle (given below)
    {

    }
}
