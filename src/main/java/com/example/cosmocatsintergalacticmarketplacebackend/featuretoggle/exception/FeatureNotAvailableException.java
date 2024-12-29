package com.example.cosmocatsintergalacticmarketplacebackend.featuretoggle.exception;

public class FeatureNotAvailableException extends RuntimeException {
    public FeatureNotAvailableException(String message) {
        super(message);
    }
}