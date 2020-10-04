# spring-security-demo
Tests and showcases around spring security. Some ideas/methods are adapted from [here][1]

About the test-packages:
- service  
  contains tests around the method-security on a service layer
- web
  contains test around the web-layer. These are divided into "full-blown" web-(mock)-mvc-calls and @WebTests mocking other stuff.

# Order of development
Here the order of branches used during development
- startbase  
  Starting point with commen setup
- step 1  
  just focus on testing the (method-) security. This is also a place to evaluate different test methods. Buzzwords are `@WithMockUser`, `@WebMvcTest`

# Links
[1]: https://reflectoring.io/unit-testing-spring-boot/