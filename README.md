# spring-security-demo
Tests and showcases around spring security. Some ideas/methods are adapted from [here][1]

About the test-packages:
- repository
  contains small tests around repository (DAO) layer
- service  
  contains tests around the method-security on a service layer
- web
  contains test around the web-layer. These are divided into "full-blown" web-(mock)-mvc-calls and @WebTests mocking other stuff.

# Order of development
Here the order of branches used during development
- startbase
- step 1  
  just focus on testing the (method-) security 
- step 2   
  adding users and/or other repository entities to be used in this context.


# Goals for step  2

## Combine different user sources (read from yaml and read from db)
**Notes**: 
- when using multiple sources for `UserDetails` better use one `UserDetailsService` which handles all those sources (?)
- passing a `UserPrincipal` to `InMemoryUserDetailsManager` will be stored as a `org.springframework.security.core.userdetails.User`

## Play around with @DataJPATest
We're in a one-module project, so we don't want the layers above DAO-layer to be initialized. That's where `@Data...Test` annotations come in handy. 

## Liquibase
### Issues
Since we're using h2 with in-memory-mode in this environment there are some test issues.

Every test, when run standalone, has to initialize the schema. So far no problem since Spring-Liquibase integration can take care of this. 

**Side note:** This will be done every time spring creates a new application context. `TestRepos` and `TestRepos2` share the same application context configuration. Spring caches the contextes used during testing and thats why we may see less Spring initialization blocks in the logs than the number of tests.

For non-test-only environments this could be a problem since there is really not much work to do. In bigger enviroments this may be a problem. 

### Proper initialization and cleanup

By default for each test a rollback is issued at the end of a test. So we don't need to think about data that may be created during a test.

Another aspect is shown in `TestReposWithInitData`: 

We simulate a test where the database is initialized with some data on class level. Something "strange" occured with the sequence. The sequence counter doesn't seem to be updated by liquibase-insert. It is unclear who is to "blame" (liquibase, h2, spring, myself...). The expectation was to "do nothing and all works" but in fact we had to assign a startvalue for the sequence. When doing so, the tests in `TestReposWithInitData` succeeds, otherwise we would get an error about duplicate primary keys.

The `TestSuite` was implemented to prove that the setup works for changing test setups during execution.

# Links
[1]: https://reflectoring.io/unit-testing-spring-boot/