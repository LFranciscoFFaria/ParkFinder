import './Parks.css'
import '../interactive_items/select.css'
import CompressedParkInfo from '../objects/CompressedParkInfo.js';
import Filter from '../objects/Filter';

const parques = [
    {
        id: 0,
        nome: "PARQUE VISCONDE DO RAIO",
        distancia: "(797 m)",
        lugares_vagos: 45,
        lugares_totais: 96,
        link_imagem: "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        custo: 1.30,
        descricao: "Public covered parking\n7 min. walk from the heart of the city\nAccessible from Monday to Friday from 8:00 am to 8:00 pm and Saturdays from 10:00 am to 8:00 pm.",
    },
    {
        id: 1,
        nome: "B&B BRAGA LAMAÇÃES",
        distancia: "(2.7 km)",
        lugares_vagos: 22,
        lugares_totais: 51,
        link_imagem: "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        custo: 7.00,
        descricao: "Covered Hotel Parking\n10 min. from University of Minho\ntaxi service Accessible 24/7",
    },
    {
        id: 2,
        nome: "BRAGA PARQUE",
        distancia: "(1.1km)",
        lugares_vagos: 186,
        lugares_totais: 268,
        link_imagem: "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        custo: 2.15,
        descricao: "Public covered Parking\nUnder the citizen's house from Braga\nAccessible 24/7",
    },
]

function Parks({
    filter,
    setState
}) {
    
    return (
        <div className='parks_content_display'>
            <div className='parks_info_display'>
                <div className='parks_header'>
                    <h1>[Local De Pesquisa]</h1>
                    <select className='select' name='Criterion' id='criterion' defaultValue={"default"}>
                        <option className='disabled_selected' value="default" disabled>Sort by</option>
                        <option value='distance'>Distance</option>
                        <option value='price'>Price</option>
                    </select>
                </div>
                {parques.map(parque => 
                    <CompressedParkInfo parque={parque} setState={setState}/>
                )}
                <div className='pageNumb'>
                    <button className='page_button'> {'<<'} </button>
                    <button className='page_button'> 1 </button>
                    <button className='page_button'> 2 </button>
                    <button className='page_button'> 3  </button>
                    <button className='page_button'> {'>>'} </button>
                </div>
            </div>
            <div className={filter? 'parks_filter_display active': 'parks_filter_display'}>
                <Filter dates={true}/>
            </div>
        </div>
    );
}

export default Parks;