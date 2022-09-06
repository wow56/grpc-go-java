package main

import (
	"context"
	"fmt"
	"go-server/proto/hello"
	"net"

	"google.golang.org/grpc"
)

type service struct{}

func (s *service) SayHello(ctx context.Context, req *hello.HelloRequest) (*hello.HelloResponse, error) {
	name := req.Name
	return &hello.HelloResponse{
		Message: fmt.Sprintf("hello %s", name),
	}, nil
}

func main() {
	lis, err := net.Listen("tcp", ":9001")
	if err != nil {
		panic(err)
	}

	s := grpc.NewServer()
	//注册 server
	hello.RegisterHelloServiceServer(s, &service{})
	_ = s.Serve(lis)
}
