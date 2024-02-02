import Globe from 'react-globe.gl';
import GlobeTexture from '../../../assets/GlobeTexture.jpeg';
import { pointsData } from '../../globe-point-data/GlobePointData';
import {SelectedCountry} from '../../components/card-weather-selected/CardWeatherSelected';


type Props = {
    setSelectedCountry: (country: SelectedCountry) => void;
};

const GlobeComponent = ({setSelectedCountry}:Props) => {

  const  onSelectCountry = (country:SelectedCountry) => {
      setSelectedCountry(country)
  }

    return (
        <Globe
            globeImageUrl={GlobeTexture}
            pointsData={pointsData}
            pointColor={() => 'red'}
            pointLabel={(point) => `${point.label}`}
            pointRadius={1}
            pointAltitude={0}
            width={600}
            height={600}
            backgroundColor={'#000'}
            pointResolution={10}
            onPointClick={onSelectCountry}
        />
    );
};

export default GlobeComponent;
