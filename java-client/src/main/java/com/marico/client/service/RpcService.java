package com.marico.client.service;


import com.marico.client.proto.hello.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Getter
@Service
public class RpcService {

    private HelloServiceGrpc.HelloServiceBlockingStub helloStub;

    @Value("${rpc.host}")
    private String host;
    @Value("${rpc.port}")
    private int serverPort;

    @PostConstruct
    public void init() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, serverPort)
                .usePlaintext().build();
        helloStub = HelloServiceGrpc.newBlockingStub(channel);
    }
}
