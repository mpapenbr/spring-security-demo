package com.example.demo.service.advanced;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithSecurityContext;

import com.example.demo.model.User;

/**
 * These attributes will be used to create a {@link User} which is set into an otherwise empty
 * {@link SecurityContext}
 *
 * @author mpapenbr
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMyOwnCustomizedSecurityContextFactory.class)
public @interface WithMyOwnStuff {

	String username() default "whatever";

	String email() default "whatever@wherever.com";

}
