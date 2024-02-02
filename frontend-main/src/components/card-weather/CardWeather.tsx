// @ts-ignore
import React, {useState,useEffect} from 'react';
import './CardWeather.module.css';
import Sun from '../../../assets/Sun.svg';
import Clouds from '../../../assets/Clouds.svg';
import Rain from '../../../assets/Rain.svg';
import Snow from '../../../assets/Snow.svg';
import Fog from '../../../assets/Fog.svg';
import SunAndClouds from '../../../assets/SunAndClouds.svg';


type WeatherHourly = {
    weather: {
        id: number,
        temperature: number,
        time?: string,
        humidity: number,
        icon: string
        day?: string
    }

}

const CardWeather = ({weather}: WeatherHourly) => {
  const [icon,setIcon] = useState('')

    useEffect(() => {
        switch (weather.icon) {
            case 'Sunny':
                setIcon(Sun);
                break;
            case 'Light Rain':
                setIcon(Rain);
                break;
            case 'Snow':
                setIcon(Snow);
                break;
            case 'Fog':
                setIcon(Fog);
                break;
            case 'Cloudy':
                setIcon(Clouds);
                break;
            case 'SunAndClouds':
                setIcon(SunAndClouds);
                break;
            default:
                setIcon(SunAndClouds);
        }
    }, [weather.icon]);


    return (
        <div className={`bg-card w-[10%]  h-[60%] rounded mt-4  rounded min-w-[70px]`}>
            <div className={'h-1/3 flex items-center justify-center'}>
                <img src={icon} alt={weather.icon}
                     className={`w-[50%] mt-5 ${weather.time === 'NOW' ? 'text-primary' : ''}`}/>
            </div>
            <div className={'h-2/3 flex items-center justify-center flex-col'}>
                <p className={`${weather.time === 'NOW' ? 'text-primary' : ''}`}>{weather.time || weather.day}</p>
                <p  className={`${weather.time === 'NOW' ? 'text-primary' : ''}`}>{weather.temperature}°</p>
            </div>
        </div>
    );
};

export default CardWeather;