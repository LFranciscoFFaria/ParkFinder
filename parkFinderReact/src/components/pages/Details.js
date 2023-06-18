import './Details.css'
import Filter from '../objects/Filter';
import {ImageBlock} from '../interactive_items/ImageBlock';
import { Button } from '../interactive_items/Button';
import '../interactive_items/select.css'


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

const bool=true;

function Details({
    parque,
    filter,
    setState
}) {

    function Characteristics() {
        document.getElementById("characteristics").style.display = "flex";
        document.getElementById("description").style.display = "none";

        document.getElementById("charButton").classList.add("desc_button_hover");
        document.getElementById("descrButton").classList.remove("desc_button_hover");
    }

    function Description() {
        document.getElementById("characteristics").style.display = "none";
        document.getElementById("description").style.display = "flex";

        document.getElementById("charButton").classList.remove("desc_button_hover");
        document.getElementById("descrButton").classList.add("desc_button_hover");
    }
    
    return (
        <>
        <div className="content_front_page">
            <main className="details">
                <div className="park_fp_header">
                    <h1>{parque.nome}</h1>
                </div>

                
                <div className="options_desc">
                    <button className="desc_button desc_button_hover" id="charButton"
                        onClick={()=>{Characteristics()}}>Characteristics</button>
                    <button className="desc_button" id="descrButton"
                        onClick={()=>{Description()}}>Description</button>
                </div>

                <div className="desc_desc_none" id="description">
                    <div className="image image_desc">
                        <ImageBlock imageLink={parque.link_imagem}/>
                    </div>
                    <label><b>Information</b></label>
                    <label className='compressed_park_info_description'>{separateString(parque.descricao)}</label>
                </div>

                <div className="desc_desc" id="characteristics">
                    <label><b>Availability</b></label>
                    <ul>
                        <li> Nº de lugares vagos: {parque.lugares_vagos} </li>
                        <li> Nº de lugares total: {parque.lugares_totais} </li>
                    </ul>        
                    <label><b>Opening hours</b></label>
                    <ul>
                        <li> From {parque.hora_init} to {parque.hora_end} everyday. </li>
                    </ul>
                    <label><b>Address</b></label>
                    <ul>
                        <li> {parque.morada} ({parque.distancia} de si) </li>
                    </ul>
                </div>
            </main>
            <div className="filter_box">
                <div className="filter_header">
                    <h2>Booking</h2>
                    <div className='filter_header_buttons'>
                        <div className='deactivate'> <Button buttonStyle="default">Book</Button> </div>
                    </div>
                </div>
                <div className="filter_dates_display">
                    <div className='filter_dates_block'>
                        Begin date:
                        <div className='filter_dates_fields'>
                            <input className='filter_date_input_field' id='date_begin' type='date' />
                            <input className='filter_date_input_field' id='time_begin' type='time' />
                        </div>
                    </div>
                    <div className='filter_dates_block'>
                        End date:
                        <div className='filter_dates_fields'>
                            <input className='filter_date_input_field' id='date_end' type='date' />
                            <input className='filter_date_input_field' id='time_end' type='time' />
                        </div>
                    </div>
                </div>

            </div>
        </div>
        </>
    );
}

export default Details;