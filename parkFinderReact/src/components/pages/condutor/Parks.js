import './Parks.css'
import '../../interactive_items/select.css'
import { CompressedParkInfo } from '../../objects/CompressedParkInfo.js';
import Filter from '../../objects/Filter';
import Navbar from '../../objects/Navbar';
import { useState } from 'react';

function Parks({
    parques,
    filter,
    setFilter,
    setState,
}) {
    const [popUp, setPopUp] = useState(false);
    
    return (
        <div className='front_page'>
            <Navbar userID={'1234567890abc'} setState={setState} setFilter={() => setFilter(!filter)}/>
            <div className='parks_content_display'>
                <div className='parks_info_display'>
                    <div className='parks_header'>
                        <h1>[Local De Pesquisa]</h1>
                        <select className='select' name='Criterion' id='criterion' defaultValue={"default"}>
                            <option className='disabled_selected' value="default" disabled>Sort by</option>
                            <option value='distance'>Distance</option>
                            <option value='price'>Price</option>
                        </select>
                    </div>
                    {parques.map(parque => 
                        <CompressedParkInfo key={parque['id']} parque={parque}/>
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
                    <Filter/>
                </div>
            </div>
        </div>
    );
}

export default Parks;