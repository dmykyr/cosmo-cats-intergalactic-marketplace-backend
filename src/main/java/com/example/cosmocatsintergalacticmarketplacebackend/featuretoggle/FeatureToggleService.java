package com.example.cosmocatsintergalacticmarketplacebackend.featuretoggle;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class FeatureToggleService {

    @Value("${feature.addProduct.enabled:false}")
    private boolean addProductEnabled;

    @Value("${feature.getSpecificProduct.enabled:false}")
    private boolean getSpecificProductEnabled;

}
