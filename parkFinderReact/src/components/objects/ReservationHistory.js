import { useState } from 'react';
import { Button } from '../interactive_items/Button'
import { getDifInDateTime, writeDifInDate } from '../interactive_items/DT';
import './ReservationHistory.css';
import PopUp from '../interactive_items/PopUp';
import { QRCodeSVG } from 'qrcode.react'






export function ReservationHistory({
    reservations,
}) {
    console.log(reservations);
    return (
        <div className='edit_perfil_form_content'>

            <h1>Histórico de Reservas</h1>
            {reservations.map((reservation, index) => 
                <div key={index} className='edit_perfil_field'>
                    <b className='reservation_history_park'>{reservation['nome_parque']}</b>
                    <div className='reservation_history_grid_content'>
                        <label>Estado da Reserva: </label>
                        <b>{reservation['estado']}</b>
                        <label>Hora de entrada: </label>
                        <b>{reservation['data_inicio']}</b>
                        <label>Duração: </label>
                        <b>{writeDifInDate(reservation['data_inicio'], reservation['data_fim'])}</b>
                        <label>Matrícula do veiculo: </label>
                        <b>{reservation['matricula']}</b>
                        <label>Custo: </label>
                        <b>{reservation['custo'] + (reservation['pago']? '€ (Pago)': '€ (Por pagar)')}</b>
                    </div>
                </div>
            )}
        </div>
    );
};




export function Reservation({
    reservations
}) {
    console.log(reservations);
    const [popUp, setPopUp] = useState(-1);
    
    function qrcode() {
        return(
            <>
                <QRCodeSVG value={'reserva:' + popUp} className="navbar_qrcode"/>
                <h3>Código: {'RES:' + popUp}</h3>
            </>
        );
    };

    return (
        <div className='edit_perfil_form_content'>

            <h1>Minhas Reservas</h1>
            {popUp >= 0?
                <PopUp closePopUp={() => setPopUp(-1)} text='QR Code' element={qrcode()} />
                :
                null
            }
            {reservations.map((reservation, index) => (
                reservation['estado'] !== "Concluida" && reservation['tipo_lugar'] !== 'Instantaneo'?
                    <div key={index} className='reservation_history_field'>
                        <div className='reservation_history_park_qrcode'>
                            <b className='reservation_history_park'>{reservation['nome_parque']}</b>
                            <Button buttonStyle={'default'} onClick={() => setPopUp(reservation['id'])}>Utilizar</Button>
                        </div>
                        <div className='reservation_history_grid_content'>
                            <label>Estado da Reserva: </label>
                            <b>{reservation['estado']}</b>
                            <label>Hora de entrada: </label>
                            <b>{reservation['data_inicio']}</b>
                            <label>Duração: </label>
                            <b>{writeDifInDate(reservation['data_inicio'], reservation['data_fim'])}</b>
                            <label>Custo: </label>
                            <b>{reservation['custo'] + (reservation['pago']? '€ (Pago)': '€ (Por pagar)')}</b>
                        </div>
                    </div>
                    :
                    null
            ))}
        </div>
    );
};


export default Reservation;