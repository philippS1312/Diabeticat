from fastapi import APIRouter

hello_router = APIRouter()
@hello_router.get('/hello')
def hello():
    return {"Ping":"Pong"}
