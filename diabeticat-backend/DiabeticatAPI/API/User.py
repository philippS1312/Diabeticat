import jwt
from fastapi import APIRouter, Request, FastAPI, Depends, HTTPException
import json
import mysql.connector
from fastapi_jwt_auth import AuthJWT
from fastapi_jwt_auth.exceptions import AuthJWTException
from pydantic import BaseModel
from datetime import timedelta

user_router = APIRouter()

secret_key: str = "ßf134gr08123r0r01r+312ag23ß9dscda4114gvf43dßkß431r"

class Settings(BaseModel):
    authjwt_secret_key: str = "ßf134gr08123r0r01r+312ag23ß9dscda4114gvf43dßkß431r"

def get_config():
    return Settings()

AuthJWT.load_config(get_config)

class User(BaseModel):
    username: str
    password: str

@user_router.post("/login")
async def login(input: Request, Authorize: AuthJWT = Depends()):

    req = await input.json()
    mydb = connectDB()
    if "username" in req and "password" in req:
        if req["username"] is not None and req["password"] is not None:
            username = req["username"]
            password = req["password"]
            mydb = connectDB()
            mycursor = mydb.cursor()
            mycursor.execute("SELECT * FROM User where username ='" + username + "' and password='" + password + "'")
            col_names = [col[0] for col in mycursor.description]
            myresult = mycursor.fetchall()

            if not myresult:
                raise HTTPException(status_code=404, detail="Username or password is incorrect")

            result_list = []
            for row in myresult:
                result_dict = {}
                for i in range(len(col_names)):
                    if(i != 2):
                        result_dict[col_names[i]] = row[i]
                result_list.append(result_dict)

            result_json = json.dumps(result_list)[1:-1]
            StringJson = str(result_json)
            returnJson = json.loads(StringJson)

            userId = returnJson["userId"]

            access_token = Authorize.create_access_token(subject=userId, expires_time=timedelta(hours=24))
            return {"access_token": access_token, "payload": returnJson}
        else:
            raise HTTPException(status_code=404, detail="Username or Password Values not found")
    else:
        raise HTTPException(status_code=404, detail="Username or Password Keys not found")

@user_router.post("/test")
async def login(input: Request):
    req = await input.json()
    return protected(req["access_token"])

def protected(token):
    try:
        var = jwt.decode(token, secret_key, algorithms="HS256")
        return var
    except jwt.exceptions.InvalidTokenError:
        raise HTTPException(status_code=401, detail="Invalid token")

@user_router.post('/createUser')
async def createUser(input: Request):
    req = await input.json()
    mydb = connectDB()
    mycursor = mydb.cursor()
    if "username" in req and "password" in req and "email" in req :
        if req["username"] is not None and req["password"] is not None and req["email"] is not None:
            username = req["username"]
            password = req["password"]
            email = req["email"]

            sql = "INSERT INTO User (username, password, email) VALUES (%s, %s, %s)"
            val = (username, password, email)
            mycursor.execute(sql, val)

            mydb.commit()

            print(mycursor.rowcount, "New User inserted!")

            return {"Succuess": True}

        else:
            raise HTTPException(status_code=404, detail="Username, Password or Email Values not found")
    else:
        raise HTTPException(status_code=404, detail="Email, Username or Password Keys not found")



@user_router.post('/login2')
async def login(input: Request):
    req = await input.json()
    mydb = connectDB()
    if "username" in req and "password" in req:
        if req["username"] is not None and req["password"] is not None:
            username = req["username"]
            password = req["password"]
            mydb = connectDB()
            mycursor = mydb.cursor()
            mycursor.execute("SELECT * FROM User where username ='" + username + "' and password='"+password+"'")
            col_names = [col[0] for col in mycursor.description]
            myresult = mycursor.fetchall()

            result_list = []
            for row in myresult:
                result_dict = {}
                for i in range(len(col_names)):
                    result_dict[col_names[i]] = row[i]
                result_list.append(result_dict)

            result_json = json.dumps(result_list)[1:-1]
            StrinJson= str(result_json)
            returnJson= json.loads(StrinJson)

            return returnJson
        else:
            raise HTTPException(status_code=404, detail="Username or Password Values not found")
    else:
        raise HTTPException(status_code=404, detail="Username or Password Keys not found")

def connectDB():
    mydb = mysql.connector.connect(
        host="sql7.freemysqlhosting.net",
        user="sql7609007",
        password="xSVyvqUxhW",
        database="sql7609007"
    )
    return mydb







