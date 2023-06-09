from fastapi import APIRouter, Request, HTTPException
import mysql.connector
import json
from datetime import datetime, date
from typing import List, Dict
from fastapi_jwt_auth import AuthJWT
import jwt

from API.MeasurementData import handle_datetime

pet_router = APIRouter()
secret_key: str = "ßf134gr08123r0r01r+312ag23ß9dscda4114gvf43dßkß431r"


def connectDB():
    mydb = mysql.connector.connect(
        host="sql7.freemysqlhosting.net",
        user="sql7614321",
        password="6QcZvqkwMb",
        database="sql7614321"
    )
    return mydb


def protected(token):
    try:
        var = jwt.decode(token, secret_key, algorithms="HS256")
        return var
    except jwt.exceptions.InvalidTokenError:
        return {"success": False,
                "statuscode": 400,
                "notice": "Invalid Access Token"}


def handle_datetime(obj):
    """Helper function to handle datetime objects during JSON serialization"""
    if isinstance(obj, (datetime, date)):
        return obj.isoformat()


@pet_router.post("/api/createPet")
async def createPet(pet: Request):
    req_pet = await pet.json()
    if "access_token" in req_pet:
        token = protected(req_pet["access_token"])
        if "sub" in token:
            userid = token["sub"]
        else:
            return {"Succuess": False,
                    "statuscode": 400,
                    "Notice": "Invalid Access Token!"}
    else:
        raise HTTPException(status_code=422,
                            detail="At least one of the following request parameters is missing: 'access_token'")

    if "name" in req_pet and "type" in req_pet and "birthday" in req_pet:
        name = req_pet["name"]
        type = req_pet["type"]
        birthday = req_pet["birthday"]
        color = "blue"

        if ("color" in req_pet):
            color = req_pet["color"]

        mydb = connectDB()
        mycursor = mydb.cursor()

        sql = "INSERT INTO Pet (name, type, birthday, userid, color) VALUES (%s, %s, %s, %s, %s)"
        val = (name, type, birthday, userid, color)
        mycursor.execute(sql, val)

        mydb.commit()
        print(mycursor.rowcount, "New Pet inserted!")

        return {"Succuess": True,
                "Notice": "New Pet inserted!"}
    else:
        raise HTTPException(status_code=422,
                            detail="At least one of the following request parameters is missing: 'name', 'type'")


@pet_router.post("/api/deletePet")
async def deletePet(input: Request):
    req = await input.json()

    if "access_token" in req:
        token = protected(req["access_token"])
        if "sub" in token:
            userId = str(token["sub"])
        else:
            return {"Succuess": False,
                    "statuscode": 400,
                    "Notice": "Invalid Access Token!"}

    if "petid" in req:
        petid = str(req["petid"])
        mydb = connectDB()
        mycursor = mydb.cursor()

        sql = "DELETE FROM Pet WHERE petId='" + petid + "' AND userid='" + userId + "'"
        mycursor.execute(sql)

        mydb.commit()
        print("Pet sucessfully deleted!")

        return {"Succuess": True,
                "Notice": "Pet sucessfully deleted!"}
    else:
        raise HTTPException(status_code=422,
                            detail="At least one of the following request parameters is missing: 'petId'")


@pet_router.post('/api/getPetsByUser')
async def getPetsByUser(input: Request):
    req = await input.json()
    mydb = connectDB()

    if "access_token" in req:
        token = protected(req["access_token"])
        if "sub" in token:
            userid = str(token["sub"])
        else:
            return {"Succuess": False,
                    "statuscode": 400,
                    "Notice": "Invalid Access Token!"}

        mycursor = mydb.cursor()
        mycursor.execute("SELECT petId, name, type, birthday, color FROM Pet Where userid ='" + userid + "'")
        col_names = [col[0] for col in mycursor.description]
        myresult = mycursor.fetchall()

        print(myresult)
        result_list = []
        for row in myresult:
            result_dict = {}
            for i in range(len(col_names)):
                result_dict[col_names[i]] = row[i]
            result_list.append(result_dict)

        result_json = json.dumps(result_list, default=handle_datetime)  # [1:-1]
        StrinJson = str(result_json)
        returnJson = json.loads(StrinJson)

        return returnJson
    else:
        raise HTTPException(status_code=422,
                            detail="At least one of the following request parameters is missing: 'access_token'")
