"""
Model config
"""

from pydantic import BaseModel
import pandas as pd
from sklearn.linear_model import LinearRegression
from random import uniform


def model(data: str, location: str):
	df = pd.read_csv(data)

	locations = pd.read_csv("./wether.csv").columns[1:].tolist()

	if location not in locations: return "Not found"

	country_data = df[['Date', location]]

	X = country_data['Date'].apply(lambda x: int(x.split('-')[2])).values.reshape(-1, 1)
	y = country_data[location].values

	model = LinearRegression()
	model.fit(X, y)

	next_day = int(df['Date'].iloc[-1].split('-')[2]) + 1
	next_day_weather = model.predict([[next_day]])

	return [round(next_day_weather[0], 2), round(uniform(round(next_day_weather[0], 2), uniform(next_day_weather[0], 2) + 2), 2), round(uniform(round(next_day_weather[0], 2), round(next_day_weather[0], 2) - 3), 2), round(uniform(round(next_day_weather[0], 2), uniform(next_day_weather[0], 2) + 4), 2), round(uniform(round(next_day_weather[0], 2), round(next_day_weather[0], 2) + 1), 2), round(uniform(round(next_day_weather[0], 0), uniform(next_day_weather[0], 2) + 1), 2), round(uniform(round(next_day_weather[0], 0) + 0.5, uniform(next_day_weather[0], 2) + 2), 2)]


class WetherModel(BaseModel):
	"""
	Base model
	"""
	navigation: str

if __name__ == "__main__":
	print(model('./wether.csv', 'AAA'))
