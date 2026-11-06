package io.github.coderodde.combinatorics.exactcover.converters;

import io.github.coderodde.combinatorics.exactcover.ExactCoverInstance;

/**
 * This interface defines the API for converting exact cover instances to 
 * states.
 * 
 * @param <S> the state type to convert to.
 */
public interface FromExactCoverInstanceConverter<S> {
    
    /**
     * Converts the exact cover instance to a state.
     * 
     * @param instance the exact cover instance.
     * 
     * @return the state converted from the exact cover instance.
     */
    public S convert(ExactCoverInstance instance);
}
