import {
    Chart as ChartJS,
    LineElement,
    PointElement,
    LineController,
    LinearScale,
    CategoryScale,
    Tooltip,
    Legend,
} from "chart.js";
import { Line } from 'react-chartjs-2';
import ChartDataLabels from 'chartjs-plugin-datalabels';



ChartJS.register(
    ChartDataLabels,
    LineElement,
    PointElement,
    LineController,
    LinearScale,
    CategoryScale,
    Tooltip,
    Legend,
)
const Graph = () => {
    const data = {
        labels: ['SN', 'MN', 'TU', 'WD', 'TH', 'FR', 'ST'],
        datasets: [
            {
                data: [22, 28, 30, 21, 20, 24, 25],
                fill: false,
                backgroundColor: '#fff',
               borderColor: 'rgba(255,255,255,0.63)',
                pointBorderWidth: 4,
                datalabels: {
                    color: '#fff',
                }

            }
        ]
    };

    const maxDataValue = Math.max(...data.datasets[0].data);

   const options= {
       plugins: {
           legend: {
               display: false
           },
           datalabels: {
               anchor: 'end',
               align: 'top',
               formatter: Math.round,
               font: {
                   weight: 'bold'
               }
           }
       },
       scales: {
           y: {
               ticks: {
                   display: false
               },
               grid: {
                   display: false,
                   drawBorder: false
               },
               max: maxDataValue + 5,
           }
       }
   };





    return (
        <div className={'w-full h-[80%] flex items-center justify-center mt-14 md:my-14'}>
            <Line
                data={data}
                // @ts-ignore
                options={options}
            />
        </div>
    );
};

export default Graph;
