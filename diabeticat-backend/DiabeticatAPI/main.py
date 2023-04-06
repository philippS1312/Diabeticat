from fastapi import FastAPI
from API.User import hello_router
from API.Pet import pet_router

app = FastAPI()

app.include_router(hello_router)
app.include_router(pet_router)



