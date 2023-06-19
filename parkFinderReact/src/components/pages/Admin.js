import '../pages/Details.css'
import NavbarStaff from '../objects/NavbarStaff';
import { Button } from '../interactive_items/Button';
import { useEffect, useState } from 'react';
import ParksAdmin from './ParksAdmin';

function Admin({
    parques,
    setFilter,
    setIdParque
}) {

    const [selected,setSelected] = useState(1);
    const [page,setPage] = useState(null);

    function renderPage() {
        switch (selected) {
            default:
                setPage(
                    <ParksAdmin parques={parques}/>
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
                </div>

                {page}
            </div>
        </div>
    );
}

export default Admin;



