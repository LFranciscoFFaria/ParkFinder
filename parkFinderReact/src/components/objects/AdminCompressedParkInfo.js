import './CompressedParkInfo.css';
import { Button } from '../interactive_items/Button';
import {ImageBlock} from '../interactive_items/ImageBlock';
import {PopUp} from '../interactive_items/PopUp';
import { useState } from 'react';


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


function AdminCompressedParkInfo({
    parque
}) {
    const [popUp, setPopUp] = useState(false);


    function ocupationColor() {
        if ((parque["lugares_vagos"]/parque["lugares_totais"]) > 0.30) {
            return "green"
        } else if ((parque["lugares_vagos"]/parque["lugares_totais"]) > 0.10) {
            return "orange"
        }
        return "red"
    }
    
    return (
        <div className="compressed_park">
            <div className="compressed_park_header">
                <div className="compressed_park_title">
                    <h2>{parque["nome"]}</h2>
                </div>
                <div className={'compressed_park_spaces ' + ocupationColor()}>
                    <b> {parque["lugares_totais"] - parque["lugares_vagos"]}/{parque["lugares_totais"]} </b>
                </div>
            </div>
            <div className="compressed_park_image_info">
                <div className='compressed_park_block_image'>
                    <ImageBlock imageLink={parque["link_imagem"]}/>
                </div>
                <div className="compressed_park_info">
                    <label>Estimated cost: <b>{parque["custo"].toFixed(2)}€</b></label>
                    <label className='compressed_park_info_description'>{separateString(parque["descricao"])}</label>
                    <div className="compressed_park_buttons">
                        <Button buttonStyle="default" onClick={() => localStorage.setItem("parqueId", parque["id"])} link={'/admin/details'}>Edit</Button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AdminCompressedParkInfo;