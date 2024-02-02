import axios from "axios";

export const getCurrentWeather = async (city,countryCode)=>{
    const res = await axios.get('http://weatherforecast.northeurope.cloudapp.azure.com:8080/api/weather/now?city='+city+'&countryCode='+countryCode)

    return res.data
  }