import './Filter.css'
import './FilterStaff.css'
import Checkbox from "../interactive_items/Checkbox"
import { Button } from '../interactive_items/Button';
import { useState } from 'react';
import { useEffect } from 'react';


export function Filter({
    showDates = true
}) {
    const [filter,setFilter] = useState(null);

    function clearFilter(){
        setFilter({
            "Tipo de parque": {"Coberto":false, "Exterior":false},
            "Serviços": {"Serviço de recarga elétrica":false, "Entradas e saídas múltiplas":false, "Acesso rápido":false, "Elevador":false},
            "Reservas": {"Reserva agendada disponível":false}
        });
    };

    function updateFilter(title, text){
        let filterAux = JSON.parse(JSON.stringify(filter));
        filterAux[title][text] = !filterAux[title][text];
        setFilter(filterAux);
    };

    useEffect(()=>{
        console.log(filter);
    },[filter]);

    useEffect(()=>{clearFilter()},[]);

    if (filter !== null)
        return (
            <div className="filter_box">
                <div className="filter_header">
                    <h2>Filtros</h2>
                    <div className='filter_header_buttons'>
                        <Button buttonStyle="contrast" onClick={() => {clearFilter()}}>Limpar filtros</Button>
                        <div className='activate'> <Button buttonStyle="default">Aplicar Filtros</Button> </div>
                    </div>
                </div>

                {showDates?
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
                    {Object.entries(filter).map(([title, fields]) =>
                        <div className="filter_block" key={title}>
                            <b>{title}</b>
                            {Object.entries(fields).map(([text, check]) =>
                                <Checkbox checked={check} key={text} onChange={() => {updateFilter(title, text)}}>{text} </Checkbox>
                            )}
                        </div>
                    )}
                </div>
                <div className='filter_submit_button deactivate'>
                    <br/>
                    <Button buttonStyle="default">Aplicar Filtros</Button>
                </div>
            </div>
        );
    else
        return null;
};







export function FilterStaff({
}) {
    const [filter,setFilter] = useState(null);

    function clearFilter(){
        setFilter({
            "Tipo de parque": {"Coberto":false, "Exterior":false},
            "Serviços": {"Serviço de recarga elétrica":false, "Entradas e saídas múltiplas":false, "Acesso rápido":false, "Elevador":false},
            "Reservas": {"Reserva agendada disponível":false}
        });
    };

    function updateFilter(title, text){
        let filterAux = JSON.parse(JSON.stringify(filter));
        filterAux[title][text] = !filterAux[title][text];
        setFilter(filterAux);
    };

    useEffect(()=>{
        console.log(filter);
    },[filter]);

    useEffect(()=>{clearFilter()},[]);

    if (filter !== null)
        return (
            <div className="filter_staff_box">
                <div className="filter_staff_header">
                    <h2>Filtros</h2>
                    <div className='filter_header_buttons'>
                        <Button buttonStyle="contrast" onClick={() => {clearFilter()}}>Limpar filtros</Button>
                        <div> <Button buttonStyle="default">Aplicar Filtros</Button> </div>
                    </div>
                </div>
                <div className='filter_staff_block_display'>
                    {Object.entries(filter).map(([title, fields]) =>
                        <div className="filter_staff_block" key={title}>
                            <b>{title}</b>
                            {Object.entries(fields).map(([text, check]) =>
                                <Checkbox checked={check} key={text} onChange={() => {updateFilter(title, text)}}>{text} </Checkbox>
                            )}
                        </div>
                    )}
                </div>
            </div>
        );
    else
        return null;
};