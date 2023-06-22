import Filter from '../../objects/Filter';
import { NavbarStaff } from '../../objects/Navbar';
import {ImageBlock} from '../../interactive_items/ImageBlock';
import { Button } from '../../interactive_items/Button';
import '../../interactive_items/select.css'
import Description from '../../objects/Description';
import Characteristics from '../../objects/Caracteristics';
import InstantPark from '../../objects/InstantPark';
import { useEffect, useState } from 'react';
import EditParkAdmin from '../../objects/EditParkAdmin';
import Tooltip from 'rc-tooltip';



const parques = [
    {
        'id': 0,
        'nome': "PARQUE VISCONDE DO RAIO",
        'morada': "rua dos reis",
        'distancia': "(797 m)",
        'lugares_vagos': 45,
        'instantaneos_total': 93,
        'lugares_totais': 96,
        'link_imagem': "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        'custo': 1.30,
        'hora_init':"8:00",
        'hora_end':"19:00",
        'descricao': "Public covered parking\n7 min. walk from the heart of the city\nAccessible from Monday to Friday from 8:00 am to 8:00 pm and Saturdays from 10:00 am to 8:00 pm.",
        'instant':"PG-58-KL\nDH-90-FDW",

    },
    {
        'id': 1,
        'nome': "B&B BRAGA LAMAÇÃES",
        'morada': "rua dos reis",
        'distancia': "(2.7 km)",
        'lugares_vagos': 22,
        'instantaneos_total': 40,
        'lugares_totais': 51,
        'link_imagem': "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        'custo': 7.00,
        'hora_init':"8:00",
        'hora_end':"19:00",
        'descricao': "Covered Hotel Parking\n10 min. from University of Minho\ntaxi service Accessible 24/7",
        'instant':"PG-58-KL\nDH-90-FD",

    },
    {
        'id': 2,
        'nome': "BRAGA PARQUE",
        'morada': "rua dos reis",
        'distancia': "(1.1km)",
        'lugares_vagos': 186,
        'instantaneos_total': 230,
        'lugares_totais': 268,
        'link_imagem': "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        'custo': 2.15,
        'hora_init':"8:00",
        'hora_end':"19:00",
        'descricao': "Public covered Parking\nUnder the citizen's house from Braga\nAccessible 24/7",
        'instant':"PG-58-KL\nDH-90-FD",
    },
]



function editParkField (
    name,
    type,
    placeholder,
    setFunc,
    saveProfile,
    ) {
    return(
        <form onSubmit={saveProfile}>
            <div className='edit_perfil_field'>
                <b> {name} </b>
                <div className='edit_perfil_input_button'>
                    <input
                        className='edit_perfil_input'
                        placeholder={placeholder}
                        type={type}
                        onChange={(e) => setFunc(e.target.value)}
                        required/>
                    <Button type='submit' buttonStyle='contrast'>Adicionar</Button>
                    <Button type='submit' buttonStyle='contrast'>Remover</Button>
                </div>
            </div>
        </form>
    )
}

function AdminDetails({
    setState
}) {
    const [instant, setInstant] = useState('');
    const [scheduled, setScheduled] = useState('');


    const savePark = (event) => {
        event.preventDefault()
        console.log("Save Park");
        console.log("instant = " + instant);
        console.log("scheduled = " + scheduled);
    };

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

    useEffect (() => {
        renderPage() 
    }, [parque,selected])

    function renderPage() {
        switch (selected) {
            case 1:
                setPage(
                    <EditParkAdmin parque={parque}/>
                );
                break;
            case 2:
                setPage(<InstantPark parque={parque}/>);
                break;
            case 3:
                setPage(<Description parque={parque}/>);
                break;

            default:
                setPage(<Characteristics parque={parque}/>);
                break;
        }
    }
    
    function ocupationColor() {
        if ((parque['lugares_vagos']/parque['lugares_totais']) > 0.30) {
            return "green"
        } else if ((parque['lugares_vagos']/parque['lugares_totais']) > 0.10) {
            return "orange"
        }
        return "red"
    }

    return (
        <div className='staff_bg'>
            <div className='staff_whitebox'>
                <NavbarStaff link_logo={'/admin'}/>
                <div className="details_header">
                    <h1>{parque['nome']}</h1>
                    <Tooltip placement="top" overlay={
                        <div className='compressed_park_tooltip'> <b>Lugares Instantaneos Ocupados</b></div>
                    } >
                        <div className={'compressed_park_spaces ' + ocupationColor()}>
                            <b> {parque["lugares_totais"] - parque["lugares_vagos"]}/{parque["lugares_totais"]} </b>
                        </div>
                    </Tooltip>
                </div>
                <label>{parque['morada']}</label>

                <div className="image details_image">
                    <ImageBlock imageLink={parque['link_imagem']}/>
                </div>
                <div className="details_options">
                    <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} onClick={()=>{setSelected(1)}}>Edição</Button>
                    <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} onClick={()=>{setSelected(2)}}>Parque instantâneo</Button>
                    <Button buttonStyle={"ditails_button"+(selected===3? ' ditails_button_selected':'')} onClick={()=>{setSelected(3)}}>Descrição</Button>
                    <Button buttonStyle={"ditails_button"+(selected===4? ' ditails_button_selected':'')} onClick={()=>{setSelected(4)}}>Características</Button>
                </div>
                {page}
            </div>
        </div>
    );
}

export default AdminDetails;