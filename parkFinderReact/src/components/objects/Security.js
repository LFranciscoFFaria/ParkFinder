
import { useState } from 'react';
import { Button } from '../interactive_items/Button';
import '../objects/EditPerfil.css';
import '../objects/Security.css';




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







function Security() {
    const [newPass, setNewPass] = useState('');
    const [confPass, setConfPass] = useState('');
    const [pass, setPass] = useState('');

    const saveProfile = (event) => {
        event.preventDefault()
        console.log("Save Profile");
        console.log("newPass = " + newPass);
        console.log("confPass = " + confPass);
        console.log("pass = " + pass);
    };

    return (
        <div className='edit_perfil_form_content'>

            <h1>Segurança</h1>

            {editPerfilField('Password',null,'Password',setPass,saveProfile)}
            {editPerfilField('Nova Password',null,'Password',setNewPass,saveProfile)}
            {editPerfilField('Escreva Novamente',null,'Password',setConfPass,saveProfile)}
        </div>
    );
};


export default Security;