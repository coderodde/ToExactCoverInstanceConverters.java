package io.github.coderodde.combinatorics.exactcover.converters;

import io.github.coderodde.combinatorics.exactcover.ExactCoverSolution;

/**
 * This interface defines the API for converting exact cover instances to 
 * states.
 * 
 * @param <S> the state type to convert to.
 */
public interface FromExactCoverSolutionConverter<S> {
    
    /**
     * Converts the exact cover instance to a state.
     * 
     * @param solution the exact cover solution to convert.
     * 
     * @return the state converted from the exact cover instance.
     */
    public S convert(ExactCoverSolution solution);
}
