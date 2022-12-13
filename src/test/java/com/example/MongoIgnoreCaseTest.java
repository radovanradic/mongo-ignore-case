package com.example;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import java.util.Optional;

@MicronautTest
class MongoIgnoreCaseTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    UserRepository userRepository;

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }

    @Test
    public void testEmailIgnoreCase() {
        User user = new User();
        user.setName("Radovan");
        user.setEmail("radovan.radic@oracle.com");
        userRepository.save(user);

        Optional<User> optUser = userRepository.getUserByEmailCaseInsensitive("RADOVAN.RADIC@ORACLE.COM");
        Assertions.assertTrue(optUser.isPresent());
        optUser = userRepository.getUserByEmailCaseInsensitive("RADOVAN.RADIC@ORACLE.COM1");
        Assertions.assertFalse(optUser.isPresent());

        optUser = userRepository.findByEmailRegex("^(?i)Radovan.Radic@oracle.com$");
        Assertions.assertTrue(optUser.isPresent());
        optUser = userRepository.findByEmailRegex("^Radovan.Radic@oracle.com$");
        Assertions.assertFalse(optUser.isPresent());
    }
}
