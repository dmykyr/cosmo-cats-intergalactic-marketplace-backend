package com.example.cosmocatsintergalacticmarketplacebackend.featuretoggle.annotation;

import com.example.cosmocatsintergalacticmarketplacebackend.featuretoggle.FeatureToggles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureToggle {
    FeatureToggles value();
}
