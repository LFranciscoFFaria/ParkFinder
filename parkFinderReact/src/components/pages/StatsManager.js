import './Parks.css'
import '../interactive_items/select.css'
import ManagerCompressedStatInfo from '../objects/ManagerCompressedStatInfo';
import Filter from '../objects/Filter';
import { useState } from 'react';
import { Button } from '../interactive_items/Button';

function StatsManager({
    stats
}) {
    const [popUp, setPopUp] = useState(false);
    
    return (
            <div className='parks_content_display'>
                <div className='parks_info_display'>
                    {stats.map(stat => 
                        <ManagerCompressedStatInfo key={stat['id']} stat={stat}/>
                    )}
                    <div className='pageNumb'>
                        <button className='page_button'> {'<<'} </button>
                        <button className='page_button'> 1 </button>
                        <button className='page_button'> 2 </button>
                        <button className='page_button'> 3  </button>
                        <button className='page_button'> {'>>'} </button>
                    </div>
                </div>
            </div>
    );
}

export default StatsManager;