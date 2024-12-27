package com.example.cosmocatsintergalacticmarketplacebackend.featuretoggle;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class FeatureToggleService {

    @Value("${feature.cosmoCats.enabled}")
    private boolean cosmoCatsEnabled;

    @Value("${feature.kittyProducts.enabled}")
    private boolean kittyProductsEnabled;

}
