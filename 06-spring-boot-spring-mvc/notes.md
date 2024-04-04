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

## Thymleaf and CSS
* You have the option of using:
    1. Local CSS file as part of your project
    1. Referencing remote CSS files
* Spring Boot will look for static resources in the directory
    * src/main/resources/static
    * You can create your own custom sub-directories under static folder

```html
    <link rel="stylesheet" th:href="@{css/style.css}"/>
```

## Spring MVC Behind the Scenes
* Fron controller known as **DispatcherServlet**
    * Part of the Spring Framework
    * Already developed by Spring Dev Team
    * It delegates the request to the Controller
* You will create:
    * **M**odel Objects
    * **V**iew Templates
    * **C**ontroller Classes

* Controller
    * Contains your business logic
    * Handle the request
    * Store/Retrieve data
    * Place data in model
    * Send to appropriate view template

* View Template
    * Recommended: Thymleaf
    * Developer creates a page
        * Displays data

![MVC behind the scene](mvc-behind-the-scene.png)

## Simple Form Eaxmple
* FormController
```Java
@Controller
public class FormController {
    @RequestMapping("/showForm")
    public String showForm(){
        return "form";
    }
    @RequestMapping("/processForm")
    public String processForm(){
        return "p-form";
    }
}
```
```Html
<!DOCKTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Form Controller Demo</title>
</head>
<body>
    <form th:action="@{processForm}" method="GET"/>
        <input type="text" name="studentName" placeholder="Enter the name"/>
        <input type="submit">
    </form>
</body>
</html>
```
```Html
<!DOCKTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Process Form</title>
</head>
<body>
    <p>Student name: <span th:text="${param.studentName}"/></p>
</body>
</html>
```