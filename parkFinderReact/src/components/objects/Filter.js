import './Filter.css'
import Checkbox from "../interactive_items/Checkbox"
import { Button } from '../interactive_items/Button';

const filter = {
    "Tipo de parque": ["Coberto", "Exterior"],
    "Serviços": ["Serviço de recarga elétrica", "Entradas e saídas múltiplas", "Acesso rápido", "Elevador"],
    "Reservas": ["Reserva agendada disponível"]
};

function Filter({
    dates
}) {
    
    return (
        <div className="filter_box">
            <div className="filter_header">
                <h2>Filtros</h2>
                <div className='filter_header_buttons'>
                    <Button buttonStyle="contrast">Limpar filtros</Button>
                    <div className='activate'> <Button buttonStyle="default">Apply filters</Button> </div>
                </div>
            </div>

            {dates?
                <div className="filter_dates_display">
                    <div className='filter_dates_block'>
                        Data de Início:
                        <div className='filter_dates_fields'>
                            <input className='filter_date_input_field' id='date_begin' type='date' />
                            <input className='filter_date_input_field' id='time_begin' type='time' />
                        </div>
                    </div>
                    <div className='filter_dates_block'>
                        Data de Fim:
                        <div className='filter_dates_fields'>
                            <input className='filter_date_input_field' id='date_end' type='date' />
                            <input className='filter_date_input_field' id='time_end' type='time' />
                        </div>
                    </div>
                </div>
                :
                null
            }
            <div className='filter_block_display'>
                {Object.entries(filter).map(([title, list]) =>
                <div className="filter_block" key={title}>
                    <b>{title}</b>
                    {list.map(element => 
                        <Checkbox key={element}>{element} </Checkbox>
                    )}
                </div>
            )}
            </div>
            <div className='deactivate'> <Button buttonStyle="default">Aplicar Filtros</Button> </div>
        </div>
    );
}

export default Filter;