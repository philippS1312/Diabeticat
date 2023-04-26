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
    if "petid" in req and "bloodSugar" in req and "insulinDose" in req:
        if req["petid"] is not None and req["bloodSugar"] is not None and req["insulinDose"] is not None:

            petid  = req["petid"]
            bloodSugar = req["bloodSugar"]
            insulinDose = req["insulinDose"]

            mycursor = mydb.cursor()
            sqlGet = "SELECT pet.userId FROM Pet WHERE petId='" + petid + "'"
            col_names = [col[0] for col in mycursor.description]
            myresult = mycursor.fetchall()
            PetUserId = myresult[0]

            if "access_token" in req:
                token = protected(req["access_token"])
                if "sub" in token:
                    TokenUserId = str(token["sub"])
                else:
                    raise HTTPException(status_code=404, detail="Access token is broken!")

            if TokenUserId == PetUserId:
                mycursor = mydb.cursor()
                sql = "INSERT INTO MeasurementData (petId, bloodSugar, insulinDose) VALUES (%s, %s, %s)"
                val = (petid, bloodSugar, insulinDose)
                mycursor.execute(sql, val)

                mydb.commit()
                print(mycursor.rowcount, "New Pet inserted!")

                return {"Succuess": True}
            else:
                raise HTTPException(status_code=404, detail="Invalid Access token!")
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

    if "petid" in req and userid != 0:
        if req["petid"] is not None:
            petid = str(req["petid"])
            mycursor = mydb.cursor()
            mycursor.execute("SELECT * FROM MeasurementData Where petId ='" + petid + "' AND pet.userId='" + userid + "'")
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
