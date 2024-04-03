## Thymleaf Template Engine
* Thymeleaf is Java templating engine
* Commonly used to generate the HTML views for web apps
* It is a general purpose templating engine
* Include dynamic content from Thymeleaf expressions
* In a web app, Thymeleaf is processed on the server
* Result included in HTML returned to browser 

![alt text](thymeleaf.png)

* For web apps, Thymeleaf templates have a .html extension
* Additional Features
    * Looping and conditionals
    * CSS and JavaScript integration
    * Template layouts and fragments

* Basic hello world app

```Java
@Controller
public class DemoController {

    @GetMapping("/hello")
    public String sayhello(Model theModel){

        theModel.addAttribute("theDate", new java.util.Date());
        return "helloworld";
    }
}
```
```Html
<!DOCKTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Thymeleaf Demo</title>
</head>
<body>
    <p th:text="'Time on the server is' + ${theDate}"\>

</body>
</html>
```