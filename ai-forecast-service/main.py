"""
main config
"""

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from wetherModel import model, WetherModel

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/get-response/")
def getAiResponse(location: str):
	"""
	Use text ai model
	"""
	return {"data": model("./wether.csv", location=location)}
