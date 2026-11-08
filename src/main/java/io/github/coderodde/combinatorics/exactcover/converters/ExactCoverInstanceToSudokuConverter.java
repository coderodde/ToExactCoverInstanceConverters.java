package io.github.coderodde.combinatorics.exactcover.converters;

import io.github.coderodde.combinatorics.exactcover.ExactCoverInstance;
import static io.github.coderodde.combinatorics.exactcover.converters.Sudoku.DIMENSION;
import static io.github.coderodde.combinatorics.exactcover.converters.Sudoku.MAXIMUM_NUMBER;
import static io.github.coderodde.combinatorics.exactcover.converters.Sudoku.MINIMUM_NUMBER;
import static io.github.coderodde.combinatorics.exactcover.converters.Sudoku.UNUSED_NUMBER;

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
        
        final int[][] grid = new int[DIMENSION]
                                    [DIMENSION];
        
        for (int r = 0; r < DIMENSION; ++r) {
            for (int c = 0; c < DIMENSION; ++c) {
                int cell           = DIMENSION * r + c;
                int candidateCount = 0;
                int clue           = UNUSED_NUMBER;
                
                for (int d = MINIMUM_NUMBER; d <= MAXIMUM_NUMBER; ++d) {
                    int candidateRow = cell * DIMENSION + d - 1;
                    
                    if (instance.get(cell, candidateRow)) {
                        ++candidateCount;
                        clue = d;
                    }
                }
                
                switch (candidateCount) {
                    case 1:
                        grid[r][c] = clue;
                        break;
                        
                    case DIMENSION:
                        grid[r][c] = UNUSED_NUMBER;
                        break;
                        
                    default:
                        throw new IllegalArgumentException(
                                ("Cell (x = %d, y = %d) has %d candidates, " +
                                "expected 1 or %d.")
                                .formatted(c, r, candidateCount, DIMENSION));
                }
            }
        }
        
        return new Sudoku(grid);
    }
}
