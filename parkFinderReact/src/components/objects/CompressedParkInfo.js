import './CompressedParkInfoStaff.css';
import './CompressedParkInfo.css';
import { Button } from '../interactive_items/Button';
import {ImageBlock} from '../interactive_items/ImageBlock';
import "rc-tooltip/assets/bootstrap.css";
import Tooltip from 'rc-tooltip';

function separateString(string) {
    let lines = string.split("\n");

    return(
        <ul>
            {lines.map((line, index) => (
                <li key={index}>{line}</li>
            ))}
        </ul>
    )
};


export function CompressedParkInfo({
    parque,
}) {
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
                    <label className='gray_label'>{parque["distancia"]}</label>
                </div>
                <Tooltip placement="top" overlay={
                    <div className='compressed_park_tooltip'> <b>Lugares Instantaneos Ocupados</b></div>
                } >
                    <div className={'compressed_park_spaces ' + ocupationColor()}>
                        <b> {parque["lugares_totais"] - parque["lugares_vagos"]}/{parque["lugares_totais"]} </b>
                    </div>
                </Tooltip>
            </div>
            <div className="compressed_park_image_info">
                <div className='compressed_park_block_image'>
                    <ImageBlock imageLink={parque["link_imagem"]}/>
                </div>
                <div className="compressed_park_info">
                    <label>Custo estimado: <b>{parque["custo"].toFixed(2)}€</b></label>
                    <label className='compressed_park_info_description'>{separateString(parque["descricao"])}</label>
                    <div className="compressed_park_buttons">
                        <Button buttonStyle="page_button see_details_button" onClick={() => localStorage.setItem("parqueId", parque["id"])} link={'/details'}>Ver detalhes</Button>
                        <Button buttonStyle="default">Reservar</Button>
                    </div>
                </div>
            </div>
        </div>
    );
};





export function CompressedParkInfoStaff({
    parque,
    editButton,
}) {
    function ocupationColor() {
        if ((parque["lugares_vagos"]/parque["lugares_totais"]) > 0.30) {
            return "green"
        } else if ((parque["lugares_vagos"]/parque["lugares_totais"]) > 0.10) {
            return "orange"
        }
        return "red"
    }
    
    return (
        <div className="compressed_park_staff">
            <div className='compressed_park_staff_block_image'>
                <ImageBlock imageSize='image_small' imageLink={parque["link_imagem"]}/>
            </div>
            <div className="compressed_park_staff_info">
                <div className="compressed_park_staff_header">
                    <h2>{parque["nome"]}</h2>
                    <Tooltip placement="top" overlay={
                        <div className='compressed_park_tooltip'> <b>Lugares Instantaneos Ocupados</b></div>
                    } >
                        <div className={'compressed_park_staff_spaces ' + ocupationColor()}>
                            <b> {parque["lugares_totais"] - parque["lugares_vagos"]}/{parque["lugares_totais"]} </b>
                        </div>
                    </Tooltip>
                </div>
                <div className="compressed_park_staff_content">
                    <div className='compressed_park_staff_street'>
                        <li>{parque['morada']}</li>
                    </div>
                    {editButton}
                </div>
            </div>
        </div>
    );
};