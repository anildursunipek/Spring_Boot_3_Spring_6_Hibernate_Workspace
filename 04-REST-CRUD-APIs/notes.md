## REST Services
* REST: **RE**presentational **S**tate **T**ransfer
* Lightweight approach for communicating between applications
* The client and server applications can use **ANY** programming language
* REST application can use any data format
* Commonly see XML and JSON
* REST calls can be made over HTTP
* REST is language independent

## JSON Data Format
* **J**avascript **O**bject **N**otation
* JSON is most popular and modern
* Lightweight data format for storing and exchanging data
* It is just plain text data
* Language independent
* Curley braces define objects in JSON
* Object members are name / value pairs (delimited by colons)
* Names (keys) is always in double-quotes
* JSON Values:
    * Numbers: No quotes
    * String: In double quotes
    * Boolean: true, false
    * Nested JSON Objects
    * Array
    * null

## REST HTTP Basics
* Most common use of REST is over HTTP
* Leverage HTTP methods for CRUD operations

Name | Description |
--- | --- |
***POST*** | Create a new entity|
***GET*** | Read a list of entities or single entity |
***PUT*** | Update an existing entity |
***DELETE*** | Delete an existing entity |

* HTTP Request Message:
    * Request line: the HTTP command
    * Header veriables: request metadata
    * Message body: contents of message
* HTTP Response Message:
    * Response line: Server protocol and status code
    * Header veriables: Response metadata
    * Message body: contents of message

Name | Description |
--- | --- |
***100-199*** | Informational |
***200-299*** | Successful |
***300-399*** | Redirection |
***400-499*** | Client Error |
***500-599*** | Server Error |

## MIME Content Type
* The message format is described by MIME content type
    * **M**ultipurpose **I**nternet **M**ail-**E**xtension
* Basic Syntax: type/sub-type
* Examples:
    * text/html, text/plain, application/json, application/xml

## Basic Hello World REST Service Example
```Java
@RestController
@RequestMapping("/test")
public class DemoRestController {
    // add code for the "/hello" endpoint
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }
}
```