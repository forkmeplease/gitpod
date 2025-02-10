// Copyright (c) 2025 Gitpod GmbH. All rights reserved.
// Licensed under the GNU Affero General Public License (AGPL).
// See License.AGPL.txt in the project root for license information.

// Code generated by protoc-gen-go-grpc. DO NOT EDIT.
// versions:
// - protoc-gen-go-grpc v1.2.0
// - protoc             (unknown)
// source: gitpod/experimental/v1/tokens.proto

package v1

import (
	context "context"
	grpc "google.golang.org/grpc"
	codes "google.golang.org/grpc/codes"
	status "google.golang.org/grpc/status"
)

// This is a compile-time assertion to ensure that this generated file
// is compatible with the grpc package it is being compiled against.
// Requires gRPC-Go v1.32.0 or later.
const _ = grpc.SupportPackageIsVersion7

// TokensServiceClient is the client API for TokensService service.
//
// For semantics around ctx use and closing/ending streaming RPCs, please refer to https://pkg.go.dev/google.golang.org/grpc/?tab=doc#ClientConn.NewStream.
type TokensServiceClient interface {
	// CreatePersonalAccessTokenRequest creates a new token.
	CreatePersonalAccessToken(ctx context.Context, in *CreatePersonalAccessTokenRequest, opts ...grpc.CallOption) (*CreatePersonalAccessTokenResponse, error)
	// ListPersonalAccessTokens returns token by ID.
	GetPersonalAccessToken(ctx context.Context, in *GetPersonalAccessTokenRequest, opts ...grpc.CallOption) (*GetPersonalAccessTokenResponse, error)
	// ListPersonalAccessTokens returns a list of tokens.
	ListPersonalAccessTokens(ctx context.Context, in *ListPersonalAccessTokensRequest, opts ...grpc.CallOption) (*ListPersonalAccessTokensResponse, error)
	// RegeneratePersonalAccessToken generates a new token and replaces the previous one.
	RegeneratePersonalAccessToken(ctx context.Context, in *RegeneratePersonalAccessTokenRequest, opts ...grpc.CallOption) (*RegeneratePersonalAccessTokenResponse, error)
	// UpdatePersonalAccessToken updates writable properties of a PersonalAccessToken.
	UpdatePersonalAccessToken(ctx context.Context, in *UpdatePersonalAccessTokenRequest, opts ...grpc.CallOption) (*UpdatePersonalAccessTokenResponse, error)
	// DeletePersonalAccessToken removes token by ID.
	DeletePersonalAccessToken(ctx context.Context, in *DeletePersonalAccessTokenRequest, opts ...grpc.CallOption) (*DeletePersonalAccessTokenResponse, error)
}

type tokensServiceClient struct {
	cc grpc.ClientConnInterface
}

func NewTokensServiceClient(cc grpc.ClientConnInterface) TokensServiceClient {
	return &tokensServiceClient{cc}
}

