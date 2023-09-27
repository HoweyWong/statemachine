package com.example.statemachine.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author huanghao
 * @version 1.0
 * company: ANSO
 * copyright: Copyright (c) 2022
 * modified by:
 * @date 2023/9/27 15:32
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LogResult {
    /**
     * 执行的业务key
     *
     * @return String
     */
    String key();
}
