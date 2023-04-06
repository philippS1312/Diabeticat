from fastapi import APIRouter

bye_router = APIRouter()
@bye_router.get('/bye')
def bye():
    return {"Schönen":"Tag Noch"}
