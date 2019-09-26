package com.ehi.controller;

import com.ehi.domain.Plan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class HelloWebFlux {

    /* visit http://localhost:8080/hello */
    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world~");
    }

    @GetMapping("/plan/1")
    public Mono<Plan> getPlan() {
        Plan plan = new Plan();
        plan.setId(1);
        plan.setCarrierId(9999);
        plan.setName("test plan");
        plan.setStatus("Q");
        plan.setType(8);
        return Mono.create(sink -> {
            sink.success(plan);
        });
        //return Mono.just(plan);
    }

    @GetMapping("/plans")
    public Flux getPlans() {
        Plan plan = new Plan();
        plan.setId(1);
        plan.setCarrierId(9999);
        plan.setName("test plan");
        plan.setStatus("Q");
        plan.setType(8);
        List<Plan> plans = new ArrayList<>();
        plans.add(plan);
        Plan plan2 = new Plan();
        plan2.setId(1);
        plan2.setCarrierId(9999);
        plan2.setName("test plan");
        plan2.setStatus("Q");
        plan2.setType(8);
        plans.add(plan2);
        return Flux.fromIterable(plans);
    }


    /* visit http://localhost:8080/time?cities=xiamen&cities=fuzhou */
    @RequestMapping("/time")
    public Flux<String> timeHandle(@RequestParam List<String> cities) {
        //将Flux的每个元素映射为一doThing耗时操作
        System.out.println("start");
        Flux<String> flux = Flux.fromStream(cities.stream().map(i -> doThing("elem-" + i)));
        System.out.println("end");
        return flux;
    }

    //定义耗时操作
    private String doThing(String msg) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg + "\n";
    }
}
