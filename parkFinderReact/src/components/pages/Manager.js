import './Manager.css'
import Filter from '../objects/Filter';
import Navbar from '../objects/Navbar';
import {ImageBlock} from '../interactive_items/ImageBlock';
import { Button } from '../interactive_items/Button';
import '../interactive_items/select.css'


function separateString(string) {
    let lines = string.split("\n");

    return(
        <ul>
            {lines.map((line, index) => (
                <li key={index}>{line}</li>
            ))}
        </ul>
    )
}

const bool=true;

function Manager({
    parques,
    admins,
    stats,
    filter,
    setState
}) {

    function Parks() {
        document.getElementById("parks").style.display = "flex";
        document.getElementById("admin").style.display = "none";
        document.getElementById("stats").style.display = "none";


        document.getElementById("parkButton").classList.add("desc_button_hover");
        document.getElementById("adminButton").classList.remove("desc_button_hover");
        document.getElementById("statButton").classList.remove("desc_button_hover");

    }

    function Admins() {
        document.getElementById("characteristics").style.display = "none";
        document.getElementById("description").style.display = "flex";
        document.getElementById("stats").style.display = "none";

        document.getElementById("charButton").classList.remove("desc_button_hover");
        document.getElementById("descrButton").classList.add("desc_button_hover");~
        document.getElementById("statButton").classList.remove("desc_button_hover");

    }

    function Stats() {
        document.getElementById("characteristics").style.display = "none";
        document.getElementById("description").style.display = "none";
        document.getElementById("stats").style.display = "flex";

        document.getElementById("charButton").classList.remove("desc_button_hover");
        document.getElementById("descrButton").classList.remove("desc_button_hover");
        document.getElementById("statButton").classList.add("desc_button_hover");

    }
    
    return (
        <div className='front_page_staff'>
            <NavbarStaff setState={setState} setFilter={null}/>
            <div className="whitebox_staff">

                    <div className="options_desc">
                        <button className="desc_button desc_button_hover" id="parkButton"
                            onClick={()=>{Parks()}}>Parques</button>
                        <button className="desc_button" id="adminButton"
                            onClick={()=>{Admins()}}>Administradores</button>
                        <button className="desc_button" id="statButton"
                            onClick={()=>{Stats()}}>Estatísticas</button>
                    </div>

                <div className="desc_desc" id="parks">
                    <div className='parks_info_display'>
                        <div className='parks_header'>
                            <h1>[Local De Pesquisa]</h1>
                        </div>
                        {parques.map(parque => 
                            <ManagerCompressedParkInfo key={parque.id} parque={parque} setIdParque={setIdParque}/>
                        )}
                        <div className='pageNumb'>
                            <button className='page_button'> {'<<'} </button>
                            <button className='page_button'> 1 </button>
                            <button className='page_button'> 2 </button>
                            <button className='page_button'> 3  </button>
                            <button className='page_button'> {'>>'} </button>
                        </div>
                    </div>  
                </div>

                <div className="desc_desc_none" id="admin">

                </div>

                <div className="desc_desc_none" id="stats">

                </div>

                <div className={filter? 'parks_filter_display active': 'parks_filter_display'}>
                    <Filter dates={true}/>
                </div>
            </div>
        </div>
    );
}

export default Manager;