package io.github.coderodde.combinatorics.exactcover.converters;

import io.github.coderodde.combinatorics.exactcover.ExactCoverInstance;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public final class SudokuToExactCoverInstanceConverterTest {
    
    @Test
    public void conversion() {
        Sudoku sudoku = new Sudoku();
        
        sudoku.set(1, 1, 4);
        sudoku.set(4, 5, 2);
        
        ExactCoverInstance coverInstance = 
            new SudokuToExactCoverInstanceConverter().convert(sudoku);
        
        Sudoku decoded = 
            new ExactCoverInstanceToSudokuConverter().convert(coverInstance);
        
        assertEquals(sudoku, decoded);
    }
}
