package com.qa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Marker {

    @Retention(RetentionPolicy.RUNTIME)
    @interface Sanity {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Regression {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface All {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Login {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface OrderManagement {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @interface PurchaseOrder {
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @interface PackingSlip {
    }
}

