## Spring Security
* It defines a framework for security
* Implemented using Servlet filters in the background
* Two methods of securing an app:
    1. Declarative
    1. Porgrammatic
* Servlet Filters are used to pre-process / post-process web request
* Servlet Filters can route web requests based on security logic
* Spring provides a bulk of security functionality with servlet filters

![security-overview](security-overview.png)

![alt text](security-in-action.png)

* **Authentication**: Check user id and password with credentials stored in app / db
* **Authorization**: Check to see if user has an authorized role

```Xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-security</artifactId>
</dependency>
```
* This will automagically secure all endpoints for application
* You can override defualt user name and generated password

## Example
```Java
@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
}
```
![users](users.png)

* Since we defined our users here, Spring Boot will Not use the user/pass from the application.properties file

## Restrict URLs based on Roles

![roles](roles.png)

```Java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception{
    http.authorizeHttpRequests(configurer ->
            configurer
                    .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                    .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                    .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
    );

    // use HTTP basic authentication
    http.httpBasic(Customizer.withDefaults());

    // disable Cross Site Request Forgery (CSRF)
    // in general, not required for statless REST APIs that use POST, PUT, DELETE and/or PATCH
    http.csrf(csrf -> csrf.disable());
    
    return http.build();
}
```

## JDBC Authentication
* Spring Security can read user account info from database
* By default, you have to follow Spring Security's predefined table schemas

![jdbc-auth](image.png)