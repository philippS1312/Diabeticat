from fastapi import FastAPI
from API.User import user_router
from API.Pet import pet_router

app = FastAPI()

app.include_router(user_router)
app.include_router(pet_router)



