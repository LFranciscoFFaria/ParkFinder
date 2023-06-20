import '../pages/condutor/Details.css';



const horas = {
    "periodos" : [
        {"dia": 2, "inicio" : 7.5, "fim" : 13},
        {"dia": 3, "inicio" : 14.5, "fim" : 21},
        {"dia": 1, "inicio" : 7.5, "fim" : 13},
        {"dia": 5, "inicio" : 14.5, "fim" : 23},
        {"dia": 5, "inicio" : 7.5, "fim" : 13},
        {"dia": 2, "inicio" : 14.5, "fim" : 21},
        {"dia": 4, "inicio" : 7.5, "fim" : 13},
        {"dia": 3, "inicio" : 7.5, "fim" : 13},
        {"dia": 6, "inicio" : 8, "fim" : 13},
        {"dia": 4, "inicio" : 14.5, "fim" : 21},
        {"dia": 1, "inicio" : 14.5, "fim" : 21},
    ]
}




function floatToTime(floatValue) {
    const hours = Math.floor(floatValue);
    const minutes = Math.floor((floatValue % 1) * 60);
  
    const formattedHours = String(hours).padStart(2, '0');
    const formattedMinutes = String(minutes).padStart(2, '0');
  
    return <label>{formattedHours}h{formattedMinutes}</label>;
}

function getOpeningHours() {
        
    let horas2 = [
        {"dia": 1, "horas" : []},
        {"dia": 2, "horas" : []},
        {"dia": 3, "horas" : []},
        {"dia": 4, "horas" : []},
        {"dia": 5, "horas" : []},
        {"dia": 6, "horas" : []},
        {"dia": 7, "horas" : []},
    ];
    let diasDaSemana = ['segunda','terça','quarta','quinta','sexta','sabado','domingo']
    horas['periodos'].forEach((horario) => {
        horas2[horario['dia'] - 1]['horas'].push(horario['inicio'], horario['fim']);
    });

    horas2.forEach((dia) => {
        dia['horas'].sort((a, b) => a - b);
    });

    return(
        <ul>
            {Object.entries(horas2).map(([dia, horario]) =>
                <li className='caracteristics_horario_grid' key={dia}>
                    <b className='caracteristics_dia_grid'>{diasDaSemana[dia]}:</b>
                    {horario['horas'].length === 0? <label  className='caracteristics_horas_1_grid'> Closed </label> : null}
                    {horario['horas'].length >= 2? <label  className='caracteristics_horas_1_grid'> {floatToTime(horario['horas'][0])}-{floatToTime(horario['horas'][1])} </label> : null}
                    {horario['horas'].length === 4? <label  className='caracteristics_horas_2_grid'> {floatToTime(horario['horas'][2])}-{floatToTime(horario['horas'][3])} </label> : null}
                </li>
            )}
        </ul>
    );
}

function Characteristics({
    parque
}) {
    return (
        <div className="details_pages_display">
            <h3>Horário de funcionamento</h3>
            {getOpeningHours()}
            <h3>Capacidade</h3>
            <ul>
                <li> Nº de lugares vagos: {parque['lugares_vagos']} </li>
                <li> Nº de lugares total: {parque['lugares_totais']} </li>
            </ul>
        </div>
    );
};


export default Characteristics;