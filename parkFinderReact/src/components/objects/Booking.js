import { Button } from '../interactive_items/Button';
import '../pages/condutor/Details.css';
import '../objects/Booking.css';
import { useState } from 'react';
import Tooltip from 'rc-tooltip';



function Booking({
    parque,
    popUpFormat = false
}) {
    const [custos,setCustos] = useState([]);

    return (
        <div className={'booking' + (popUpFormat? ' booking_reverse':'')}>
            <div className={popUpFormat? 'booking_popup_format':'booking_box'}>
                <div className='booking_header'>
                    <h2>Agendar Reserva</h2>
                </div>
                <div className='booking_dates_display'>
                    <div className='booking_dates_block'>
                        Data de Início:
                        <div className='booking_dates_fields'>
                            <input className='booking_date_input_field' id='date_begin' type='date' />
                            <input className='booking_date_input_field' id='time_begin' type='time' />
                        </div>
                    </div>
                    <div className='booking_dates_block'>
                        Data de Fim:
                        <div className='booking_dates_fields'>
                            <input className='booking_date_input_field' id='date_end' type='date' />
                            <input className='booking_date_input_field' id='time_end' type='time' />
                        </div>
                    </div>
                    <div class='booking_desc_section'>
                        <div class='booking_desc_subsection'>
                            <label>Tempo utilizado: 2h</label>
                            <label>1.30€</label>
                        </div>
                        <div class='booking_desc_subsection'>
                            <b>Total</b>
                            <b>{custos}€</b>
                        </div>
                    </div>
                </div>
                <div className='booking_submit_button'>
                    <br/>
                    <Button buttonStyle='default'>Book</Button>
                </div>
            </div>
            <div className={popUpFormat? 'booking_popup_format_line':''}></div>
            <div className={popUpFormat? 'booking_popup_format':'booking_box'}>
                <div className='booking_header'>
                    <h2>Dar entrada</h2>
                    <Tooltip placement='top' overlay={
                        <div className='compressed_park_tooltip'>
                            <b>Para garantir que vai encontrar um lugar</b>
                            <b>quando chegar ao destino, pode</b>
                            <b>começar já a ocupá-lo</b>
                            <b>(podem ser cobrados custos adicionais)</b>
                        </div>
                    }>
                        <div className='booking_header_info'>?</div>
                    </Tooltip>
                </div>
                <div class='booking_desc_section'>
                    <div class='booking_desc_subsection'>
                        <label>Preço por hora: </label>
                        <b> 1.30€ </b>
                    </div>
                </div>
                <div className='booking_submit_button'>
                    <br/>
                    <Button buttonStyle='default'>Book</Button>
                </div>
            </div>
        </div>
    );
};

export default Booking;



