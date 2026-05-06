package com.example.hello.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {
    @GetMapping("/bye")
    public String sayBye(@RequestParam(defaultValue = "World") String name) {
        /*
            url 양식 (키워드 인자)
            /bye -> param: default -> Bye, World!
            /bye?name=mario -> param: mario -> Bye, mario!
        */
        return "Bye, " + name + "!";
    }
    @GetMapping("/bye/{name}")
    public String sayByeByPath(@PathVariable String name){
        /*
            url 양식 (위치)
            /bye/mario -> param: mario -> Bye, mario!
        */
        return "Bye, " + name + "!";
    }
}




