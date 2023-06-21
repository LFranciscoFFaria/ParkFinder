import './Details.css'

import '../../objects/CompressedParkInfo.css'
import '../../interactive_items/select.css'

import Description from '../../objects/Description';
import Characteristics from '../../objects/Caracteristics';
import Booking from '../../objects/Booking';

import{ Navbar }from '../../objects/Navbar';
import { ImageBlock } from '../../interactive_items/ImageBlock';
import { Button } from '../../interactive_items/Button';
import { useEffect, useState } from 'react';


const parques = [
    {
        "id" : 0,
        "nome" : "PARQUE VISCONDE DO RAIO",
        "morada" : "rua dos reis",
        "distancia" : "(797 m)",
        "lugares_vagos" : 45,
        "lugares_totais" : 96,
        "link_imagem" : "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        "custo" : 1.30,
        "hora_init" :"8:00",
        "hora_end" :"19:00",
        "descricao" : "Public covered parking\n7 min. walk from the heart of the city\nAccessible from Monday to Friday from 8:00 am to 8:00 pm and Saturdays from 10:00 am to 8:00 pm.",
        'instant':"PG-58-KL\nDH-90-FDW",
    },
    {
        "id" : 1,
        "nome" : "B&B BRAGA LAMAÇÃES",
        "morada" : "rua dos reis",
        "distancia" : "(2.7 km)",
        "lugares_vagos" : 22,
        "lugares_totais" : 51,
        "link_imagem" : "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        "custo" : 7.00,
        "hora_init" :"8:00",
        "hora_end" :"19:00",
        "descricao" : "Covered Hotel Parking\n10 min. from University of Minho\ntaxi service Accessible 24/7",
        'instant':"PG-58-KL\nDH-90-FDW",
    },
    {
        "id" : 2,
        "nome" : "BRAGA PARQUE",
        "morada" : "rua dos reis",
        "distancia" : "(1.1km)",
        "lugares_vagos" : 186,
        "lugares_totais" : 268,
        "link_imagem" : "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        "custo" : 2.15,
        "hora_init" :"8:00",
        "hora_end" :"19:00",
        "descricao" : "Public covered Parking\nUnder the citizen's house from Braga\nAccessible 24/7",
        'instant':"PG-58-KL\nDH-90-FDW",
    },
]

function Details({
    setState,
    userID
}) {

    const [selected,setSelected] = useState(1);
    const [page,setPage] = useState(1);
    const [parque,setParque] = useState(parques[1]);

    
    useEffect(() => {
        let parque_id = localStorage.getItem('parqueId');
        if (parque_id===null){
            parque_id = sessionStorage.getItem('parqueId');
        } else {
            localStorage.removeItem('parqueId');
            sessionStorage.setItem('parqueId', parque_id);
        }
        setParque(parques[parque_id]);
        console.log(parque_id);
        console.log(parque);
    }, []);



    function renderPage() {
        switch (selected) {
            case 1:
                setPage(<Description parque={parque}/>);
                break;

            default:
                setPage(<Characteristics parque={parque}/>);
                break;
        }
    }
    
    useEffect (() => {
        renderPage() 
    }, [selected])
    
    function ocupationColor() {
        if ((parque['lugares_vagos']/parque['lugares_totais']) > 0.30) {
            return "green"
        } else if ((parque['lugares_vagos']/parque['lugares_totais']) > 0.10) {
            return "orange"
        }
        return "red"
    }
    
    return (
        <div className='front_page'>
            <Navbar setState={setState} setFilter={null} userID={userID}/>
            <div className="front_page_content">
                <div className="details_display">
                    <div className="details_header">
                        <h1>{parque['nome']}</h1>
                        <div className={'compressed_park_spaces ' + ocupationColor()}>
                            <b> {parque['lugares_totais'] - parque['lugares_vagos']}/{parque['lugares_totais']} </b>
                        </div>
                    </div>
                    <label>{parque['morada']}</label>

                    <div className="image details_image">
                        <ImageBlock imageLink={parque['link_imagem']}/>
                    </div>
                    <div className="details_options">
                        <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} onClick={()=>{setSelected(1)}}>Descrição</Button>
                        <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} onClick={()=>{setSelected(2)}}>Características</Button>
                    </div>
                    {page}
                </div>
                <Booking/>
            </div>
        </div>
    );
}

export default Details;