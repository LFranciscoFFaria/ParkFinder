import '../pages/Details.css'
import NavbarStaff from '../objects/NavbarStaff';
import { Button } from '../interactive_items/Button';
import { useEffect, useState } from 'react';
import ParksManager from './ParksManager';
import AdminsManager from './AdminsManager';
import StatsManager from './StatsManager';

function Manager({
    parques,
    administradores,
    estatisticas,
    setFilter,
    setIdParque
}) {

    const [selected,setSelected] = useState(1);
    const [page,setPage] = useState(null);

    function renderPage() {
        switch (selected) {
            case 1:
                setPage(
                    <ParksManager parques={parques}/>
                );
                break;

            case 2:
                setPage(
                    <AdminsManager admins={administradores}/>
                );
                break;

            default:
                setPage(
                    <StatsManager stats={estatisticas}/>
                );
                break;
        }
    }

    useEffect (() => {
        renderPage() 
    }, [selected]);

    return (
        <div className='staff_bg'>
            <div className='staff_whitebox'>
                <NavbarStaff link_logo={'/manager'}/>
                <div className='details_options'>
                    <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} onClick={()=>{setSelected(1)}}>Parques</Button>
                    <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} onClick={()=>{setSelected(2)}}>Administradores</Button>
                    <Button buttonStyle={"ditails_button"+(selected===3? ' ditails_button_selected':'')} onClick={()=>{setSelected(3)}}>Estatísticas</Button>
                </div>

                {page}
            </div>
        </div>
    );
}

export default Manager;



