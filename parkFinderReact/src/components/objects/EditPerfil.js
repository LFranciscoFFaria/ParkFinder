
import { useEffect, useState } from 'react';
import { Button } from '../interactive_items/Button';
import './EditPerfil.css';
import { ImageBlock } from '../interactive_items/ImageBlock';

export function editPerfilField (
    name,
    type,
    placeholder,
    setFunc,
    saveProfile,
    ) {
    return(
        <form onSubmit={saveProfile}>
            <div className='security_field'>
                <b> {name} </b>
                <div className='edit_perfil_input_button'>
                    <input
                        className='edit_perfil_input'
                        placeholder={placeholder}
                        type={type}
                        onChange={(e) => setFunc(e.target.value)}
                        required/>
                </div>
            </div>
        </form>
    )
}


export function EditPerfil({
    user,
}) {
    const [color, setColor] = useState(null);
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState(0);
    const [nif, setNIF] = useState(0);


    useEffect(() => {
        setName(user['nome']);
        setEmail(user['email']);
        setPhoneNumber(user['telemovel']);
        setNIF(user['nif']);
    },[user]);




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
            <div className='security_field '>
                <b> Editar Foto de Perfil </b>
                <div className='edit_perfil_input_image'>
                    <div className='edit_perfil_color'>
                        <label>Cor de Fundo:</label>
                        <input
                            className='edit_perfil_input_color'
                            value={color}
                            type='color'
                            onChange={(e) => setColor(e.target.value)}
                            required/>
                        
                    </div>
                    <ImageBlock imageLink={'images/perfil_black.png'} no_scale={true} imageSize='image_edit'/>
                </div>
            </div>

            <form onSubmit={saveProfile}>
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
                <div className='edit_perfil_input_button'>
                    <br/>
                    <Button type='submit' buttonStyle='contrast'>Gravar</Button>
                </div>
            </form>

        </div>
    );
};
