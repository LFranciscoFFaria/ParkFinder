
import { NavbarStaff } from './Navbar';
import { ImageBlock } from '../interactive_items/ImageBlock';
import { Button } from '../interactive_items/Button';
import '../interactive_items/select.css'
import { useEffect, useState } from 'react';
import './CreateAdmin.css';
import Checkbox from '../interactive_items/Checkbox';
import { editPerfilField } from './EditPerfil';





function CreateAdmin({
}) {
    const [email, setAdminEmail] = useState('');
    const [name, setAdminName] = useState('');
    const [contact, setAdminContact] = useState('');
    const [parks, setAdminParks] = useState('');
    const [password, setAdminPassword] = useState('');

    const [parques,setParques] = useState(pk);

    const [parkbuff, setAdminParkbuff] = useState('');

    let parklist = [];

    const change_on_list = (event) => {
        let index = parklist.indexOf(parkbuff);
        if(parklist.includes(parkbuff)==true){
            parklist = parklist.slice(0,index).concat(parklist.slice(index+1));
        }
        else{
            parklist.concat([parkbuff]);
        }
        setAdminParks(parklist);
        console.log(parkbuff);
    };

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

                {editPerfilField('Nome',null,'Nome',setAdminName,saveAdmin)}
                {editPerfilField('Email','email','email',setAdminEmail,saveAdmin)}
                {editPerfilField('Contact','contact','contact',setAdminContact,saveAdmin)}
                {editPerfilField('Password','password','password',setAdminPassword,saveAdmin)}

                <form onSubmit={saveAdmin}>
                    <div className='edit_perfil_field'>
                        <b> {name} </b>
                        <div className='edit_perfil_input_button'>
                            <div className='admin_grid_container' name='Criterion' id='criterion' defaultValue={"default"}>
                                {parques.map((parque, index) => (
                                    <label className="checkbox_label" key={index}>
                                        <input type="checkbox" className="checkbox"  value={parque['id']} 
                                            onChange={(e) => {setAdminParkbuff(e.target.value);change_on_list(e.target.value)}}/>
                                        {parque['nome']}
                                    </label>
                                ))}
                            </div>
                        </div>
                            <Button type='submit' buttonStyle='contrast'>Gravar</Button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default CreateAdmin;