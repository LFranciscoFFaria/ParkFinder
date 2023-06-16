import '../objects/Parks.css';
import '../objects/Perfil.css';
import '../objects/Details.css';
import Parks from '../objects/Parks.js';
import Perfil from '../objects/Perfil.js';
import Details from '../objects/Details.js';
import Navbar from '../objects/Navbar'
import { useEffect, useState } from 'react';

function FrontPage({
    
}) {
    const [state, setState] = useState("");
    let elementToRender;


    function changeElement() {
        console.log("state = " + state);
        switch (state) {
            case 'perfil':
                elementToRender = <Perfil />;
                break;
            case 'details':
                elementToRender = <Details />;
                break;
            default:
                elementToRender = <Parks />;
        }
    }

    useEffect(() => {
        changeElement();
    }, [state]);
    
    return (
        <>
            <Navbar setState={setState}/>
            {elementToRender}
        </>
    );
}

export default FrontPage;