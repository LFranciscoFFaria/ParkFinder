import '../pages/Details.css';




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

function Description(
    parque
) {
    return (
        <div className="desc_desc">
            <b>Informação</b>
            <label className='compressed_park_info_description'>{separateString(parque.parque['descricao'])}</label>
            <b>Morada</b>
            <ul>
                <li> {parque.parque['morada']} ({parque.parque['distancia']} de si) </li>
            </ul>
        </div>
    );
};

export default Description;


