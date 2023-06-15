import React, { useState, useEffect } from 'react';
import './Login.css';
import { Link, useNavigate } from 'react-router-dom';
import { Button } from '../interactive_items/Button';

function Login({
    
}) {
    return (
        <div className="bg_color">
            <div className="whitebox">
                <form className="form_login">
                    <div className="form_title_login">
                        <h1>[Logo]</h1>
                        <h1> Login </h1>
                    </div>
                    <div className="form_content_login">
                        <h3> Email </h3>
                        <input placeholder="email"/>
                        <h3> Password </h3>
                        <input placeholder="password"/>
                    </div>
                    <div className="register_label_login">
                        <label>Don’t have an account?</label>
                        <Link to='/register' className='link'>
                            Sign up
                        </Link>
                    </div>
                    
                    <div className="buttons_login">
                        <Button buttonStyle={"contrast"} link={"/"}>Login</Button>
                    </div>
                </form>
                <div className="image">
                    <h3> Imagem </h3>
                </div>
            </div>
        </div>
    );
}

export default Login;