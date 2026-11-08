package io.github.coderodde.combinatorics.exactcover.converters;

import io.github.coderodde.combinatorics.exactcover.ExactCoverInstance;
import static io.github.coderodde.combinatorics.exactcover.converters.Sudoku.BOX_DIMENSION;
import static io.github.coderodde.combinatorics.exactcover.converters.Sudoku.DIMENSION;
import static io.github.coderodde.combinatorics.exactcover.converters.Sudoku.MAXIMUM_NUMBER;
import static io.github.coderodde.combinatorics.exactcover.converters.Sudoku.MINIMUM_NUMBER;
import static io.github.coderodde.combinatorics.exactcover.converters.Sudoku.UNUSED_NUMBER;

/**
 * This class provides a method for converting sudoku states to exact cover 
 * instances.
 */
public final class SudokuExactCoverEncoder 
              implements ExactCoverEncoder<Sudoku> {

    private static final int COLUMNS = 324;
    private static final int ROWS    = 729;
    
    /**
     * Converts the input sudoku state to an exact cover instance.
     * 
     * @param state the sudoku state to convert.
     * 
     * @return an exact cover instance.
     */
    @Override
    public ExactCoverInstance encode(Sudoku state) {
        ExactCoverInstance instance = new ExactCoverInstance(COLUMNS, ROWS);
        
        for (int r = 0; r < DIMENSION; ++r) {
            for (int c = 0; c < DIMENSION; ++c) {
                int clue = state.get(c, r);
                int box  = BOX_DIMENSION 
                         * (r / BOX_DIMENSION) 
                         + c / BOX_DIMENSION;
                
                for (int d = MINIMUM_NUMBER; d <= MAXIMUM_NUMBER; ++d) {
                    if (clue != UNUSED_NUMBER && clue != d) {
                        continue;
                    }
                    
                    int digitIndex   = d - 1;
                    int candidateRow = (DIMENSION * r + c) 
                                     * DIMENSION
                                     + digitIndex;
                    
                    int x1 = DIMENSION * DIMENSION;
                    int x2 = 2 * x1;
                    int x3 = 3 * x1;
                    
                    instance.set(     DIMENSION * r + c,          candidateRow);
                    instance.set(x1 + DIMENSION * r + digitIndex, candidateRow);
                    instance.set(x2 + DIMENSION * c + digitIndex, candidateRow);
                    instance.set(x3 + DIMENSION * box + digitIndex, 
                                 candidateRow);
                }
            }
        }
        
        return instance;
    }
    
    public static int columns() {
        return COLUMNS;
    }
    
    public static int rows() {
        return ROWS;
    }
}
