# spring-security-demo
Tests and showcases around spring security. Some ideas/methods are adapted from [here][1]

About the test-packages:
- service  
  contains tests around the method-security on a service layer
- web
  contains test around the web-layer. These are divided into "full-blown" web-(mock)-mvc-calls and @WebTests mocking other stuff.

# Order of development
Here the order of branches used during devlopment
- startbase
- service-security
  just focus on testing the (method-) security 
- repository-service-security
  adding users and/or other repository entities to be used in this context.

# Goals for repository-service-security

- have two AuthenticationProvider (read from yaml and read from db)
- play around with @DataJPATest

**Notes**: 
- when using multiple sources for UserDetails better use one UserDetailsService which handles all those sources (?)
- passing a UserPrincipal to InMemoryUserDetailsManager will be stored as a org.springframework.security.core.userdetails.User

# Links
[1]: https://reflectoring.io/unit-testing-spring-boot/