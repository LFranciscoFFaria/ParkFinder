import React, { useState, useEffect } from 'react';
import './Register.css';
import './Login.css';
import { Link, useNavigate } from 'react-router-dom';
import { Button } from '../interactive_items/Button';

function Register({
    
}) {
    const [sex, setSex] = useState(true);
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [nif, setNIF] = useState('');
    const [password, setPassword] = useState('');
    const [cpass, setCPass] = useState('');

    const validateForm = (event) => {
        event.preventDefault()
        console.log("validate");
        window.location.href = '/';
    };


    return (
        <div className='bg_color'>
            <div className='whitebox'>
                <form className='form_login' onSubmit={validateForm}>
                    <div className='form_title_register'>
                        <div className='form_title_image_register'>
                            <img className={'button_image'} src={'images/preto_no_branco.png'} alt={''} />
                        </div>
                        <div className='form_title_desc_register'>
                            <label> Register </label>
                        </div>
                    </div>
                    <div className='form_content_login'>
                        <div className='form_sex_register'>
                            <b> Sex: </b>
                            <div className='form_sex_buttons_register'>
                                <Button type='button' buttonStyle={!sex?'sex_button':'sex_button_selected'} onClick={() => setSex(true)} novalidate>Mr.</Button>
                                <Button type='button' buttonStyle={sex?'sex_button':'sex_button_selected'} onClick={() => setSex(false)} novalidate>Mrs.</Button>
                            </div>
                        </div>
                        <b> Name </b>
                        <input
                            className='input_register'
                            placeholder='Name'
                            onChange={(e) => setName(e.target.value)}
                            required/>
                        <b> Email </b>
                        <input
                            className='input_register'
                            type='email'
                            placeholder='Email'
                            onChange={(e) => setEmail(e.target.value)}
                            required/>
                        <b> NIF </b>
                        <input
                            className='input_register'
                            type='number'
                            placeholder='NIF'
                            onChange={(e) => setNIF(e.target.value)}
                            required/>
                        <b> Password </b>
                        <input
                            className='input_register'
                            type='password'
                            placeholder='Password'
                            onChange={(e) => setPassword(e.target.value)}
                            required/>
                        <b> Confirm Password </b>
                        <input
                            className='input_register'
                            type='password'
                            placeholder='Password'
                            onChange={(e) => setCPass(e.target.value)}
                            required/>
                    </div>
                    <div className='register_label_login'>
                        <label>By creating an account, you agree </label>
                        <div>
                            <label> to ParkFinder's </label><label className='Link'>Conditions</label>
                        </div>
                        <div>
                            <label className='Link'> of Use</label>
                            <label> and </label><label className='Link'>Privacy Notice.</label>
                        </div>
                    </div>
                    <div className='buttons_login'>
                        <Button buttonStyle={'contrast'} type='submit'>Submit</Button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Register;