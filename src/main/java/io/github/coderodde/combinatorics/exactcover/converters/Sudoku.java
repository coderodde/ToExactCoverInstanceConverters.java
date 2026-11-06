package io.github.coderodde.combinatorics.exactcover.converters;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class implements the sudoku state.
 */
public final class Sudoku {
    
    private static final int DIMENSION      = 9;
    private static final int BOX_DIMENSION  = 3;
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 9;
    private static final int UNUSED_NUMBER  = 0;
    
    private final int[][] matrix;
    
    public Sudoku() {
        this.matrix = new int[DIMENSION][DIMENSION];
    }
    
    public Sudoku(int[][] grid) {
        this();
        
        Objects.requireNonNull(grid, "The input grid is null.");
        
        if (grid.length != DIMENSION) {
            throw new IllegalArgumentException(
                "Expected %d rows, %d received.".formatted(DIMENSION, 
                                                           grid.length));
        }
        
        for (int[] row : grid) {
            Objects.requireNonNull(row, "A row is null.");
            
            if (row.length != DIMENSION) {
                throw new IllegalArgumentException(
                    "A row is %d elements long, %d expected."
                            .formatted(row.length, DIMENSION));
            }
        }
        
        for (int y = 0; y < grid.length; ++y) {
            for (int x = 0; x < grid[y].length; ++x) {
                set(x, y, grid[y][x]);
            }
        }
    }
    
    public int get(int x, int y) {
        return matrix[checkY(y)][checkX(x)];
    }
    
    public void set(int x, int y, int number) {
        matrix[checkY(y)][checkX(x)] = checkNumber(number);
    }
    
    public static int dimension() {
        return DIMENSION;
    }
    
    public static int boxDimension() {
        return BOX_DIMENSION;
    }
    
    public static int minimumNumber() {
        return MINIMUM_NUMBER;
    }
    
    public static int maximumNumber() {
        return MAXIMUM_NUMBER;
    }
    
    public static int unusedNumber() {
        return UNUSED_NUMBER;
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof Sudoku s && Arrays.deepEquals(matrix, s.matrix);
    }
    
    private static int checkY(int y) {
        if (y < 0 || y >= DIMENSION) {
            throw new IndexOutOfBoundsException("y = " + y);
        }
        
        return y;
    }
    
    private static int checkX(int x) {
        if (x < 0 || x >= DIMENSION) {
            throw new IndexOutOfBoundsException("x = " + x);
        }
        
        return x;
    }
    
    private static int checkNumber(int number) {
        
        if (number != UNUSED_NUMBER && 
                ((number < MINIMUM_NUMBER ||
                  number > MAXIMUM_NUMBER))) {
            
            throw new IndexOutOfBoundsException("number = " + number);
        }
        
        return number;
    }
}
