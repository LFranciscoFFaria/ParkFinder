import { NavbarStaff } from './Navbar';
import { Button } from '../interactive_items/Button';
import '../pages/driver/Details.css'
import { useEffect, useState } from 'react';
import './EditPark.css';
import './Security.css';
import './Contacts.css';




function CreateGestor({
    selected,
}) {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState(0);
    const [nif, setNIF] = useState(0);


    const saveProfile = (event) => {
        event.preventDefault()
        console.log("Save Profile");
        console.log("email = " + email);
        console.log("name = " + name);
        console.log("nif = " + nif);
        console.log("phoneNumber = " + phoneNumber);
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
                        <h1>Criar Gestor</h1>
                    </div>

                    <div className='edit_park_container'>
                        <form onSubmit={saveProfile} className='edit_park_fields_container'>
                            <div className='security_field'>
                                <b> {'Nome'} </b>
                                <input className='edit_perfil_input' placeholder={'Nome'} value={name} onChange={(e) => setName(e.target.value)} required/>
                            </div>
                            <div className='security_field'>
                                <b> {'Email'} </b>
                                <input className='edit_perfil_input' placeholder={'Email'} type={'email'} value={email} onChange={(e) => setEmail(e.target.value)} required/>
                            </div>
                            <div className='security_field'>
                                <b> {'NIF'} </b>
                                <input className='edit_perfil_input' placeholder={'NIF'} type={'number'} value={nif} onChange={(e) => setNIF(e.target.value)} required/>
                            </div>
                            <div className='security_field'>
                                <b> {'Número de Telemovel'} </b>
                                <input className='edit_perfil_input' placeholder={'987654321'} type={'number'} value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} required/>
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

export default CreateGestor;



