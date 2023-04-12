from fastapi import APIRouter, Request, HTTPException
from datetime import  datetime
import json
import mysql.connector


measurementData_router = APIRouter()

@measurementData_router.post('/insertMeasurementData')
async def insertData(input: Request):
    req = await input.json()
    mydb = connectDB()
    if "petid" in req and "bloodSugar" in req and "insulinDose" in req:
        if req["petid"] is not None and req["bloodSugar"] is not None and req["insulinDose"] is not None:

            petid  = req["petid"]
            bloodSugar = req["bloodSugar"]
            insulinDose = req["insulinDose"]

            mycursor = mydb.cursor()

            sql = "INSERT INTO MeasurementData (petId, bloodSugar, insulinDose) VALUES (%s, %s, %s)"
            val = (petid, bloodSugar, insulinDose)
            mycursor.execute(sql, val)

            mydb.commit()
            print(mycursor.rowcount, "New Pet inserted!")

            return {"Succuess": True}

        else:
            raise HTTPException(status_code=404, detail="petid was not found")
    else:
        raise HTTPException(status_code=404, detail="Key 'petid' does not exist or was not found")




@measurementData_router.post('/getMeasurementDataByPet')
async def getDataByPet(input: Request):
    req = await input.json()
    mydb= connectDB()
    if "petid" in req :
        if req["petid"] is not None:
            petid = str(req["petid"])
            mycursor = mydb.cursor()
            mycursor.execute("SELECT * FROM MeasurementData Where petId ='" + petid + "'")
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
            raise HTTPException(status_code=404, detail="petid was not found")
    else:
        raise HTTPException(status_code=404, detail="Key 'petid' does not exist or was not found")



# Funktion zur Umwandlung von datetime-Objekten in Strings
def handle_datetime(obj):
    if isinstance(obj, datetime):
        return obj.isoformat()


def connectDB():
    mydb = mysql.connector.connect(
        host="sql7.freemysqlhosting.net",
        user="sql7609007",
        password="xSVyvqUxhW",
        database="sql7609007"
    )
    return mydb