func (c *tokensServiceClient) CreatePersonalAccessToken(ctx context.Context, in *CreatePersonalAccessTokenRequest, opts ...grpc.CallOption) (*CreatePersonalAccessTokenResponse, error) {
	out := new(CreatePersonalAccessTokenResponse)
	err := c.cc.Invoke(ctx, "/gitpod.experimental.v1.TokensService/CreatePersonalAccessToken", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

func (c *tokensServiceClient) GetPersonalAccessToken(ctx context.Context, in *GetPersonalAccessTokenRequest, opts ...grpc.CallOption) (*GetPersonalAccessTokenResponse, error) {
	out := new(GetPersonalAccessTokenResponse)
	err := c.cc.Invoke(ctx, "/gitpod.experimental.v1.TokensService/GetPersonalAccessToken", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

func (c *tokensServiceClient) ListPersonalAccessTokens(ctx context.Context, in *ListPersonalAccessTokensRequest, opts ...grpc.CallOption) (*ListPersonalAccessTokensResponse, error) {
	out := new(ListPersonalAccessTokensResponse)
	err := c.cc.Invoke(ctx, "/gitpod.experimental.v1.TokensService/ListPersonalAccessTokens", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

func (c *tokensServiceClient) RegeneratePersonalAccessToken(ctx context.Context, in *RegeneratePersonalAccessTokenRequest, opts ...grpc.CallOption) (*RegeneratePersonalAccessTokenResponse, error) {
	out := new(RegeneratePersonalAccessTokenResponse)
	err := c.cc.Invoke(ctx, "/gitpod.experimental.v1.TokensService/RegeneratePersonalAccessToken", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

func (c *tokensServiceClient) UpdatePersonalAccessToken(ctx context.Context, in *UpdatePersonalAccessTokenRequest, opts ...grpc.CallOption) (*UpdatePersonalAccessTokenResponse, error) {
	out := new(UpdatePersonalAccessTokenResponse)
	err := c.cc.Invoke(ctx, "/gitpod.experimental.v1.TokensService/UpdatePersonalAccessToken", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

func (c *tokensServiceClient) DeletePersonalAccessToken(ctx context.Context, in *DeletePersonalAccessTokenRequest, opts ...grpc.CallOption) (*DeletePersonalAccessTokenResponse, error) {
	out := new(DeletePersonalAccessTokenResponse)
	err := c.cc.Invoke(ctx, "/gitpod.experimental.v1.TokensService/DeletePersonalAccessToken", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

// TokensServiceServer is the server API for TokensService service.
// All implementations must embed UnimplementedTokensServiceServer
// for forward compatibility
type TokensServiceServer interface {
	// CreatePersonalAccessTokenRequest creates a new token.
	CreatePersonalAccessToken(context.Context, *CreatePersonalAccessTokenRequest) (*CreatePersonalAccessTokenResponse, error)
	// ListPersonalAccessTokens returns token by ID.
	GetPersonalAccessToken(context.Context, *GetPersonalAccessTokenRequest) (*GetPersonalAccessTokenResponse, error)
	// ListPersonalAccessTokens returns a list of tokens.
	ListPersonalAccessTokens(context.Context, *ListPersonalAccessTokensRequest) (*ListPersonalAccessTokensResponse, error)
	// RegeneratePersonalAccessToken generates a new token and replaces the previous one.
	RegeneratePersonalAccessToken(context.Context, *RegeneratePersonalAccessTokenRequest) (*RegeneratePersonalAccessTokenResponse, error)
	// UpdatePersonalAccessToken updates writable properties of a PersonalAccessToken.
	UpdatePersonalAccessToken(context.Context, *UpdatePersonalAccessTokenRequest) (*UpdatePersonalAccessTokenResponse, error)
	// DeletePersonalAccessToken removes token by ID.
	DeletePersonalAccessToken(context.Context, *DeletePersonalAccessTokenRequest) (*DeletePersonalAccessTokenResponse, error)
	mustEmbedUnimplementedTokensServiceServer()
}

// UnimplementedTokensServiceServer must be embedded to have forward compatible implementations.
type UnimplementedTokensServiceServer struct {
}

func (UnimplementedTokensServiceServer) CreatePersonalAccessToken(context.Context, *CreatePersonalAccessTokenRequest) (*CreatePersonalAccessTokenResponse, error) {
	return nil, status.Errorf(codes.Unimplemented, "method CreatePersonalAccessToken not implemented")
}
func (UnimplementedTokensServiceServer) GetPersonalAccessToken(context.Context, *GetPersonalAccessTokenRequest) (*GetPersonalAccessTokenResponse, error) {
	return nil, status.Errorf(codes.Unimplemented, "method GetPersonalAccessToken not implemented")
}
func (UnimplementedTokensServiceServer) ListPersonalAccessTokens(context.Context, *ListPersonalAccessTokensRequest) (*ListPersonalAccessTokensResponse, error) {
	return nil, status.Errorf(codes.Unimplemented, "method ListPersonalAccessTokens not implemented")
}
func (UnimplementedTokensServiceServer) RegeneratePersonalAccessToken(context.Context, *RegeneratePersonalAccessTokenRequest) (*RegeneratePersonalAccessTokenResponse, error) {
	return nil, status.Errorf(codes.Unimplemented, "method RegeneratePersonalAccessToken not implemented")
}
func (UnimplementedTokensServiceServer) UpdatePersonalAccessToken(context.Context, *UpdatePersonalAccessTokenRequest) (*UpdatePersonalAccessTokenResponse, error) {
	return nil, status.Errorf(codes.Unimplemented, "method UpdatePersonalAccessToken not implemented")
}
func (UnimplementedTokensServiceServer) DeletePersonalAccessToken(context.Context, *DeletePersonalAccessTokenRequest) (*DeletePersonalAccessTokenResponse, error) {
	return nil, status.Errorf(codes.Unimplemented, "method DeletePersonalAccessToken not implemented")
}
func (UnimplementedTokensServiceServer) mustEmbedUnimplementedTokensServiceServer() {}

// UnsafeTokensServiceServer may be embedded to opt out of forward compatibility for this service.
// Use of this interface is not recommended, as added methods to TokensServiceServer will
// result in compilation errors.
type UnsafeTokensServiceServer interface {
	mustEmbedUnimplementedTokensServiceServer()
}

func RegisterTokensServiceServer(s grpc.ServiceRegistrar, srv TokensServiceServer) {
	s.RegisterService(&TokensService_ServiceDesc, srv)
}

func _TokensService_CreatePersonalAccessToken_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(CreatePersonalAccessTokenRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(TokensServiceServer).CreatePersonalAccessToken(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/gitpod.experimental.v1.TokensService/CreatePersonalAccessToken",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(TokensServiceServer).CreatePersonalAccessToken(ctx, req.(*CreatePersonalAccessTokenRequest))
	}
	return interceptor(ctx, in, info, handler)
}

func _TokensService_GetPersonalAccessToken_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(GetPersonalAccessTokenRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(TokensServiceServer).GetPersonalAccessToken(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/gitpod.experimental.v1.TokensService/GetPersonalAccessToken",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(TokensServiceServer).GetPersonalAccessToken(ctx, req.(*GetPersonalAccessTokenRequest))
	}
	return interceptor(ctx, in, info, handler)
}

func _TokensService_ListPersonalAccessTokens_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(ListPersonalAccessTokensRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(TokensServiceServer).ListPersonalAccessTokens(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/gitpod.experimental.v1.TokensService/ListPersonalAccessTokens",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(TokensServiceServer).ListPersonalAccessTokens(ctx, req.(*ListPersonalAccessTokensRequest))
	}
	return interceptor(ctx, in, info, handler)
}

func _TokensService_RegeneratePersonalAccessToken_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(RegeneratePersonalAccessTokenRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(TokensServiceServer).RegeneratePersonalAccessToken(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/gitpod.experimental.v1.TokensService/RegeneratePersonalAccessToken",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(TokensServiceServer).RegeneratePersonalAccessToken(ctx, req.(*RegeneratePersonalAccessTokenRequest))
	}
	return interceptor(ctx, in, info, handler)
}

func _TokensService_UpdatePersonalAccessToken_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(UpdatePersonalAccessTokenRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(TokensServiceServer).UpdatePersonalAccessToken(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/gitpod.experimental.v1.TokensService/UpdatePersonalAccessToken",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(TokensServiceServer).UpdatePersonalAccessToken(ctx, req.(*UpdatePersonalAccessTokenRequest))
	}
	return interceptor(ctx, in, info, handler)
}

func _TokensService_DeletePersonalAccessToken_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(DeletePersonalAccessTokenRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(TokensServiceServer).DeletePersonalAccessToken(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/gitpod.experimental.v1.TokensService/DeletePersonalAccessToken",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(TokensServiceServer).DeletePersonalAccessToken(ctx, req.(*DeletePersonalAccessTokenRequest))
	}
	return interceptor(ctx, in, info, handler)
}

// TokensService_ServiceDesc is the grpc.ServiceDesc for TokensService service.
// It's only intended for direct use with grpc.RegisterService,
// and not to be introspected or modified (even as a copy)
var TokensService_ServiceDesc = grpc.ServiceDesc{
	ServiceName: "gitpod.experimental.v1.TokensService",
	HandlerType: (*TokensServiceServer)(nil),
	Methods: []grpc.MethodDesc{
		{
			MethodName: "CreatePersonalAccessToken",
			Handler:    _TokensService_CreatePersonalAccessToken_Handler,
		},
		{
			MethodName: "GetPersonalAccessToken",
			Handler:    _TokensService_GetPersonalAccessToken_Handler,
		},
		{
			MethodName: "ListPersonalAccessTokens",
			Handler:    _TokensService_ListPersonalAccessTokens_Handler,
		},
		{
			MethodName: "RegeneratePersonalAccessToken",
			Handler:    _TokensService_RegeneratePersonalAccessToken_Handler,
		},
		{
			MethodName: "UpdatePersonalAccessToken",
			Handler:    _TokensService_UpdatePersonalAccessToken_Handler,
		},
		{
			MethodName: "DeletePersonalAccessToken",
			Handler:    _TokensService_DeletePersonalAccessToken_Handler,
		},
	},
	Streams:  []grpc.StreamDesc{},
	Metadata: "gitpod/experimental/v1/tokens.proto",
}
