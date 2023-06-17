import './Details.css'


function Details({
    
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
                <label>Rua Raio, 209, 4710-923 Braga</label>
                <div className="image image_desc">
                    Imagem
                </div>
                
                <div className="options_desc">
                    <button className="desc_button desc_button_hover">Description</button>
                    <button className="desc_button">Characteristics</button>
                    <button className="desc_button">Location</button>
                </div>

                <div className="desc_desc">
                    <h2>Description</h2>
                    <h3>Information</h3>
                    <ul>
                        <li> Public parking The PARC VISCONDE DO RAIO is located in the heart of the city, just a 7-minute walk from the historic city center. </li>
                        <li> Parking is covered and accessible from Monday to Friday from 8:00 am to 8:00 pm and Saturdays from 10:00 to 20:00. </li>
                        <li> Parking is offered by our partner VISCONDE DO RAY PARK . </li>
                    </ul>
                    <h3>Opening hours</h3>
                    <ul>
                        <li> Monday to Friday from 08:00 to 20:00 and Saturdays from 10:00 to 20:00. </li>
                    </ul>
                </div>
            </main>
            <aside className="aside_fp">
                <h2>Booking information</h2>
                <div className="aside_desc_section">
                    <h3>[selectDataInicio]</h3>
                </div>
                <div className="aside_desc_section">
                    <h3>[selectDataFim]</h3>
                </div>
                <div className="aside_desc_section">
                    <div className="aside_desc_subsection">
                        <label>Tempo utilizado: 2h</label>
                        <label>€1.30</label>
                    </div>
                    <div className="aside_desc_subsection">
                        <label>Multiple entries and exits</label>
                        <label>Free</label>
                    </div>
                    <div className="aside_desc_subsection">
                        <b>Total</b>
                        <b>€1.30</b>
                    </div>
                </div>
                {/*<button className="default" style="width: 100%;">Book</button>*/}
            </aside>
        </div>
        </>
    );
}

export default Details;