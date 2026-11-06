package io.github.coderodde.combinatorics.exactcover.converters;

import io.github.coderodde.combinatorics.exactcover.ExactCoverInstance;

/**
 * This class provides the method for decoding a sudoku state from an exact
 * cover instance.
 */
public final class ExactCoverInstanceToSudokuConverter 
    implements FromExactCoverInstanceConverter<Sudoku> {
    
    @Override
    public Sudoku convert(ExactCoverInstance instance) {
        final int columns = SudokuToExactCoverInstanceConverter.columns();
        final int rows    = SudokuToExactCoverInstanceConverter.rows();
        
        if (instance.width() != columns || instance.height() != rows) {
            throw new IllegalArgumentException(
                ("Expected a " 
                        + columns 
                        + "-column, "
                        + rows
                        + "-row Sudoku instance, got %d columns and %d rows.")
                .formatted(instance.width(), instance.height())
            );
        }
        
        final int dimension = Sudoku.dimension();
        final int unused    = Sudoku.unusedNumber();
        final int minNumber = Sudoku.minimumNumber();
        final int maxNumber = Sudoku.maximumNumber();
        
        final int[][] grid = new int[dimension]
                                    [dimension];
        
        for (int r = 0; r < dimension; ++r) {
            for (int c = 0; c < dimension; ++c) {
                int cell           = dimension * r + c;
                int candidateCount = 0;
                int clue           = unused;
                
                for (int d = minNumber; d <= maxNumber; ++d) {
                    int candidateRow = cell * dimension + d - 1;
                    
                    if (instance.get(cell, candidateRow)) {
                        ++candidateCount;
                        clue = d;
                    }
                }
                
                if (candidateCount == 1) {
                    grid[r][c] = clue;
                } else if (candidateCount == dimension) {
                    grid[r][c] = unused;
                } else {
                    throw new IllegalArgumentException(
                        ("Cell (x = %d, y = %d) has %d candidates, " + 
                         "expected 1 or %d.")
                                .formatted(c, r, candidateCount, dimension));
                }
            }
        }
        
        return new Sudoku(grid);
    }
}
