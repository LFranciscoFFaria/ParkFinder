import './CompressedParkInfo.css';
import { Button } from '../interactive_items/Button';
import {ImageBlock} from '../interactive_items/ImageBlock';

function separateString(string) {
    let lines = string.split("\n");

    return(
        <ul>
            {lines.map((line, index) => (
                <li key={index}>{line}</li>
            ))}
        </ul>
    )
}


function ManagerCompressedAdminInfo({
    admin,
    setIdAdmin
}) {
    
    return (
        <div className="compressed_park">
            <div className="compressed_park_header">
                <div className="compressed_park_title">
                    <h2>{admin.nome}</h2>
                </div>
            </div>
            <div className="compressed_park_info">
                <label className='compressed_park_info_description'>{separateString(admin.parques)}</label>
                <div className="compressed_park_buttons">
                    <Button buttonStyle="default" onClick={() => setIdAdmin(admin.id)} link={'/admin/edit_park'}>Edit</Button>
                </div>
            </div>
        </div>
    );
}

export default ManagerCompressedAdminInfo;