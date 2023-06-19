import './CompressedParkInfo.css';
import { Button } from '../interactive_items/Button';
import {ImageBlock} from '../interactive_items/ImageBlock';


function ManagerCompressedStatInfo({
    stat
}) {
    
    return (
        <div className="compressed_park">
            <div className="compressed_park_header">
                <div className="compressed_park_title">
                    <h2>{stat.nome}</h2>
                </div>
            </div>
            <div className="compressed_park_info">
                <label >Stat1: {stat.nr}</label>
            </div>
        </div>
    );
}

export default ManagerCompressedStatInfo;