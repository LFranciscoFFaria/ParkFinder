import './Details.css'
import Filter from '../objects/Filter';
import {ImageBlock} from '../interactive_items/ImageBlock';


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

function Details({
    filter,
    parque,
    setState
}) {
    
    return (
        <>
        <div className="content_front_page">
            <main className="main_fp">
                <div className="park_fp_header">
                    <h1>PARQUE VISCONDE DO RAIO</h1>
                    <div className="park_fp_spaces park_desc_spaces">
                        <b> 45/96 </b>
                    </div>
                </div>
                <label>{separateString(parque.morada)}</label>
                <div className="image image_desc">
                    <ImageBlock imageLink={parque.link_imagem}/>
                </div>
                
                <div className="options_desc">
                    <button className="desc_button desc_button_hover">Description</button>
                    <button className="desc_button">Characteristics</button>
                    <button className="desc_button">Location</button>
                </div>

                <div className="desc_desc">
                    <h3>Information</h3>
                    <label className='compressed_park_info_description'>{separateString(parque.descricao)}</label>
                    <h3>Opening hours</h3>
                    <ul>
                        <li> Monday to Friday from 08:00 to 20:00 and Saturdays from 10:00 to 20:00. </li>
                    </ul>
                </div>
            </main>
            <div className={filter? 'parks_filter_display active': 'parks_filter_display'}>
                <Filter dates={true}/>
            </div>
        </div>
        </>
    );
}

export default Details;