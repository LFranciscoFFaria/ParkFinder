import '../pages/condutor/Parks.css'
import './Contacts.css'
import { FilterStaff } from './Filter';
import { CompressedParkInfoStaff } from './CompressedParkInfo';
import { Button } from '../interactive_items/Button';
import { useState } from 'react';

function ParksProgrammer({
    parks,
}) {

    const [filter,setFilter] = useState(false);

    return (
        <div className="contact_display">
            <div className="contact_header">
                <h1>Parques</h1>
                <div className='contact_button compressed_park_staff_create_button'>
                    <Button buttonStyle={(!filter? "sex_button":"sex_button_selected") + " compressed_park_staff_filter_button"} onClick={() => setFilter(!filter)}>Filtro</Button>
                    <Button buttonStyle={"default"} onClick={() => console.log("Novo Parque")}>Criar Parque</Button>
                </div>
            </div>
            {filter? <FilterStaff/> : null}
            {parks.map(parque => 
                <CompressedParkInfoStaff key={parque['id']} parque={parque} editButton={
                    <Button buttonStyle={'default'} onClick={() => {console.log("Parque Removido")}}>Remover</Button>
                }/>
            )}
            <div className='pageNumb'>
                <button className='page_button'> {'<<'} </button>
                <button className='page_button'> 1 </button>
                <button className='page_button'> 2 </button>
                <button className='page_button'> 3  </button>
                <button className='page_button'> {'>>'} </button>
            </div>
        </div>
    );
}

export default ParksProgrammer;