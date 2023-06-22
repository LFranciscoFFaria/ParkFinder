import React, { useState, useEffect } from 'react';
import './Login.css';
import { Link, useNavigate } from 'react-router-dom';
import { Button } from '../../interactive_items/Button';

function Login({
}) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const validateForm = (event) => {
        event.preventDefault()

        let login = {
            "email": email,
            "password": password
        }

        let requestOptions = {
            method: 'PUT',
            headers: { "Access-Control-Allow-Origin": "*" ,  "Content-Type": "application/json" },
            body: JSON.stringify(login),
            credencials: 'include'
        }
        console.log(login);
        fetch('http://localhost:8080/apiV1/utilizadores/login', requestOptions)
            .then(res => {
                if (res.status !== 200) {
                    var errorMsg = res.headers.get("x-error");
                    if (errorMsg == null)
                        errorMsg = "Error occured";
                    alert(errorMsg);
                    return(null)
                }
                else {
                    console.log("login");
                    return(res.json())
                }
            })
            .then(result => {console.log(result);
                localStorage.setItem('userId',result['id']);
                if (result['nif'])
                    localStorage.setItem('userStates', 'condutor');
                else if (result['id_gestor'])
                    localStorage.setItem('userStates', 'admin');
                else if (result['ids_admins'])
                    localStorage.setItem('userStates', 'gestor');
                else
                    localStorage.setItem('userStates', 'programador');
                window.location.href = '/';
            })
    };

    return (
        <div className='bg_color'>
            <div className='whitebox'>
                <form className='form_login' onSubmit={validateForm}>
                    <div className='form_title_login'>
                        <img className={'button_image'} src={"images/preto_no_branco.png"} alt={""} />
                        <h1> Login </h1>
                    </div>
                    <div className='form_content_login'>
                        <h3> Email </h3>
                        <input 
                            type='email'
                            placeholder='email'
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                        <h3> Password </h3>
                        <input
                            type='password'
                            placeholder='password'
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <div className='register_label_login'>
                        <label>Don’t have an account?</label>
                        <Link to='/register' className='link'>
                            Sign up
                        </Link>
                    </div>
                    
                    <div className='buttons_login'>
                        <Button buttonStyle={'contrast'} type='submit'>Login</Button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Login;