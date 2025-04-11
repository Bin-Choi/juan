package com.juanbuild.juan.user.infrastructure;

import com.juanbuild.juan.user.domain.policy.PasswordPolicy;

import org.springframework.stereotype.Component;

@Component
public class SimplePasswordPolicy implements PasswordPolicy {

    @Override
    public boolean isValid(String password) {
        return password != null && password.length() >= 8;
    }
}
