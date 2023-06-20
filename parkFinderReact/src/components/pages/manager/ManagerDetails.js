import Filter from '../../objects/Filter';
import { NavbarStaff } from '../../objects/Navbar';
import {ImageBlock} from '../../interactive_items/ImageBlock';
import { Button } from '../../interactive_items/Button';
import '../../interactive_items/select.css'
import Description from '../../objects/Description';
import Characteristics from '../../objects/Caracteristics';
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
    },
]

function editPerfilField (
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
                    <Button type='submit' buttonStyle='contrast'>Gravar</Button>
                </div>
            </div>
        </form>
    )
}

function ManagerDetails({
    setState
}) {
    const [description, setDescription] = useState('');
    const [name, setParkName] = useState('');


    const savePark = (event) => {
        event.preventDefault()
        console.log("Save Park");
        console.log("description = " + description);
        console.log("name = " + name);
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
    }, [selected])

    function renderPage() {
        switch (selected) {
            case 1:
                setPage(
                    <div className='details_pages_display'>
                        <div className='edit_perfil_form_content'>
                            {editPerfilField('Nome',null,'Nome',setParkName,(savePark))}
                            {editPerfilField('Descrição','descricao','descricao',setDescription,savePark)}
                        </div>
                    </div>
                );
                break;
            case 2:
                setPage(<Description parque={parque}/>);
                break;

            case 3:
                setPage(<Characteristics parque={parque}/>);
                break;

            default:
                setPage(
                    <>
                    </>
                );
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
                <NavbarStaff link_logo={'/manager'}/>
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
                    <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} onClick={()=>{setSelected(1)}}>Edição</Button>
                    <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} onClick={()=>{setSelected(2)}}>Descrição</Button>
                    <Button buttonStyle={"ditails_button"+(selected===3? ' ditails_button_selected':'')} onClick={()=>{setSelected(3)}}>Caracteristicas</Button>
                    <Button buttonStyle={"ditails_button"+(selected===4? ' ditails_button_selected':'')} onClick={()=>{setSelected(4)}}>Estatisticas</Button>

                </div>
                {page}
            </div>
        </div>
    );
}

export default ManagerDetails;