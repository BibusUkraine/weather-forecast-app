// @ts-ignore
import React from 'react';
import Humidity from "../../../assets/Humidity.svg";

// type DetailedProps {
//     className? :
// }
const DetailedBlock = ({className}:any) => {
    return (
        <div className={`w-[45%] h-[48%] rounded-md bg-primary mb-4 mr-4 ${className}`}>
            <div className={'flex items-center p-2'}>
            <img src={Humidity} alt="" className={'w-[10%] mr-1 '}/>
                <p className={'text-sm'}>Humidity</p>
                
            </div>
            <div className={'flex h-full justify-center'}>
                <div className={'relative flex items-center justify-center w-[70%] h-[100px]'}>
                    <svg xmlns="http://www.w3.org/2000/svg"
                         viewBox="-1 -1 34 34"
                         className={'h-[100px] w-[100px] rotate-[-90deg] absolute'}>

                        <circle cx="16" cy="16" r="15.9155"
                                style={{fill:"none",stroke:"rgba(102,102,102,0.54)",strokeWidth:0.8,strokeDashoffset:100,strokeDasharray:"100 22",strokeLinecap:"round"}}/>

                        <circle cx="16" cy="16" r="15.9155"
                                style={{stroke:"#C2A022",strokeLinecap:"round",strokeDashoffset:100,strokeDasharray:"120 100",strokeWidth:1.8,fill:"none"}}/>
                    </svg>
                    <div className={'bg-secondary w-[80px] h-[80px] rounded-full absolute  flex items-center justify-center'}>
                        <p className={'text-2xl text-primary'}>50<span className={'text-sm'}>%</span></p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default DetailedBlock;