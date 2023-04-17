from fastapi import APIRouter, Request, HTTPException
import mysql.connector
import json

from API.MeasurementData import handle_datetime

pet_router = APIRouter()

def connectDB():
    mydb = mysql.connector.connect(
        host="sql7.freemysqlhosting.net",
        user="sql7609007",
        password="xSVyvqUxhW",
        database="sql7609007"
    )
    return mydb

@pet_router.post("/createPet")
async def createPet(pet: Request):
    req_pet = await pet.json()
    name = req_pet["name"]
    type = req_pet["type"]
    birthday = req_pet["birthday"]
    userid = req_pet["userId"]

    mydb=connectDB()
    mycursor = mydb.cursor()

    sql = "INSERT INTO Pet (name, type, birthday, userid) VALUES (%s, %s, %s, %s)"
    val = (name, type, birthday, userid)
    mycursor.execute(sql,val)

    mydb.commit()
    print(mycursor.rowcount, "New Pet inserted!")

    return {"Succuess":True}

@pet_router.post("/deletePet")
async def deletePet(pet: Request):
    req_pet = await pet.json()
    petid = str(req_pet["id"])

    mydb=connectDB()
    mycursor = mydb.cursor()

    sql = "DELETE FROM Pet WHERE petId='" + petid + "'"
    mycursor.execute(sql)

    mydb.commit()
    print("Pet sucessfully deleted!")

    return {"Succuess":True}

@pet_router.post('/getPetsByUser')
async def getPetsByUser(input: Request):
    req = await input.json()
    mydb= connectDB()
    if "userid" in req :
        if req["userid"] is not None:
            userid = str(req["userid"])
            mycursor = mydb.cursor()
            mycursor.execute("SELECT * FROM Pet Where userid ='" + userid + "'")
            col_names = [col[0] for col in mycursor.description]
            myresult = mycursor.fetchall()

            result_list = []
            for row in myresult:
                result_dict = {}
                for i in range(len(col_names)):
                    result_dict[col_names[i]] = row[i]
                result_list.append(result_dict)

            result_json = json.dumps(result_list, default=handle_datetime) #[1:-1]
            StrinJson = str(result_json)
            returnJson = json.loads(StrinJson)

            return returnJson
        else:
            raise HTTPException(status_code=404, detail="userid was not found")
    else:
        raise HTTPException(status_code=404, detail="Key 'userid' does not exist or was not found")