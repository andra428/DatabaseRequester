import futures3
import grpc
import users_pb2_grpc
class ServiceLog(users_pb2_grpc.AuthServiceServicer):

    def Login(self, request, context):
        pass

    def Logout(self, request, context):
        pass

def serve():
    server=grpc.server(futures3.ThreadPoolExecutor(max_workers=2))
    users_pb2_grpc.add_AuthServiceServicer_to_server(ServiceLog(),server)
    server.add_insecure_port('[::]:50051')
    server.start()
    print("Server started ")
    server.wait_for_termination()
if __name__ == "__main__":
    serve()