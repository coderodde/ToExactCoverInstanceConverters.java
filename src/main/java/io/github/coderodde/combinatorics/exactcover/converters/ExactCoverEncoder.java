package io.github.coderodde.combinatorics.exactcover.converters;

import io.github.coderodde.combinatorics.exactcover.ExactCoverInstance;

/**
 * This interface specifies the API for converting from generic states to exact
 * cover instances.
 * 
 * @param <S> the type of the state to encode.
 */
public interface ExactCoverEncoder<S> {

    /**
     * Converts the {@code state} into an exact cover instance.
     * 
     * @param state the state to convert.
     * 
     * @return the exact cover instance representing {@code state}.
     */
    public ExactCoverInstance encode(S state);
}
