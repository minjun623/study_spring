package com.example.hello.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(@RequestParam(defaultValue = "World") String name) {
        /*
            url 양식 (키워드 인자)
            /hello -> param: default -> Hello, World!
            /hello?name=mario -> param: mario -> Hello, mario!
        */
        return "Hello, " + name + "!";
    }
    @GetMapping("/hello/{name}")
    public String sayHelloByPath(@PathVariable String name){
        /*
            url 양식 (URL 경로에 직접 박혀 들어옴)
            /hello/mario -> param: mario -> Hello, mario!
        */
        return "Hello, " + name + "!";
    }
    @GetMapping("/hello_age")
    public String sayHelloWithAge(@RequestParam String name,
                                  @RequestParam(defaultValue = "20") int age){
        /*
            url양식 (키워드)
            /hello_age?age=30&name=mario

            test: /hello_age?age=30&name=mario&age=15 -> age:30, name: mario -> 중복시 첫번째로 받은게 들어옴
            -> 여러개 쓸거면 리스트(List<Integer> age)로 받기
        */
        return "Hello, age: " + age + ", name: " + name;
    }
    @GetMapping("/hello_age_2/{name}/{age}")
    public String sayHelloByPath2(
            @PathVariable String name,
            @PathVariable int age
    ) {
        /*
        url 양식 (위치)
        /hello_age_2/mario/20 -> 문제 X
        /hello_age_2/20/mario -> 400 error
        /hello_age_2/mario/20/20 -> 404 error (2개만 위치 인자로 받아야 하는데 3개 넣음)
        */
        return "Hello, age: " + age + ", name: " + name;
    }
    @GetMapping("/hello_mix/{name}/")
    public  String sayHelloMix(
            @PathVariable String name,
            @RequestParam (defaultValue = "20") int age
    ){
        /*
        url 양식: (위치 + 키워드)
        /hello_mix/mario/ -> name: mario, age: 20 (default)
        /hello_mix/mario/?age=30 -> name=mario, age=30
        /hello_mix/mario -> 404 error
        */
        return "your name: " + name + " - your age:" + age;
    }

    @GetMapping("/hello_mix_json/{name}/")
    public Map<String, Object> sayHelloMixJson(
            @PathVariable String name,
            @RequestParam(defaultValue = "20") int age
    ) {
        /*
        url 양식: (위치 + 키워드)
        /hello_mix_json/mario/ -> name: mario, age: 20 (default)
        /hello_mix_json/mario/?age=30 -> name=mario, age=30
        /hello_mix_json/mario -> 404 error

        출력 형태가 json
        */
        return Map.of(
                "name", name,
                "age", age,
                "greeting", "Hello, " + name + "!"
        );
    }
    @GetMapping("/hello_list")
    public Map<String, Object> sayHelloList(
            @RequestParam List<String> names
    ) {
        /*
        url 양식: 다중 키워드
        /hello_list?names=mario&names=kupa&names=yoshi -> names=["mario, "kupa", "yoshi"]
        */
        return Map.of(
                "received", names,
                "count", names.size()
        );
    }
}
