import os;
import string;
import random;
import requests;
import json

users=list()

def text_generator(size=240, chars=string.ascii_uppercase + string.digits):
  return ''.join(random.choice(chars) for _ in range(size))

def name_generator(size=7, chars=string.ascii_uppercase + string.digits):
  return ''.join(random.choice(chars) for _ in range(size))

def id_generator(size=24, chars='abcdef' + string.digits):
  return ''.join(random.choice(chars) for _ in range(size))

def user_id_generator_saved(size=24, chars='abcdef' + string.digits):
  return random.choice(users)['userId']

url = "http://localhost:8812/poster/api/user/registered/all/"
credentials=('user', 'password')
r = requests.get(url, auth=credentials)
users=r.json()['users']
print(users)

for x in range(0,512):
  url = "http://localhost:8812/poster/api/post/save"
  data = {"text": text_generator(),"userId": user_id_generator_saved()}
  headers = {'Content-type': 'application/json'}
  credentials=('user', 'password')
  r = requests.post(url, data=json.dumps(data), headers=headers, auth=credentials)
  print(r.json())
