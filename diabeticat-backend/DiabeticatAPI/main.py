from fastapi import FastAPI
from API.User import user_router
from API.Pet import pet_router
from API.MeasurementData import measurementData_router
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

origins = [
    "http://localhost",
    "http://localhost:8080",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

app.include_router(user_router)
app.include_router(pet_router)
app.include_router(measurementData_router)



