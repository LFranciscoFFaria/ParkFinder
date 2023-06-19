import './Manager.css'
import NavbarStaff from '../objects/NavbarStaff';
import { Button } from '../interactive_items/Button';
import '../pages/Details.css'
import { useEffect, useState } from 'react';
import Contacts from '../objects/Contacts';


const gestores = [
    {
        'id': 0,
        'nome': "rui",
        'email': "rui@gmail.com",
        'telemovel': "936978575",
        'password': "rui",
        'parques': ["BRAGA PARQUE","B&B BRAGA LAMAÇÃES"],
        'administradores': ["joao","miguel","antonio"],
    },
    {
        'id': 1,
        'nome': "carlos",
        'email': "carlos@gmail.com",
        'telemovel': "936978575",
        'password': "carlos",
        'parques': ["Alex_Nao","Sabe_Conduzir"],
        'administradores': ["Sr. Qual","Sr. Alex","Sr. ???"],
    },
    {
        'id': 2,
        'nome': "pedro",
        'email': "pedro@gmail.com",
        'telemovel': "936978575",
        'password': "pedro",
        'parques': ["esquina",],
        'administradores': ["pedro2","rui2","antonio2"],
    },
]



function Programmer({
}) {

    const [selected,setSelected] = useState(1);
    const [page,setPage] = useState(null);

    function renderPage() {
        switch (selected) {
            case 1:
                setPage(<label>{/* função/modulo para renderizar Parques (vê o discord)*/} Parques </label>);
                break;

            default:
                setPage(
                    <Contacts
                        listUsers={gestores} 
                        createButton={<Button buttonStyle={"default"} onClick={() => console.log("createButton")}>createButton</Button>} 
                        editButton={<Button buttonStyle={"default"} onClick={() => console.log("editButton")}>editButton</Button>} 
                        removeButton={<Button buttonStyle={"default"} onClick={() => console.log("removeButton")}>removeButton</Button>} 
                        title={"Gestores"}
                    />
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
                <NavbarStaff link_logo={'/programmer'}/>
                <div className='details_options'>
                    <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} onClick={()=>{setSelected(1)}}>Parques</Button>
                    <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} onClick={()=>{setSelected(2)}}>Gestores</Button>
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



