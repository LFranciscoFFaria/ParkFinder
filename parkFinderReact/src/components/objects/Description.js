import '../pages/driver/Details.css';




function separateString(string) {
    let lines = string.split("\\n");

    return(
        <ul>
            {lines.map((line, index) => (
                <li key={index}>{line}</li>
            ))}
        </ul>
    )
}

function Description({
    parque
}) {
    return (
        <div>
            <div className="contact_header">
                <h1>Descrição</h1>
            </div>
            <div className="details_pages_display">
                <h3>Informação</h3>
                <label className='compressed_park_info_description'>{separateString(parque['descricao'])}</label>
                <h3>Morada</h3>
                <ul>
                    <li> {parque['morada']} ({parque['distancia']} de si) </li>
                </ul>
            </div>
        </div>
    );
};

export default Description;


