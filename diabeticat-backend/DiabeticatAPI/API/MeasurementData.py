import jwt
from fastapi import APIRouter, Request, HTTPException
from datetime import datetime
import json
import mysql.connector


measurementData_router = APIRouter()

secret_key: str = "ßf134gr08123r0r01r+312ag23ß9dscda4114gvf43dßkß431r"

def protected(token):
    try:
        var = jwt.decode(token, secret_key, algorithms="HS256")
        return var
    except jwt.exceptions.InvalidTokenError:
        raise HTTPException(status_code=401, detail="Invalid token")


@measurementData_router.post('/insertMeasurementData')
async def insertData(input: Request):
    global TokenUserId
    req = await input.json()
    mydb = connectDB()
    if "petid" in req and "bloodSugar" in req and "insulinDose" in req and "access_token" in req:
        if req["petid"] != 0 and req["bloodSugar"] != 0 and req["insulinDose"] != 0 and "access_token" != None:

            petId = req["petid"]

            bloodSugar = req["bloodSugar"]
            insulinDose = req["insulinDose"]

            mycursor = mydb.cursor()

            sqlGet = "SELECT Pet.userId FROM Pet WHERE petId='" + str(petId) + "'"
            mycursor.execute(sqlGet)

            if mycursor.with_rows:
                rows = mycursor.fetchall()

                if "access_token" in req:
                    print("Access Token found")
                    token = protected(req["access_token"])
                    if "sub" in token:
                        TokenUserId = str(token["sub"])
                    else:
                        raise HTTPException(status_code=404, detail="Access token is broken!")
                    print("Access Token ID:" + str(TokenUserId))
                    petUserId = rows[0][0]
                    print("PetUserId:" + str(petUserId))
                    print("result:" + str(int(TokenUserId) == int(petUserId)))
                    if int(TokenUserId) == int(petUserId):
                        print("TokenId = userId")
                        mycursor2 = mydb.cursor()
                        sql = "INSERT INTO MeasurementData (petId, bloodSugar, insulinDose) VALUES (%s, %s, %s)"
                        val = (petId, bloodSugar, insulinDose)
                        mycursor2.execute(sql, val)

                        mydb.commit()
                        print(mycursor.rowcount, "New MeasurementData inserted!")
                        return {"Succuess": True}
                else:
                    raise HTTPException(status_code=404, detail="Invalid Access token!")
            else:
                raise HTTPException(status_code=404, detail="Pet not found.")
        else:
            raise HTTPException(status_code=404, detail="One or more Key(s) was not found")
    else:
        raise HTTPException(status_code=404, detail="One or more Key(s) do not exist or was not found")




@measurementData_router.post('/getMeasurementDataByPet')
async def getDataByPet(input: Request):
    global userid
    req = await input.json()
    mydb = connectDB()

    if "access_token" in req:
        token = protected(req["access_token"])
        if "sub" in token:
            userid = token["sub"]
        else:
            raise HTTPException(status_code=404, detail="Access token is broken!")
    else:
        raise HTTPException(status_code=404, detail="Access token is missing!")

    if "petid" in req and userid != 0:
        if req["petid"] is not None:
            petid = req["petid"]
            mycursor = mydb.cursor()
            mycursor.execute("SELECT Pet.petId, measurementDataId, bloodSugar, insulinDose FROM MeasurementData JOIN Pet ON Pet.petId=MeasurementData.petId Where Pet.petId ='" + str(petid) + "' AND Pet.userId='" + str(userid) + "'")
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
            raise HTTPException(status_code=404, detail="Petid was not found!")
    else:
        raise HTTPException(status_code=404, detail="Key 'petid' does not exist or was not found or Access token is invalid!")



# Funktion zur Umwandlung von datetime-Objekten in Strings
def handle_datetime(obj):
    if isinstance(obj, datetime):
        return obj.isoformat()


def connectDB():
    mydb = mysql.connector.connect(
        host="sql7.freemysqlhosting.net",
        user="sql7614321",
        password="6QcZvqkwMb",
        database="sql7614321"
    )
    return mydb
