from fastapi import APIRouter, Request
import mysql.connector

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
    userId = req_pet["userId"]

    mydb=connectDB()
    mycursor = mydb.cursor()

    sql = "INSERT INTO Pet (name, type, birthday, userId) VALUES (%s, %s, %s, %s)"
    val = (name, type, birthday, userId)
    mycursor.execute(sql,val)

    mydb.commit()
    print(mycursor.rowcount, "New Pet inserted!")

    return {"Succuess":True}

@pet_router.post("/deletePet")
async def deletePet(pet: Request):
    req_pet = await pet.json()
    petId = req_pet["id"]

    mydb=connectDB()
    mycursor = mydb.cursor()

    sql = "DELETE FROM Pet WHERE petId='" + petId + "'"
    mycursor.execute(sql)

    mydb.commit()
    print("Pet sucessfully deleted!")

    return {"Succuess":True}