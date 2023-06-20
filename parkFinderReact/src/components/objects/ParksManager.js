import '../pages/condutor/Parks.css'
import './Contacts.css'
import { FilterStaff } from './Filter';
import { CompressedParkInfoStaff } from './CompressedParkInfo';
import { Button } from '../interactive_items/Button';
import { useState } from 'react';

function ParksManager({
    parks,
    createButton = null,
}) {

    const [filter,setFilter] = useState(false);

    return (
        <div className="contact_display">
            <div className="contact_header">
                <h1>Parques</h1>
                <div className='contact_button'>{createButton}</div>
            </div>
            <div className={filter? 'parks_filter_display active': 'parks_filter_display'}>
                {/* <FilterStaff dates={true}/> */}
            </div>
            {parks.map(parque => 
                <CompressedParkInfoStaff key={parque['id']} parque={parque} editButton={
                    <Button buttonStyle={'default'} onClick={() => localStorage.setItem("parqueId", parque["id"])} link={'/manager/details'}>Gerir</Button>
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

export default ParksManager;