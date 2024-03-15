import base64
import json
from datetime import datetime, timedelta
import jwt
ISS = "http://127.0.0.1:50051"
def readList():
    with open("list.txt", "r") as list_input:
        list = json.load(list_input)

    return list

def writeList(list):
    it = json.dumps(list, indent=4)
    with open("list.txt", "w") as list_output:
        list_output.write(it)

def checkDate(token, payload):
    list = readList()
    lt = datetime.now()
    exp = datetime.fromtimestamp(payload['exp']) - timedelta(hours=2)
    if lt>exp:
        uuid = payload['jti']
        list[uuid] = token
        writeList(list)
        return False
    return True

def checkToken(token):
    list = readList()
    try:
        p = jwt.decode(token, "secret", "HS256")
        return p

    except Exception as e:
        p2 = decode(token)
        uuid = p2['jti']
        list[uuid] = token
        writeList(list)

        return None

def decode(token):
    h, p, s = token.split(".")
    p = base64.urlsafe_b64decode(p)
    p = p.decode('utf-8')
    p = json.loads(p)

    return p
