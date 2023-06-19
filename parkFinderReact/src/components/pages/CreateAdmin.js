import NavbarStaff from '../objects/NavbarStaff';
import {ImageBlock} from '../interactive_items/ImageBlock';
import { Button } from '../interactive_items/Button';
import '../interactive_items/select.css'
import { useEffect, useState } from 'react';

function editPerfilField (
    name,
    type,
    placeholder,
    setFunc,
    saveProfile,
    ) {
    return(
        <form onSubmit={saveProfile}>
            <div className='edit_perfil_field'>
                <b> {name} </b>
                <div className='edit_perfil_input_button'>
                    <input
                        className='edit_perfil_input'
                        placeholder={placeholder}
                        type={type}
                        onChange={(e) => setFunc(e.target.value)}
                        required/>
                    <Button type='submit' buttonStyle='contrast'>Gravar</Button>
                </div>
            </div>
        </form>
    )
}

function CreateAdmin({
}) {
    const [email, setAdminEmail] = useState('');
    const [name, setAdminName] = useState('');
    const [contact, setAdminContact] = useState('');
    const [parks, setAdminParks] = useState('');
    const [password, setAdminPassword] = useState('');


    const saveAdmin = (event) => {
        event.preventDefault()
        console.log("Save Admin");
        console.log("name = " + name);
        console.log("email = " + email);
        console.log("contact = " + contact);
        console.log("parks = " + parks);
        console.log("password = " + password);
    };
    
    return(
        <div className='staff_bg'>
            <div className='staff_whitebox'>
                <NavbarStaff link_logo={'/manager'}/>
                <div className='edit_perfil_form_content'>
                {editPerfilField('Nome',null,'Nome',setAdminName,(saveAdmin))}
                {editPerfilField('Email','email','email',setAdminEmail,saveAdmin)}
                {editPerfilField('Contact','contact','contact',setAdminContact,saveAdmin)}
                {editPerfilField('Parks','parks','parks',setAdminParks,saveAdmin)}
                {editPerfilField('Password','password','password',setAdminPassword,saveAdmin)}

                </div>
            </div>
        </div>
    );
}

export default CreateAdmin;