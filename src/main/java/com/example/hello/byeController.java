package com.example.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class byeController {
    @GetMapping("/bye")
    public String sayBye(@RequestParam(defaultValue = "World") String name) {
        /*
            url 양식 (키워드 인자)
            /hello -> param: default -> Hello, World!
            /hello?name=mario -> param: mario -> Hello, mario!
        */
        return "Bye, " + name + "!";
    }
    @GetMapping("/bye/{name}")
    public String sayByeByPath(@PathVariable String name){
        /*
            url 양식 (URL 경로에 )
            /hello/mario -> param: mario -> Hello, mario!
        */
        return "Bye, " + name + "!";
    }
}




