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
                        createButton={<Button buttonStyle={"default"} onClick={() => console.log("Criar Gestor")} link={'/programmer/managers/createGestor'}>Criar Gestor</Button>} 
                        editButton={<Button buttonStyle={"default"} onClick={() => console.log("Editar Gestor")}>Editar</Button>} 
                        removeButton={<Button buttonStyle={"default"} onClick={() => console.log("Remove Gestor")}>Remover</Button>}
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
            </div>
        </div>
    );
}

export default Programmer;



