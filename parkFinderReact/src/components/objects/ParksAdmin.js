import '../pages/condutor/Parks.css'
import '../interactive_items/select.css'
import AdminCompressedParkInfo from './AdminCompressedParkInfo';
import Filter from './Filter';
import NavbarStaff from './NavbarStaff';
import { useEffect, useState } from 'react';
import { Button } from '../interactive_items/Button';

function ParksAdmin({
    setState,
    filter,
    parques
}) {
    const [popUp, setPopUp] = useState(false);
    console.log(parques);

    return (
            <div className='parks_content_display'>
                <div className='parks_info_display'>
                    {parques.map(parque => 
                        <AdminCompressedParkInfo key={parque['id']} parque={parque}/>
                    )}
                    <div className='pageNumb'>
                        <button className='page_button'> {'<<'} </button>
                        <button className='page_button'> 1 </button>
                        <button className='page_button'> 2 </button>
                        <button className='page_button'> 3  </button>
                        <button className='page_button'> {'>>'} </button>
                    </div>
                </div>
                <div className={filter? 'parks_filter_display active': 'parks_filter_display'}>
                    <Filter dates={true}/>
                </div>
            </div>
    );
}

export default ParksAdmin;