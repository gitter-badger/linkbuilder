package com.github.osvaldopina.linkbuilder.argumentresolver;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by osvaldopina on 1/25/16.
 */
public class PageableClassIsPresent implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        try {
            Class.forName("org.springframework.data.domain.Pageable");
            return true;

        } catch (ClassNotFoundException e) {
            return false;
        }

    }
}
