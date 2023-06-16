import './CompressedParkInfo.css';
import { Button } from '../interactive_items/Button';

const parque = {
    nome: "PARQUE VISCONDE DO RAIO",
    lugares_vagos: 40,
    lugares_totais: 95,
    link_imagem: "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
    custo: 2.3,
    descricao: "Public covered parking \n7 min. walk from the heart of the city \n Accessible from Monday to Friday from 8:00 am to 8:00 pm and Saturdays from 10:00 am to 8:00 pm."
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


function CompressedParkInfo({
    
}) {
    function adminOptions() {
        if ((parque.lugares_vagos/parque.lugares_totais) < 0.70) {
            return "green"
        } else if ((parque.lugares_vagos/parque.lugares_totais) < 0.9) {
            return "yellow"
        }
        return "red"
    }
    
    return (
        <div className="compressed_park">
            <div className="compressed_park_header">
                <div className="compressed_park_title">
                    <h2>{parque.nome}</h2>
                    <label className='gray_label'>(797 m)</label>
                </div>
                <div className={'compressed_park_spaces ' + adminOptions()}>
                    <b> {parque.lugares_totais - parque.lugares_vagos}/{parque.lugares_totais} </b>
                </div>
            </div>
            <div className="compressed_park_image_info">
                <div className='compressed_park_block_image'>
                    <img className={'compressed_park_image'} src={parque.link_imagem} alt={""} />
                </div>
                <div className="compressed_park_info">
                    <label>Estimated cost: <b>{parque.custo.toFixed(2)}€</b></label>
                    <label className='compressed_park_info_description'>{separateString(parque.descricao)}</label>
                    <div className="compressed_park_buttons">
                        <Button buttonStyle="page_button see_details_button">See details</Button>
                        <Button buttonStyle="default">Book</Button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CompressedParkInfo;