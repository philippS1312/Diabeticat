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
        return {"success": False,
                "statuscode": 400,
                "notice": "Invalid Access Token"}


@measurementData_router.post('/api/insertMeasurementData')
async def insertData(input: Request):
    global TokenUserId
    req = await input.json()
    mydb = connectDB()
    if "petid" in req and "bloodSugar" in req and "insulinDose" in req and "measureDate" and "access_token" in req:
        if req["petid"] != 0 and req["bloodSugar"] != 0 and req["insulinDose"] != 0 and req["measureDate"] is not None and \
                req["access_token"] is not None:

            petId = req["petid"]

            bloodSugar = req["bloodSugar"]
            insulinDose = req["insulinDose"]
            measureDate = req["measureDate"]

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
                        return {"Succuess": False,
                                "statuscode": 400,
                                "Notice": "Invalid Access Token!"}
                    print("Access Token ID:" + str(TokenUserId))
                    petUserId = rows[0][0]
                    print("PetUserId:" + str(petUserId))
                    print("result:" + str(int(TokenUserId) == int(petUserId)))
                    if int(TokenUserId) == int(petUserId):
                        print("TokenId = userId")
                        mycursor2 = mydb.cursor()
                        sql = "INSERT INTO MeasurementData (petId, bloodSugar, insulinDose, measureDate) VALUES (%s, %s, %s, %s)"
                        val = (petId, bloodSugar, insulinDose, measureDate)
                        mycursor2.execute(sql, val)

                        mydb.commit()
                        print(mycursor.rowcount, "New MeasurementData inserted!")
                        return {"Succuess": True}
                else:
                    raise HTTPException(status_code=422, detail="At least one of the following request parameters is missing: 'access_token'")
            else:
                return {"Succuess": False,
                        "statuscode": 400,
                        "Notice": "A pet with this petId does not exist."}
        else:
            raise HTTPException(status_code=422, detail="At least one of the following request parameters values is null: 'access_token', 'petId', 'bloodSugar', 'insulineDose'")
    else:
        raise HTTPException(status_code=422, detail="At least one of the following request parameters is missing: 'access_token', 'petId', 'bloodSugar', 'insulineDose'")




@measurementData_router.post('/api/getMeasurementDataByPet')
async def getDataByPet(input: Request):
    global userid
    req = await input.json()
    mydb = connectDB()

    if "access_token" in req:
        token = protected(req["access_token"])
        if "sub" in token:
            userid = token["sub"]
        else:
            return {"Succuess": False,
                    "statuscode": 400,
                    "Notice": "Invalid Access Token!"}
    else:
        raise HTTPException(status_code=422, detail="At least one of the following request parameters is missing: 'access_token'")

    if "petid" in req:
        if req["petid"] is not None:
            petid = req["petid"]
            mycursor = mydb.cursor()
            mycursor.execute("SELECT Pet.petId, measurementDataId, bloodSugar, insulinDose, measureDate FROM MeasurementData JOIN Pet ON Pet.petId=MeasurementData.petId Where Pet.petId ='" + str(petid) + "' AND Pet.userId='" + str(userid) + "'")
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
            raise HTTPException(status_code=422, detail="At least one of the following request parameters values is null: 'petId'")
    else:
        raise HTTPException(status_code=422, detail="At least one of the following request parameters is missing: 'petId'")



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
