import grpc
import users_pb2
import users_pb2_grpc


def run():
    with grpc.insecure_channel('localhost:50051') as channel:
        stub = users_pb2_grpc.AuthServiceStub(channel)

        request = users_pb2.UserCredentials(username="prof.prof", password="december")
        response = stub.AuthenticateUser(request)

        token_request = users_pb2.Token(token_value=response.token_value)
        try:
            validate_response = stub.ValidateToken(token_request)
            add_response3 = stub.InvalidateToken(token_request)
            print(f"{response}")
            print(f"{token_request.token_value}")
            print(f"Validate result {validate_response}")
            print(f"Invalidate result {add_response3}")
        except grpc.RpcError as e:
            print(e)


if __name__ == '__main__':
    run()
