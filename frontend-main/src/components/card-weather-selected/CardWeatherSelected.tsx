// @ts-ignore
import React, {useEffect,useState} from 'react';
import DetailedBlock from "../detailed-block/DetailedBlock";
import Sun from '../../../assets/Sunny.svg';
import Wind from '../../../assets/Wind.svg';
import Humidity from '../../../assets/Humidity.svg';
import Termometer from '../../../assets/Termometer.svg';
import styles from './CardWeatherSelected.module.css';
import Rain from "../../../assets/Rain.svg";
import Snow from "../../../assets/Snow.svg";
import Fog from "../../../assets/Fog.svg";
import Clouds from "../../../assets/Clouds.svg";
import SunAndClouds from "../../../assets/SunAndClouds.svg";

type SelectedCountry = {
    selectedCounrty:{
        city:string,
        country:string,
        label: string,
        lat: number,
        lng: number,
        value: number,
        countryCode: string,
    }
}
const CardWeatherSelected = ({selectedCounrty}:SelectedCountry) => {
    const [icon,setIcon] = useState('')

    useEffect(() => {
        switch (selectedCounrty.weatherCondition) {
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
            case 'Clouds':
                setIcon(Clouds);
                break;
            case 'SunAndClouds':
                setIcon(SunAndClouds);
                break;
            default:
                setIcon('');
        }
    }, [selectedCounrty.weatherCondition]);


    return (
        <div className={'w-[90%]  justify-between h-[15%] min-h-[200px] flex'}>
            <div className={'w-[65%] flex-col rounded-xl bg-primary md:w-full'}>
                <div className={'w-full flex sm:h-[70%]'}>
                    <div className={`${styles.blockFirst} flex items-center`}>
                        <img src={icon} alt="" className={'w-[80%] ml-5'}/>
                    </div>
                    <div className={`${styles.blockSecond}`}>
                        <div className={'h-[70%] w-[70%] md:w-[85%]'}>
                            <div className={'h-1/2 flex items-center sm:flex-col'}>
                                <p className={'text-8xl mr-4 md:text-6xl'}>{selectedCounrty.temperature}°</p>
                                <p className={'text-4xl md:text-xl'}>{selectedCounrty?.modifiedData?.label || selectedCounrty?.modifiedData?.city},
                                    {selectedCounrty?.modifiedData?.countryCode}</p>
                            </div>
                            <div className={'h-1/2 flex items-end justify-between sm:hidden'}>
                                <div className={'flex items-center '}>
                                    <img src={Wind} alt="" className={'w-[25px] mr-1 '}/>
                                    <p className={'text-sm'}>{selectedCounrty.windSpeed} km/h</p>
                                </div>
                                <div className={'flex items-center '}>
                                    <img src={Humidity} alt="" className={'w-[25px] mr-1 '}/>
                                    <p className={'text-sm'}>{selectedCounrty.humidity}%</p>
                                </div>
                                <div className={'flex items-center'}>
                                    <img src={Termometer} alt="" className={'h-[25px] mr-1 '}/>
                                    <p className={'text-sm'}>{selectedCounrty.feelsLike}°</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className={'h-[20%] hidden items-end justify-center sm:flex'}>
                    <div className={'w-[90%] flex justify-between'}>
                        <div className={'flex items-center '}>
                            <img src={Wind} alt="" className={'w-[25px] mr-1 '}/>
                            <p className={'text-sm'}>11 km/h</p>
                        </div>
                        <div className={'flex items-center '}>
                            <img src={Humidity} alt="" className={'w-[25px] mr-1 '}/>
                            <p className={'text-sm'}>2%</p>
                        </div>
                        <div className={'flex items-center'}>
                            <img src={Termometer} alt="" className={'h-[25px] mr-1 '}/>
                            <p className={'text-sm'}>29°</p>
                        </div>
                    </div>
                </div>
            </div>
            <div className={`${styles.blockThird} ml-5 flex flex-wrap justify-between md:hidden`}>

                <DetailedBlock/>
                <DetailedBlock/>
                <DetailedBlock/>
            </div>
        </div>
    );
};

export default CardWeatherSelected;