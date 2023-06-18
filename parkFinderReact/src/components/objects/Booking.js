import { Button } from '../interactive_items/Button';
import '../pages/Details.css';
import '../objects/Filter.css';



function Booking(
    parque
) {
    return (
        <div className="filter_box">
            <div className="filter_header">
                <h2>Booking</h2>
            </div>
            <div className="filter_dates_display">
                <div className='filter_dates_block'>
                    Begin date:
                    <div className='filter_dates_fields'>
                        <input className='filter_date_input_field' id='date_begin' type='date' />
                        <input className='filter_date_input_field' id='time_begin' type='time' />
                    </div>
                </div>
                <div className='filter_dates_block'>
                    End date:
                    <div className='filter_dates_fields'>
                        <input className='filter_date_input_field' id='date_end' type='date' />
                        <input className='filter_date_input_field' id='time_end' type='time' />
                    </div>
                </div>
                <div class="booking_desc_section">
                    <div class="booking_desc_subsection">
                        <label>Tempo utilizado: 2h</label>
                        <label>€1.30</label>
                    </div>
                    <div class="booking_desc_subsection">
                        <label>Multiple entries and exits</label>
                        <label>Free</label>
                    </div>
                    <div class="booking_desc_subsection">
                        <b>Total</b>
                        <b>€1.30</b>
                    </div>
                </div>
            </div>
            <div className='filter_header_buttons'>
                <Button buttonStyle="default">Book</Button>
            </div>
        </div>
    );
};

export default Booking;



