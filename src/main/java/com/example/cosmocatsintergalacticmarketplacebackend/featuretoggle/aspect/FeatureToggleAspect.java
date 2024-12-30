package com.example.cosmocatsintergalacticmarketplacebackend.featuretoggle.aspect;

import com.example.cosmocatsintergalacticmarketplacebackend.featuretoggle.FeatureToggleService;
import com.example.cosmocatsintergalacticmarketplacebackend.featuretoggle.FeatureToggles;
import com.example.cosmocatsintergalacticmarketplacebackend.featuretoggle.annotation.FeatureToggle;
import com.example.cosmocatsintergalacticmarketplacebackend.featuretoggle.exception.FeatureNotAvailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class FeatureToggleAspect {
    private final FeatureToggleService featureToggleService;

    @Before("@annotation(com.example.cosmocatsintergalacticmarketplacebackend.featuretoggle.annotation.FeatureToggle)")
    public void checkFeatureToggle(JoinPoint joinPoint) throws FeatureNotAvailableException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        FeatureToggle featureToggle = method.getAnnotation(FeatureToggle.class);
        FeatureToggles toggle = featureToggle.value();

        if (!isFeatureEnabled(toggle)) {
            throw new FeatureNotAvailableException(toggle.name() + " feature is not available");
        }
    }

    private boolean isFeatureEnabled(FeatureToggles toggle) {
        return switch (toggle) {
            case GET_SPECIFIC_PRODUCT -> featureToggleService.isGetSpecificProductEnabled();
            case ADD_PRODUCT -> featureToggleService.isAddProductEnabled();
        };
    }
}
