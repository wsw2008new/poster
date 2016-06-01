import os;
import string;
import random;
import requests;
import json


def name_generator(size=7, chars=string.ascii_uppercase + string.digits):
  return ''.join(random.choice(chars) for _ in range(size))

def id_generator(size=24, chars='abcdef' + string.digits):
  return ''.join(random.choice(chars) for _ in range(size))

for x in range(0,10):
  url = "http://localhost:8812/poster/api/user/register"
  data = {"userNickName": name_generator(),"userName": name_generator()}
  headers = {'Content-type': 'application/json'}
  credentials=('user', 'password')
  r = requests.post(url, data=json.dumps(data), headers=headers, auth=credentials)
  userId=r.json()['userId']
  print(userId)
