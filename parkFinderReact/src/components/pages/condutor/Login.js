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
        console.log("validate");
        window.location.href = '/manager';
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