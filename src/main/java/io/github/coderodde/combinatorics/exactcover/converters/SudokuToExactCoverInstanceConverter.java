package io.github.coderodde.combinatorics.exactcover.converters;

import io.github.coderodde.combinatorics.exactcover.ExactCoverInstance;

/**
 * This class provides a method for converting sudoku states to exact cover 
 * instances.
 */
public final class SudokuToExactCoverInstanceConverter 
              implements ToExactCoverInstanceConverter<Sudoku> {

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
    public ExactCoverInstance convert(Sudoku state) {
        ExactCoverInstance instance = new ExactCoverInstance(COLUMNS, ROWS);
        
        final int dimension    = Sudoku.dimension();
        final int boxDimension = Sudoku.boxDimension();
        final int minNumber    = Sudoku.minimumNumber();
        final int maxNumber    = Sudoku.maximumNumber();
        final int unusedNumber = Sudoku.unusedNumber();
        
        for (int r = 0; r < dimension; ++r) {
            for (int c = 0; c < dimension; ++c) {
                int clue = state.get(c, r);
                int box  = boxDimension * (r / boxDimension) + c / boxDimension;
                
                for (int d = minNumber; d <= maxNumber; ++d) {
                    if (clue != unusedNumber && clue != d) {
                        continue;
                    }
                    
                    int digitIndex   = d - 1;
                    int candidateRow = (dimension * r + c) 
                                     * dimension
                                     + digitIndex;
                    
                    int x1 = dimension * dimension;
                    int x2 = 2 * x1;
                    int x3 = 3 * x1;
                    
                    instance.set(     dimension * r + c,          candidateRow);
                    instance.set(x1 + dimension * r + digitIndex, candidateRow);
                    instance.set(x2 + dimension * c + digitIndex, candidateRow);
                    instance.set(x3 + dimension * box + digitIndex, 
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
