import React, { useState, useEffect } from 'react';
import './Login.css';
import { Link, useNavigate } from 'react-router-dom';
import { Button } from '../../interactive_items/Button';

function Login({
}) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorState, setErrorState] = useState(false);

    const validateForm = (event) => {
        event.preventDefault()

        let login = {
            "email": email,
            "password": password
        }

        let requestOptions = {
            method: 'PUT',
            headers: { "Content-Type": "application/json", 'Access-Control-Allow-Origin' : '*'},
            body: JSON.stringify(login),
            credentials: 'include'
        }
        console.log(login);
        fetch('http://localhost:8080/apiV1/utilizadores/login', requestOptions)
            .then(res => {
                if (res.status !== 200) {
                    var errorMsg = res.headers.get("x-error");
                    if (errorMsg === null)
                        errorMsg = "Error occured";
                    setErrorState(true);
                    return(null);
                }
                else {
                    console.log("login");
                    return(res.json())
                }
            })
            .then(result => {
                console.log(result);
                let href = "/";
                if (result['nif'])
                    result['tipo_user'] =  'driver';
                else if (result['id_gestor']){
                    result['tipo_user'] =  'admin';
                    href = href + "admin";
                } else if (result['ids_admins'] === null || result['ids_admins']){
                    result['tipo_user'] =  'manager';
                    href = href + "manager";
                } else {
                    result['tipo_user'] =  'programmer';
                    href = href + "programmer";
                }
                localStorage.setItem('user',JSON.stringify(result));
                window.location.href = href;
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
                            className={errorState?'error_login':''}    
                            type='email'
                            placeholder='email'
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                        <h3> Password </h3>
                        <input
                            className={errorState?'error_login':''}    
                            type='password'
                            placeholder='password'
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    {errorState?
                        <div className='register_label_login'>
                            <label className='error_login'>*Your login credentials don’t match </label> <label className='error_login'>an account in our system</label>
                        </div>
                        :
                        null
                    }
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