import '../pages/driver/Details.css';

const diasDaSemana = ['segunda','terça','quarta','quinta','sexta','sabado','domingo'];

const infoSchedule = {
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

function getOpeningHours(infoSchedule) {

    let organizedSchedule = {
        1 : [],
        2 : [],
        3 : [],
        4 : [],
        5 : [],
        6 : [],
        7 : []
    };

    infoSchedule['periodos'].forEach((periodo) => {
        organizedSchedule[periodo['dia']].push(periodo);
    });
    
    function sortScheduleArray(array) {
        array.sort((a, b) => a.inicio - b.inicio || a.fim - b.fim);
    }

    for (const key in organizedSchedule) {
        if (organizedSchedule[key].length > 0) {
            const array = organizedSchedule[key];
            sortScheduleArray(array);
        }
    }

    return(
        <ul>
            {[1, 2, 3, 4, 5, 6, 7].map(day => (
                <li className='caracteristics_schedule_grid' key={day}>
                    <b className='caracteristics_day_grid'>{diasDaSemana[day-1]}:</b>
                    <div className='caracteristics_shifts_grid'>
                        {organizedSchedule[day].map((shift)=>(
                            (organizedSchedule[day].length === 0?
                                <label key={shift['inicio']}> Fechado </label>
                                :
                                <label key={shift['inicio']}> {floatToTime(shift['inicio'])} - {floatToTime(shift['fim'])} </label>
                            )
                        ))}
                    </div>
                </li>
            ))}
        </ul>
    );
}

function Characteristics({
    parque
}) {
    return (
        <div>
            <div className="contact_header">
                <h1>Características</h1>
            </div>
            <div className="details_pages_display">
                <h3>Horário de funcionamento</h3>
                {getOpeningHours(infoSchedule)}
                <h3>Capacidade</h3>
                <ul>
                    <li> Nº de lugares vagos: {parque['lugares_vagos']} </li>
                    <li> Nº de lugares total: {parque['lugares_totais']} </li>
                </ul>
            </div>
        </div>
    );
};


export default Characteristics;