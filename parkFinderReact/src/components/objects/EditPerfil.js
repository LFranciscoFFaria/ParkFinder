
import { useState } from 'react';
import { Button } from '../interactive_items/Button';
import '../objects/EditPerfil.css';

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


function EditPerfil(

) {
    const [color, setColor] = useState(null);
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [nif, setNIF] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');


    const saveProfile = (event) => {
        event.preventDefault()
        console.log("Save Profile");
        console.log("color = " + color);
        console.log("email = " + email);
        console.log("name = " + name);
        console.log("nif = " + nif);
        console.log("phoneNumber = " + phoneNumber);
    };

    return (
        <div className='edit_perfil_form_content'>

            <h1>Editar Perfil</h1>
            <div className='edit_perfil_field'>
                <b> Editar Foto de Perfil </b>
                <div className='edit_perfil_input_button'>
                    <div className='edit_perfil_color'>
                        Cor de Fundo:
                        <input
                            className='edit_perfil_input_color'
                            value={color}
                            type='color'
                            onChange={(e) => setColor(e.target.value)}
                            required/>
                    </div>
                </div>
                <div className='edit_perfil_input_button'>
                    <br/>
                    <Button type='submit' buttonStyle='contrast'>Gravar</Button>
                </div>
            </div>

            {editPerfilField('Nome',null,'Nome',setName,(saveProfile))}
            {editPerfilField('Email','email','Email',setEmail,saveProfile)}
            {editPerfilField('NIF','number','NIF',setNIF,saveProfile)}
            {editPerfilField('PhoneNumber','number','987654321',setPhoneNumber,saveProfile)}
        </div>
    );
};


export default EditPerfil;