import '../driver/Details.css'
import { NavbarStaff } from '../../objects/Navbar';
import { Button } from '../../interactive_items/Button';
import { useEffect, useState } from 'react';

import ParksManager from '../../objects/ParksManager';
import StatsManager from '../../objects/StatsManager';
import ContactsManager from '../../objects/ContactsManager';

function Manager({
    parks,
    administradores,
    estatisticas,
    selected,
}) {

    const [page,setPage] = useState(null);

    useEffect (() => {
        function renderPage() {
            switch (selected) {
                case 1:
                    setPage(
                        <ParksManager parks={parks}/>
                    );
                    break;

                case 2:
                    setPage(
                        <ContactsManager administradores={administradores} />
                    );
                    break;

                default:
                    setPage(
                        <StatsManager stats={estatisticas}/>
                    );
                    break;
            }
        }

        renderPage() 
    }, [selected,parks,administradores,estatisticas]);

    return (
        <div className='staff_bg'>
            <div className='staff_whitebox'>
                <NavbarStaff link_logo={'/manager'}/>
                <div className='details_options'>
                    <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} link={'/manager/'}>Parques</Button>
                    <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} link={'/manager/admins'}>Administradores</Button>
                    <Button buttonStyle={"ditails_button"+(selected===3? ' ditails_button_selected':'')} link={'/manager/statistics'}>Estatísticas</Button>
                </div>

                {page}
            </div>
        </div>
    );
}

export default Manager;



