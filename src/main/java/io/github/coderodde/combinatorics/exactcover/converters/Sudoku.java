package io.github.coderodde.combinatorics.exactcover.converters;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class implements the sudoku state.
 */
public final class Sudoku {
    
    public static final int DIMENSION      = 9;
    public static final int BOX_DIMENSION  = 3;
    public static final int MINIMUM_NUMBER = 1;
    public static final int MAXIMUM_NUMBER = 9;
    public static final int UNUSED_NUMBER  = 0;
    
    private final int[][] matrix;
    
    public Sudoku() {
        this.matrix = new int[DIMENSION][DIMENSION];
    }
    
    public Sudoku(Sudoku other) {
        this(other.matrix);
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
    
    public void clear() {
        for (int y = 0; y < matrix.length; ++y) {
            for (int x = 0; x < matrix.length; ++x) {
                matrix[y][x] = UNUSED_NUMBER;
            }
        }
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
