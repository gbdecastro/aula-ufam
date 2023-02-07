package com.gabriel.aula.domain.common;

import java.util.Objects;

public abstract class StringUtils {
    public static boolean isEmpty(String value) {
        return Objects.isNull(value) || value.trim().isEmpty();
    }
}