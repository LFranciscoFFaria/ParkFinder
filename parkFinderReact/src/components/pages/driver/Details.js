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
import Tooltip from 'rc-tooltip';


function Details({
    setParkTypeFilter
}) {

    const [selected,setSelected] = useState(1);
    const [page,setPage] = useState(1);
    const [parque,setParque] = useState(null);

    var userId = localStorage.getItem('userId')


    useEffect(() => {
        let parque_id = localStorage.getItem('parqueId');
        if (parque_id===null){
            parque_id = sessionStorage.getItem('parqueId');
        } else {
            localStorage.removeItem('parqueId');
            sessionStorage.setItem('parqueId', parque_id);
        }

        const requestOptions = {
            method: 'GET',
            headers: { "Content-Type": "application/json" }
        }
        fetch("http://localhost:8080/apiV1/parques/id?id_parque=" + parque_id, requestOptions)
            .then(res => res.json())
            .then((result) => {
                setParque(result)
            })
    }, []);



    useEffect (() => {
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
        if (parque !== null)
            renderPage() 
    }, [selected, parque])

    function ocupationColor() {
        if ((parque['instantaneos_livres']/parque['instantaneos_total']) > 0.30) {
            return "green"
        } else if ((parque['instantaneos_livres']/parque['instantaneos_total']) > 0.10) {
            return "orange"
        }
        return "red"
    }
    if (parque !== null)
        return (
            <div className='front_page'>
                <Navbar setParkTypeFilter={setParkTypeFilter} setFilter={null}/>
                <div className="front_page_content">
                    <div className="details_display">
                        <div className="details_header">
                            <h1>{parque['nome']}</h1>
                            <Tooltip placement="top" overlay={
                                <div className='compressed_park_tooltip'> <b>Lugares Instantaneos Ocupados</b></div>
                            } >
                                <div className={'compressed_park_spaces ' + ocupationColor()}>
                                    <b> {parque["instantaneos_total"] - parque["instantaneos_livres"]}/{parque["instantaneos_total"]} </b>
                                </div>
                            </Tooltip>
                        </div>
                        <label>{parque['morada']}</label>

                        <div className="image details_image">
                            <ImageBlock imageLink={parque['caminho_foto']}/>
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
    else return <></>
}

export default Details;