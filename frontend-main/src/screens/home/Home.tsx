// @ts-ignore
import React, {useState, useEffect} from 'react';
import axios from 'axios';
import CardWeatherSelected from "../../components/card-weather-selected/CardWeatherSelected";
import Graph from "../../components/graph/Graph";
import CardWeather from "../../components/card-weather/CardWeather";
import Clock from '../../../assets/Clock.svg';
import Calendar from '../../../assets/Calendar.svg';
//@ts-ignore
import Slider from 'react-slick';


import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import GlobeComponent from "../../components/globe/Globe";
import DetailedBlock from "../../components/detailed-block/DetailedBlock";
import {getCurrentWeather} from "../../services/WeaklyWeather";


export const Home = () => {
    // const [loading, setLoading] = useState(true)
    const [windowWidth, setWindowWidth] = useState<number>(0)
    const [selectedCounrty, setSelectedCountry] = useState('')
    // @ts-ignore
    const [weatherWeakly, setWeatherWeakly] = useState([
        {
            id: 1,
            temperature: 28,
            day: 'MON',
            humidity: 2,
            wind: 11,
            icon: 'Sunny'
        },
        {
            id: 2,
            temperature: 28,
            day: 'TUE',
            humidity: 2,
            wind: 11,
            icon: 'Sunny'
        },
        {
            id: 3,
            day: 'WED',
            temperature: 28,
            humidity: 2,
            wind: 11,
            icon: 'Sunny'
        },
        {
            id: 4,
            day: 'THU',
            temperature: 28,
            humidity: 2,
            wind: 11,
            icon: 'Sunny'
        },
        {
            id: 5,
            day: 'FRI',
            temperature: 28,
            humidity: 2,
            wind: 11,
            icon: 'Sunny'
        },
        {
            id: 6,
            day: 'SAT',
            temperature: 28,
            humidity: 2,
            wind: 11,
            icon: 'Sunny'
        },
        {
            id: 7,
            day: 'SUN',
            temperature: 28,
            humidity: 2,
            wind: 11,
            icon: 'Sunny'
        },
    ])
    // @ts-ignore
    const [weatherHourly, setWeatherHourly] = useState([
        {
            id: 1,
            temperature: 28,
            time: 'NOW',
            humidity: 2,
            icon: 'Sunny'
        },
        {
            id: 2,
            temperature: 28,
            time: '13:00',
            humidity: 2,
            icon: 'SunAndClouds'
        },
        {
            id: 3,
            temperature: 28,
            time: '14:00',
            humidity: 2,
            icon: 'Sunny'
        },
        {
            id: 4,
            temperature: 22,
            time: '15:00',
            humidity: 2,
            icon: 'Light Rain'
        },
        {
            id: 5,
            temperature: 22,
            time: '16:00',
            humidity: 2,
            icon: 'Snow'
        },
        {
            id: 6,
            temperature: 23,
            time: '17:00',
            humidity: 2,
            icon: 'Light Rain'
        },
        {
            id: 7,
            temperature: 26,
            time: '18:00',
            humidity: 2,
            icon: 'Snow'
        },
        {
            id: 8,
            temperature: 27,
            time: '19:00',
            humidity: 2,
            icon: 'Light Rain'
        },
        {
            id: 9,
            temperature: 27,
            time: '20:00',
            humidity: 2,
            icon: 'Snow'
        },
        {
            id: 10,
            temperature: 22,
            time: '21:00',
            humidity: 2,
            icon: 'Snow'
        },
        {
            id: 11,
            temperature: 22,
            time: '22:00',
            humidity: 2,
            icon: 'Snow'
        },
        {
            id: 12,
            temperature: 24,
            time: '23:00',
            humidity: 2,
            icon: 'Snow'
        },
    ])

    useEffect(() => {
        const getUsersLocation = async () => {
            try {
                const res = await axios.get('https://ipinfo.io?token=3391acfea9c986');
                setSelectedCountry(res.data);
            } catch (err) {
                console.log(err);
            }
        };
        getUsersLocation();
    }, []);

    useEffect(() => {
        if (selectedCounrty) {
            const modifiedData = {
                city: selectedCounrty.city || (selectedCounrty?.label ? selectedCounrty.label.split(',')[0] : ''),
                countryCode: selectedCounrty.country || selectedCounrty.countryCode
            }
            console.log(modifiedData)
            getCurrentWeather(modifiedData.city, modifiedData.countryCode).then(res => {
                console.log({
                    ...res,
                    modifiedData
                })
                setSelectedCountry({
                    ...res,
                    modifiedData
                });
            });
        }
    }, [selectedCounrty])

    useEffect(() => {
        setWindowWidth(window.innerWidth)
    }, [window.innerWidth])

    const settings = {
        dots: false,
        infinite: true,
        speed: 500,
        slidesToShow: windowWidth < 1000 ? 6 : 10,
        slidesToScroll: windowWidth < 1000 ? 6 : 10,
        nextArrow: windowWidth < 1000 ? <></> : null,
        prevArrow: windowWidth < 1000 ? <></> : null,
    };

    return (
        <div className={'flex  flex-col justify-center w-full h-full'}>
            <section className={'flex justify-center items-center w-full h-full'}>
                <CardWeatherSelected selectedCounrty={selectedCounrty}/>
            </section>
            <div className={'flex flex-col h-full w-full md:flex-col-reverse'}>
                <section className={'flex justify-center md:overflow-hidden'}>
                    <div className={'w-[90%] h-1/2 flex justify-between md:flex-col items-center'}>
                        <div className={'w-[75%] flex justify-center md:my-5'}>
                            <GlobeComponent setSelectedCountry={setSelectedCountry}/>
                        </div>
                        <div className={`md:flex ml-5 justify-center w-full h-auto lg:hidden `}>
                            <DetailedBlock className={'h-[60%] min-h-[175px] w-[25%]'}/>
                            <DetailedBlock className={'h-[60%] min-h-[175px] w-[25%]'}/>
                            <DetailedBlock className={'h-[60%] min-h-[175px] w-[25%]'}/>
                        </div>
                        <div className={'w-[25%] mt-5 md:w-[100%]'}>
                            <h1 className={'flex mt-5'}><img src={Calendar} className={'mr-2'}/>Weekly controll</h1>
                            <Graph/>
                        </div>
                    </div>
                </section>
                <section className={'flex justify-center'}>
                    <div className={'w-[90%] flex justify-between'}>
                        <div className={'w-[100%] md:my-5'}>
                            <h1 className={'flex mt-5'}><img src={Clock} className={'mr-2'}/>Hourly forecast</h1>
                            <Slider {...settings}>
                                {weatherHourly.map((item) =>
                                    <CardWeather key={item.id} weather={item}/>
                                )
                                }
                            </Slider>
                            <h1 className={'flex mt-5'}><img src={Clock} className={'mr-2'}/>Weakly forecast</h1>
                            <Slider {...settings}>
                                {weatherWeakly.map((item) =>
                                    <CardWeather key={item.id} weather={item}/>
                                )
                                }
                            </Slider>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    )

};
