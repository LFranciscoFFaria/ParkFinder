
import { Button } from '../../interactive_items/Button';
import PopUp from '../../interactive_items/PopUp';
import '../pages/Details.css'
import { useEffect, useState } from 'react';


function funcName({

}) {

    // PopUp

    const [popUp, setPopUp] = useState(false);




    // Pages

    const [selected,setSelected] = useState(1);
    const [page,setPage] = useState(1);

    function renderPage() {
        switch (selected) {
            case 1:
                setPage({/* função/modulo para renderizar Pagina 1 (vê o discord)*/});
                break;

            default:
                setPage({/* função/modulo para renderizar Pagina 2 (vê o discord)*/});
                break;
        }
    }

    useEffect (() => {
        renderPage() 
    }, [selected]);







    return(
        <div>
            <button onClick={() => setPopUp(true)}>popup</button>
            {popUp?
                <PopUp closePopUp={() => setPopUp(false)} element={{/* elemento a ser renderizado */}}/>
                :
                null
            }


            {/* ... */}
            <div className="details_options">
                <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} onClick={()=>{setSelected(1)}}>Pagina 1</Button>
                <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} onClick={()=>{setSelected(2)}}>Pagina 2</Button>
            </div>
            {/* ... */}
            {page}
        </div>
    );
}

export default funcName;