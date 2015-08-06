/*************************************************************************
 *  Compilation:  javac PuzzleChecker.java
 *  Execution:    java PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java In.java
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
 *  8puzzle/puzzle00.txt
8puzzle/puzzle01.txt
8puzzle/puzzle02.txt
8puzzle/puzzle03.txt
8puzzle/puzzle04.txt
8puzzle/puzzle05.txt
8puzzle/puzzle06.txt
8puzzle/puzzle07.txt
8puzzle/puzzle08.txt
8puzzle/puzzle09.txt
8puzzle/puzzle10.txt
8puzzle/puzzle11.txt
8puzzle/puzzle12.txt
8puzzle/puzzle13.txt
8puzzle/puzzle14.txt
8puzzle/puzzle15.txt
8puzzle/puzzle16.txt
8puzzle/puzzle17.txt
8puzzle/puzzle18.txt
8puzzle/puzzle19.txt
8puzzle/puzzle20.txt
8puzzle/puzzle21.txt
8puzzle/puzzle22.txt
8puzzle/puzzle23.txt
8puzzle/puzzle24.txt
8puzzle/puzzle25.txt
8puzzle/puzzle26.txt
8puzzle/puzzle27.txt
8puzzle/puzzle28.txt
8puzzle/puzzle29.txt
8puzzle/puzzle2x2-00.txt
8puzzle/puzzle2x2-01.txt
8puzzle/puzzle2x2-02.txt
8puzzle/puzzle2x2-03.txt
8puzzle/puzzle2x2-04.txt
8puzzle/puzzle2x2-05.txt
8puzzle/puzzle2x2-06.txt
8puzzle/puzzle2x2-unsolvable1.txt
8puzzle/puzzle2x2-unsolvable2.txt
8puzzle/puzzle2x2-unsolvable3.txt
8puzzle/puzzle30.txt
8puzzle/puzzle31.txt
8puzzle/puzzle32.txt
8puzzle/puzzle33.txt
8puzzle/puzzle34.txt
8puzzle/puzzle35.txt
8puzzle/puzzle36.txt
8puzzle/puzzle37.txt
8puzzle/puzzle38.txt
8puzzle/puzzle39.txt
8puzzle/puzzle3x3-00.txt
8puzzle/puzzle3x3-01.txt
8puzzle/puzzle3x3-02.txt
8puzzle/puzzle3x3-03.txt
8puzzle/puzzle3x3-04.txt
8puzzle/puzzle3x3-05.txt
8puzzle/puzzle3x3-06.txt
8puzzle/puzzle3x3-07.txt
8puzzle/puzzle3x3-08.txt
8puzzle/puzzle3x3-09.txt
8puzzle/puzzle3x3-10.txt
8puzzle/puzzle3x3-11.txt
8puzzle/puzzle3x3-12.txt
8puzzle/puzzle3x3-13.txt
8puzzle/puzzle3x3-14.txt
8puzzle/puzzle3x3-15.txt
8puzzle/puzzle3x3-16.txt
8puzzle/puzzle3x3-17.txt
8puzzle/puzzle3x3-18.txt
8puzzle/puzzle3x3-19.txt
8puzzle/puzzle3x3-20.txt
8puzzle/puzzle3x3-21.txt
8puzzle/puzzle3x3-22.txt
8puzzle/puzzle3x3-23.txt
8puzzle/puzzle3x3-24.txt
8puzzle/puzzle3x3-25.txt
8puzzle/puzzle3x3-26.txt
8puzzle/puzzle3x3-27.txt
8puzzle/puzzle3x3-28.txt
8puzzle/puzzle3x3-29.txt
8puzzle/puzzle3x3-30.txt
8puzzle/puzzle3x3-31.txt
8puzzle/puzzle3x3-unsolvable1.txt
8puzzle/puzzle3x3-unsolvable2.txt
8puzzle/puzzle3x3-unsolvable.txt
8puzzle/puzzle40.txt
8puzzle/puzzle41.txt
8puzzle/puzzle42.txt
8puzzle/puzzle43.txt
8puzzle/puzzle44.txt
8puzzle/puzzle45.txt
8puzzle/puzzle46.txt
8puzzle/puzzle47.txt
8puzzle/puzzle48.txt
8puzzle/puzzle49.txt
8puzzle/puzzle4x4-00.txt
8puzzle/puzzle4x4-01.txt
8puzzle/puzzle4x4-02.txt
8puzzle/puzzle4x4-03.txt
8puzzle/puzzle4x4-04.txt
8puzzle/puzzle4x4-05.txt
8puzzle/puzzle4x4-06.txt
8puzzle/puzzle4x4-07.txt
8puzzle/puzzle4x4-08.txt
8puzzle/puzzle4x4-09.txt
8puzzle/puzzle4x4-10.txt
8puzzle/puzzle4x4-11.txt
8puzzle/puzzle4x4-12.txt
8puzzle/puzzle4x4-13.txt
8puzzle/puzzle4x4-14.txt
8puzzle/puzzle4x4-15.txt
8puzzle/puzzle4x4-16.txt
8puzzle/puzzle4x4-17.txt
8puzzle/puzzle4x4-18.txt
8puzzle/puzzle4x4-19.txt
8puzzle/puzzle4x4-20.txt
8puzzle/puzzle4x4-21.txt
8puzzle/puzzle4x4-22.txt
8puzzle/puzzle4x4-23.txt
8puzzle/puzzle4x4-24.txt
8puzzle/puzzle4x4-25.txt
8puzzle/puzzle4x4-26.txt
8puzzle/puzzle4x4-27.txt
8puzzle/puzzle4x4-28.txt
8puzzle/puzzle4x4-29.txt
8puzzle/puzzle4x4-30.txt
8puzzle/puzzle4x4-31.txt
8puzzle/puzzle4x4-32.txt
8puzzle/puzzle4x4-33.txt
8puzzle/puzzle4x4-34.txt
8puzzle/puzzle4x4-35.txt
8puzzle/puzzle4x4-36.txt
8puzzle/puzzle4x4-37.txt
8puzzle/puzzle4x4-38.txt
8puzzle/puzzle4x4-39.txt
8puzzle/puzzle4x4-40.txt
8puzzle/puzzle4x4-41.txt
8puzzle/puzzle4x4-42.txt
8puzzle/puzzle4x4-43.txt
8puzzle/puzzle4x4-44.txt
8puzzle/puzzle4x4-45.txt
8puzzle/puzzle4x4-46.txt
8puzzle/puzzle4x4-47.txt
8puzzle/puzzle4x4-48.txt
8puzzle/puzzle4x4-49.txt
8puzzle/puzzle4x4-50.txt
8puzzle/puzzle4x4-78.txt
8puzzle/puzzle4x4-80.txt
8puzzle/puzzle4x4-unsolvable.txt
8puzzle/puzzle50.txt
 *
 *************************************************************************/

public class PuzzleChecker {

    public static void main(String[] args) {

        // for each command-line argument
        for (String filename : args) {

            // read in the board specified in the filename
            In in = new In(filename);
            int N = in.readInt();
            int[][] tiles = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            Solver solver = new Solver(initial);
            System.out.println(filename + ": " + solver.moves());
        }
    }
}