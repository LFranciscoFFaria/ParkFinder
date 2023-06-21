import './Contacts.css'
import './StatsManager.css'
import { useState } from 'react';

function StatsManager({
    stats
}) {
    return (
        <div className="contact_display">
            <div className="contact_header">
                <h1>Estatísticas</h1>
            </div>
            <div className="stats_manager_grid_container">
                {stats.map(stat => 
                    <div key={stat['id']} className="stats_manager_grid_item">
                        <div className="stats_manager_title">
                            <b>{stat.nome}</b>
                        </div>
                        <li className="stats_manager_info">Stat1: <b>{stat.nr}</b></li>
                        <li className="stats_manager_info">Stat1: <b>{stat.nr}</b></li>
                        <li className="stats_manager_info">Stat1: <b>{stat.nr}</b></li>
                    </div>
                )}
            </div>
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

export default StatsManager;