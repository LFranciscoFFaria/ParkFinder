import { NavbarStaff } from './Navbar';
import { Button } from '../interactive_items/Button';
import '../pages/condutor/Details.css'
import { useEffect, useState } from 'react';
import './EditPark.css';
import './Security.css';
import './Contacts.css';




function CreatePark({
    selected,
}) {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [latitude, setLatitude] = useState(0);
    const [longitude, setLongitude] = useState(0);


    const saveProfile = (event) => {
        event.preventDefault()
        console.log("Save Profile");
        console.log("email = " + email);
        console.log("name = " + name);
        console.log("latitude = " + latitude);
        console.log("longitude = " + longitude);
    };

    return (
        <div className='staff_bg'>
            <div className='staff_whitebox'>
                <NavbarStaff link_logo={'/programmer'}/>
                <div className='details_options'>
                    <Button buttonStyle={"ditails_button"+(selected===1? ' ditails_button_selected':'')} link={'/programmer'}>Parques</Button>
                    <Button buttonStyle={"ditails_button"+(selected===2? ' ditails_button_selected':'')} link={'/programmer/managers'}>Gestores</Button>
                </div>
                <div className="contact_display">
                    <div className="contact_header">
                        <h1>Criar Parque</h1>
                    </div>

                    <div className='edit_park_container'>
                        <form onSubmit={saveProfile} className='edit_park_fields_container'>
                            <div className='security_field'>
                                <b className='edit_park_title'> {'Nome'} </b>
                                <input className='edit_perfil_input' placeholder={'Nome'} value={name} onChange={(e) => setName(e.target.value)} required/>
                            </div>
                            <div className='security_field'>
                                <b className='edit_park_title'> {'Morada'} </b>
                                <input className='edit_perfil_input' placeholder={'Email'} type={'email'} value={email} onChange={(e) => setEmail(e.target.value)} required/>
                            </div>
                            <div className='security_field'>
                                <b className='edit_park_title'> {'Localização do parque:'} </b>
                                <div className='edit_park_schedule_container'>
                                    <div className='edit_park_parking_spaces'>
                                        <b> {'Latitude:'} </b>
                                        <input className='edit_park_input' placeholder={'Latitude'} type={'number'} value={latitude} onChange={(e) => setLatitude(e.target.value)} required/>
                                    </div>
                                    <div className='edit_park_parking_spaces'>
                                        <b> {'Longitude:'} </b>
                                        <input className='edit_park_input' placeholder={'Longitude'} type={'number'} value={longitude} onChange={(e) => setLongitude(e.target.value)} required/>
                                    </div>
                                </div>
                            </div>
                            <div className='security_input_button'>
                                <br/>
                                <Button type='submit' buttonStyle='contrast'>Gravar</Button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CreatePark;



