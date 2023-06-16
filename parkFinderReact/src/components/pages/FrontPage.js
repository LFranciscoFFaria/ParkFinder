import './FrontPage.css';
import Parks from './Parks.js';
import Perfil from './Perfil.js';
import Details from './Details.js';
import Navbar from '../objects/Navbar.js'
import { useEffect, useState } from 'react';

function FrontPage({
    
}) {
    const [state, setState] = useState('');
    const [filter, setFilter] = useState(false);
    const [elementToRender, setElementToRender] = useState(null);


    function changeElement() {
        console.log('state = ' + state);
        switch (state) {
            case 'perfil':
              setElementToRender(<Perfil />);
              break;
            case 'details':
              setElementToRender(<Details />);
              break;
            default:
              setElementToRender(<Parks filter={filter}/>);
        }
    }


    useEffect(() => {
        changeElement();
    }, [state,filter]);


    return (
        <div className='front_page'>
            <Navbar setState={setState} setFilter={() => setFilter(!filter)}/>
            {elementToRender}
        </div>
    );
}

export default FrontPage;