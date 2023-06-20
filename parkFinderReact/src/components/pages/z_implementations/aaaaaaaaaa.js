
import PopUp from '../../interactive_items/PopUp';
import { Button } from '../../interactive_items/Button';
import '../pages/Details.css'
import { useEffect, useState } from 'react';


function funcName({

}) {

// PopUp

    const [popUp, setPopUp] = useState(false);




// Pages para teste (simplificado)

    const [selected,setSelected] = useState(1);
    const [page,setPage] = useState(1);

    function renderPage() {
        switch (selected) {
            case 1:
                setPage({/* função/modulo para renderizar Pagina 1*/});
                break;

            default:
                setPage({/* função/modulo para renderizar Pagina 2*/});
                break;
        }
    }

    useEffect (() => {
        renderPage() 
    }, [selected]);




// Pages para teste (simplificado)
    
    // valor do selected recebido como variavel da função
    // como tal tem de ser passado o numero da pagina que deve ser executado para o URL atual
    const [page2,setPage2] = useState(1);

    function renderPage() {
        switch (selected) {
            case 1:
                setPage2({/* função/modulo para renderizar Pagina 1*/});
                break;

            default:
                setPage2({/* função/modulo para renderizar Pagina 2*/});
                break;
        }
    }

    useEffect (() => {
        renderPage() 
    }, [selected]);







    return(
        <div>
            {/* >>> implementar popUp <<< */}
            {/* ... */}
            <button onClick={() => setPopUp(true)}>popup</button>
            {/* ... */}
            {popUp?
                <PopUp closePopUp={() => setPopUp(false)} element={{/* elemento a ser renderizado */}}/>
                :
                null
            }
            {/* ... */}





            {/* >>> implementar a mudança de pagina simplificada <<<  */}

            {/* ... */}
            <div className="details_options">
                <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} onClick={()=>{setSelected(1)}}>Pagina 1</Button>
                <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} onClick={()=>{setSelected(2)}}>Pagina 2</Button>
            </div>
            {/* ... */}
            {page}





            {/* >>> implementar a mudança de pagina com o URL <<<  */}

            {/* ... */}
            <div className="details_options">
                <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} link={''/* link para um outro URL */}>Parques</Button>
                <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} link={''/* link para um outro URL */}>Gestores</Button>
            </div>
            {/* ... */}
            {page}
        </div>
    );
}

export default funcName;