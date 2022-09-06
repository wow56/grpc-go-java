package com.marico.client.controller;

import com.marico.client.proto.HelloRequest;
import com.marico.client.proto.HelloResponse;
import com.marico.client.service.RpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RpcService rpcService;

    @GetMapping("/test/{name}")
    public String test(@PathVariable String name) {
        HelloResponse response = rpcService.getHelloStub().sayHello(
                HelloRequest.newBuilder().setName(name).build()
        );
        return response.getMessage();
    }
}
