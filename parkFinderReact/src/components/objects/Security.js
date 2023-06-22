
import { useState } from 'react';
import { Button } from '../interactive_items/Button';
import './EditPerfil.css';
import './Security.css';
import '../interactive_items/select.css';




function Security() {
    const [newPass, setNewPass] = useState('');
    const [confPass, setConfPass] = useState('');
    const [pass, setPass] = useState('');
    const [error, setError] = useState(0);

    const saveProfile = (event) => {
        event.preventDefault()
        console.log("Save Profile");
        console.log("pass = " + pass);
        console.log("newPass = " + newPass);
        console.log("confPass = " + confPass);

        if(newPass === confPass){
            setError(0);
        } else {
            setError(2);
        }
    };

    return (
        <div className='edit_perfil_form_content'>

            <h1>Segurança</h1>

            <form onSubmit={saveProfile}>
                <div className='security_field'>
                    <b> {'Password'} </b>
                    <input
                        className={'security_input' + (error===1? ' security_input_error':'')}
                        placeholder='Password'
                        type='password'
                        onChange={(e) => setPass(e.target.value)}
                        required/>
                    <label className={error===1? 'scurity_error':'disabled_selected'}>*password incorreta</label>
                </div>
                <div className='security_field'>
                    <b> {'Nova Password'} </b>
                    <input
                        className='security_input'
                        placeholder='Password'
                        type='password'
                        onChange={(e) => setNewPass(e.target.value)}
                        required/>
                </div>
                <div className='security_field'>
                    <b> {'Escreva Novamente'} </b>
                    <input
                        className={'security_input' + (error===2? ' security_input_error':'')}
                        placeholder='Password'
                        type='password'
                        onChange={(e) => setConfPass(e.target.value)}
                        required/>
                    <label className={error===2? 'scurity_error':'disabled_selected'}>*password são diferentes</label>
                </div>
                <div className='security_input_button'>
                    <br/>
                    <Button type='submit' buttonStyle='contrast'>Gravar</Button>
                </div>
            </form>
        </div>
    );
};


export default Security;