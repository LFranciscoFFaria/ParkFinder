import './Manager.css'
import NavbarStaff from '../objects/NavbarStaff';
import { Button } from '../interactive_items/Button';
import '../pages/Details.css'
import { useEffect, useState } from 'react';


function Programmer({
    setIdParque
}) {

    const [selected,setSelected] = useState(1);
    const [page,setPage] = useState(null);

    function renderPage() {
        switch (selected) {
            case 1:
                setPage(<label>{/* função/modulo para renderizar Parques (vê o discord)*/} Parques </label>);
                break;

            default:
                setPage(<label>{/* função/modulo para renderizar Administradores (vê o discord)*/} Gestores </label>);
                break;
        }
    }

    useEffect (() => {
        renderPage() 
    }, [selected]);

    return (
        <div className='staff_bg'>
            <div className='staff_whitebox'>
                <NavbarStaff link_logo={'/programmer'}/>
                <div className='details_options'>
                    <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} onClick={()=>{setSelected(1)}}>Parques</Button>
                    <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} onClick={()=>{setSelected(2)}}>Administradores</Button>
                </div>

                {page}

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

export default Programmer;



