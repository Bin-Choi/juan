package com.juanbuild.juan.user.domain.policy;

public interface PasswordPolicy {
    boolean isValid(String password);
}
