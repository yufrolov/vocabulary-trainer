package com.yufrolov.vocabularytrainer.enums;

import lombok.Getter;

@Getter
public enum Roles {
    ROLE_USER(1),
    ROLE_ADMIN(2);

    private final long value;

    Roles(long value) {
        this.value = value;
    }


}
