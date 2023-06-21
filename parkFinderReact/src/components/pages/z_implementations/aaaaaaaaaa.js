
import PopUp from '../../interactive_items/PopUp';
import { Button } from '../../interactive_items/Button';
import '../pages/Details.css'
import { useEffect, useState } from 'react';


















function funcName({

}) {

// PopUp

    const [popUp, setPopUp] = useState(false);

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
        </div>
    );
}

















function funcName({
}) {

// Mudança de pagina simples

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

    return(
        <div>
            {/* >>> implementar a mudança de pagina simplificada <<<  */}

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






















function funcName({
}) {
    
// Mudança de pagina com URL diferente

    // iniciar a variavel especifica (parque)

    const [parque,setParque] = useState(parques[1]);

    useEffect(() => {
        let parque_id = localStorage.getItem('parqueId');
        if (parque_id===null){
            parque_id = sessionStorage.getItem('parqueId');
        } else {
            localStorage.removeItem('parqueId');
            sessionStorage.setItem('parqueId', parque_id);
        }
        setParque(parques[parque_id]);
        console.log(parque_id);
        console.log(parque);
    }, []);




    return(
        <div>
            {/* >>> implementar o carregamento da variavel especifica (parqueId) <<<  */}

            {/* ... */}
            <Button buttonStyle="page_button see_details_button" onClick={() => localStorage.setItem("parqueId", parque["id"])} link={'/details'}>Ver detalhes</Button>
            {/* ... */}
            {page}
        </div>
    );
}

export default funcName;