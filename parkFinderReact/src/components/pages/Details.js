import './Details.css'
import Navbar from '../objects/Navbar';
import { Button } from '../interactive_items/Button';
import '../interactive_items/select.css'
import '../objects/CompressedParkInfo.css'
import { ImageBlock } from '../interactive_items/ImageBlock';
import { useEffect } from 'react';


const parques = [
    {
        id: 0,
        nome: "PARQUE VISCONDE DO RAIO",
        morada: "rua dos reis",
        distancia: "(797 m)",
        lugares_vagos: 45,
        lugares_totais: 96,
        link_imagem: "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        custo: 1.30,
        hora_init:"8:00",
        hora_end:"19:00",
        descricao: "Public covered parking\n7 min. walk from the heart of the city\nAccessible from Monday to Friday from 8:00 am to 8:00 pm and Saturdays from 10:00 am to 8:00 pm.",
    },
    {
        id: 1,
        nome: "B&B BRAGA LAMAÇÃES",
        morada: "rua dos reis",
        distancia: "(2.7 km)",
        lugares_vagos: 22,
        lugares_totais: 51,
        link_imagem: "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        custo: 7.00,
        hora_init:"8:00",
        hora_end:"19:00",
        descricao: "Covered Hotel Parking\n10 min. from University of Minho\ntaxi service Accessible 24/7",
    },
    {
        id: 2,
        nome: "BRAGA PARQUE",
        morada: "rua dos reis",
        distancia: "(1.1km)",
        lugares_vagos: 186,
        lugares_totais: 268,
        link_imagem: "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        custo: 2.15,
        hora_init:"8:00",
        hora_end:"19:00",
        descricao: "Public covered Parking\nUnder the citizen's house from Braga\nAccessible 24/7",
    },
]

const horas = {
    "periodos" : [
        {"dia": 2, "inicio" : 7.5, "fim" : 13},
        {"dia": 3, "inicio" : 14.5, "fim" : 21},
        {"dia": 1, "inicio" : 7.5, "fim" : 13},
        {"dia": 5, "inicio" : 14.5, "fim" : 23},
        {"dia": 5, "inicio" : 7.5, "fim" : 13},
        {"dia": 2, "inicio" : 14.5, "fim" : 21},
        {"dia": 4, "inicio" : 7.5, "fim" : 13},
        {"dia": 3, "inicio" : 7.5, "fim" : 13},
        {"dia": 6, "inicio" : 8, "fim" : 13},
        {"dia": 4, "inicio" : 14.5, "fim" : 21},
        {"dia": 1, "inicio" : 14.5, "fim" : 21},
    ]
}


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

    let horas2 = [
        {"dia": 1, "horas" : []},
        {"dia": 2, "horas" : []},
        {"dia": 3, "horas" : []},
        {"dia": 4, "horas" : []},
        {"dia": 5, "horas" : []},
        {"dia": 6, "horas" : []},
        {"dia": 7, "horas" : []},
    ];
    
    if (parque)
        localStorage.setItem('parqueId', parque.id);
    else
        parque = parques[localStorage.getItem('parqueId')];


    function getOpeningHours() {

        horas['periodos'].map((horario) => {
            horas2[horario['dia']-1]['horas'].push(horario['inicio']);
            horas2[horario['dia']-1]['horas'].push(horario['fim']);
            horas2[horario['dia']-1]['horas'].sort();
        })
        console.log(horas2);
    }




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

    
    function ocupationColor() {
        if ((parque.lugares_vagos/parque.lugares_totais) > 0.30) {
            return "green"
        } else if ((parque.lugares_vagos/parque.lugares_totais) > 0.10) {
            return "orange"
        }
        return "red"
    }
    
    return (
        <div className='front_page'>
            <Navbar setState={setState} setFilter={null}/>
            <div className="front_page_content">
                <div className="details">
                    <div className="details_header">
                        <h1>{parque.nome}</h1>
                        <div className={'compressed_park_spaces ' + ocupationColor()}>
                            <b> {parque.lugares_totais - parque.lugares_vagos}/{parque.lugares_totais} </b>
                        </div>
                    </div>
                    <label>{parque.morada}</label>

                    <div className="image image_desc">
                        <ImageBlock imageLink={parque.link_imagem}/>
                    </div>
                    <div className="options_desc">
                        <button className="desc_button desc_button_hover" id="charButton"
                            onClick={()=>{Characteristics()}}>Characteristics</button>
                        <button className="desc_button" id="descrButton"
                            onClick={()=>{Description()}}>Description</button>
                    </div>

                    <div className="desc_desc_none" id="description">
                        <b>Informação</b>
                        <label className='compressed_park_info_description'>{separateString(parque.descricao)}</label>
                        <b>Morada</b>
                        <ul>
                            <li> {parque.morada} ({parque.distancia} de si) </li>
                        </ul>
                    </div>

                    <div className="desc_desc" id="characteristics">
                        <b></b>
                        <ul>
                            <li> Nº de lugares vagos: {parque.lugares_vagos} </li>
                            <li> Nº de lugares total: {parque.lugares_totais} </li>
                        </ul>        
                        <b>Horário de funcionamento</b>
<button onClick={getOpeningHours}>cliccc</button>
                        <b>Morada</b>
                        <ul>
                            <li> {parque.morada} ({parque.distancia} de si) </li>
                        </ul>
                    </div>
                </div>
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
        </div>
    );
}

export default Details;