package com.daisy.happyhorse.model.business.manager;

import com.daisy.happyhorse.model.domain.RentalCart;
import com.daisy.happyhorse.model.services.manager.IPropertyManager;

public abstract class Manager {
    private final IPropertyManager propertyManager;

    public Manager(IPropertyManager manager) {
        propertyManager = manager;
    }

    public String getProperty(String key) {
        return propertyManager.getProperty(key);
    }

    public abstract boolean performAction(String command, RentalCart cart);
}