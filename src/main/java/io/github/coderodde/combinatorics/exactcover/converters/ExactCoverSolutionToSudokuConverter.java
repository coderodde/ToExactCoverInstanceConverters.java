package io.github.coderodde.combinatorics.exactcover.converters;

import io.github.coderodde.combinatorics.exactcover.ExactCoverSolution;
import static io.github.coderodde.combinatorics.exactcover.converters.Sudoku.DIMENSION;
import java.util.Objects;

/**
 * This class provides the method for decoding a sudoku state from an exact
 * cover instance.
 */
public final class ExactCoverSolutionToSudokuConverter 
    implements FromExactCoverSolutionConverter<Sudoku> {
    
    @Override
    public Sudoku convert(ExactCoverSolution solution) { // <-- the parameter must be a ExactCoverSolution
        Objects.requireNonNull(solution, "The input solution is null.");
  
        int cellCount         = DIMENSION * DIMENSION;
        int candidateRowCount = DIMENSION * cellCount;
    
        int[][] grid = new int[DIMENSION]
                              [DIMENSION];
  
        boolean[][] assigned = new boolean[DIMENSION]
                                          [DIMENSION];
        
        for (int candidateRow : solution.selectedRows()) {
            if (candidateRow < 0 || candidateRow >= candidateRowCount) {
                throw new IllegalArgumentException(
                    "Invalid candidate row index: " + candidateRow);
            }
            
            int number = candidateRow % DIMENSION + 1;
            int cell   = candidateRow / DIMENSION;
            int x      = cell % DIMENSION;
            int y      = cell / DIMENSION;
            
            if (assigned[y][x]) {
                throw new IllegalArgumentException(
                    "Multiple selected candidates for cell (%d, %d)."
                    .formatted(x, y));
            }
            
            assigned[y][x] = true;
            grid[y][x]     = number;
        }
        
        return new Sudoku(grid);
    }
}
