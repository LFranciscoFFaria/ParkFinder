
import './ReservationHistory.css';

function ReservationHistory({
    reservations
}) {
    console.log(reservations);
    return (
        <div className='edit_perfil_form_content'>

            <h1>Historico de Reservas</h1>
            {reservations.map((reservation, index) => 
                <div key={index} className='edit_perfil_field'>
                    <b>{reservation['nome_utilizador']}</b>
                </div>
            )}
        </div>
    );
};


export default ReservationHistory;