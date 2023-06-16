import './Parks.css';
import './Perfil.css';
import './Details.css';
import Parks from './Parks.js';
import Perfil from './Perfil.js';
import Details from './Details.js';
import Navbar from '../objects/Navbar'
import { useEffect, useState } from 'react';
import Filter from './Filter.js';

function FrontPage({
    
}) {
    const [state, setState] = useState("");
    const [elementToRender, setElementToRender] = useState(null);



    function changeElement() {
        console.log("state = " + state);
        switch (state) {
            case 'perfil':
              setElementToRender(<Perfil />);
              break;
            case 'details':
              setElementToRender(<Details />);
              break;
            default:
              setElementToRender(<Parks />);
        }
    }

    useEffect(() => {
        changeElement();
    }, [state]);
    
    return (
        <>
            <Navbar setState={setState}/>
            {elementToRender}
            {elementToRender}
            <Filter/>
        </>
    );
}

export default FrontPage;