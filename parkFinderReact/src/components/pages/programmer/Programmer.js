import { NavbarStaff } from '../../objects/Navbar';
import { Button } from '../../interactive_items/Button';
import '../condutor/Details.css'
import { useEffect, useState } from 'react';
import Contacts from '../../objects/Contacts';
import ParksProgrammer from '../../objects/ParksProgrammer';




function Programmer({
    managers,
    selected,
    parks
}) {

    const [page,setPage] = useState(null);

    function renderPage() {
        switch (selected) {
            case 1:
                setPage(<ParksProgrammer parks={parks}/>);
                break;

            default:
                setPage(
                    <Contacts
                        listUsers={managers} 
                        createButton={<Button buttonStyle={"default"} onClick={() => console.log("createButton")}>Criar Administrador</Button>} 
                        editButton={<Button buttonStyle={"default"} onClick={() => console.log("editButton")}>Editar</Button>} 
                        removeButton={<Button buttonStyle={"default"} onClick={() => console.log("removeButton")}>Remover</Button>}
                        title={"Gestores"}
                        showPark={true}
                    />
                );
                break;
        }
    }

    useEffect (() => {
        console.log("render");
        renderPage() 
    }, [selected]);

    return (
        <div className='staff_bg'>
            <div className='staff_whitebox'>
                <NavbarStaff link_logo={'/programmer'}/>
                <div className='details_options'>
                    <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} link={'/programmer'}>Parques</Button>
                    <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} link={'/programmer/managers'}>Gestores</Button>
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



