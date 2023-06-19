import './FrontPageStaff.css';
import Parks from '../Manager.js';
import Perfil from '../Perfil.js';
import Details from '../Details.js';
import Navbar from '../../objects/NavbarStaff.js'
import { useEffect, useState } from 'react';

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

function FrontPage({
    
}) {
    const [state, setState] = useState('');
    const [filter, setFilter] = useState(false);
    const [elementToRender, setElementToRender] = useState(null);


    function changeElement() {
        let details_park = state.split(' ');
        console.log('state = ' + details_park);

        switch (details_park[0]) {
            case 'perfil':
              setElementToRender(<Perfil setState={setState}/>);
              break;
            case 'details':
              setElementToRender(<Details parque={parques[parseInt(details_park[1])]} filter={filter} setState={setState}/>);
              break;
            default:
              setElementToRender(<Manager parques={parques} filter={filter} setFilter={setFilter} setState={setState}/>);
        }
    }


    useEffect(() => {
        changeElement();
    }, [state,filter]);


    return (
        <div className='front_page_staff'>
            <div className='whitebox_staff'>
                <NavbarStaff setState={setState} setFilter={() => setFilter(!filter)}/>
                {elementToRender}
            </div>
        </div>
    );
}

export default FrontPage;