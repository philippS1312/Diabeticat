from fastapi import Request, FastAPI
import mysql.connector

app = FastAPI()


def connectDB():
    mydb = mysql.connector.connect(
        host="sql7.freemysqlhosting.net",
        user="sql7609007",
        password="xSVyvqUxhW",
        database="sql7609007"
    )
    return mydb


@app.get("/")
async def root():




    for x in myresult:
        print(x)
    return {"message": x}


@app.post("/test")
async def get_body(over: Request):
    req_info = await over.json()
    print(req_info["Test"])
    return {"Status":"Okay"}

@app.post("/getUserByName")
async def getUserByName(over: Request):
    mydb=connectDB()
    mycursor = mydb.cursor()
    mycursor.execute("SELECT * FROM User where username ='EinUser1'")
    myresult = mycursor.fetchall()


@app.post("/persistUser")
async def persistUser(user: Request):
    req_user = await user.json()
    email=req_user["email"]
    password=req_user["password"]
    username=req_user["username"]

    mydb=connectDB()
    mycursor = mydb.cursor()

    sql = "INSERT INTO User (email, password, username) VALUES (%s, %s, %s)"
    val = (email, password,username)
    mycursor.execute(sql,val)

    mydb.commit()
    print(mycursor.rowcount, "record inserted.")

    return {"Succuess":True}






@app.get("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}



