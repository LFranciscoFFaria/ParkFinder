import React, { useState, useEffect } from 'react';
import './Register.css';
import './Login.css';
import { Link, useNavigate } from 'react-router-dom';
import { Button } from '../interactive_items/Button';

function Register({
    
}) {
    return (
        <div className="bg_color">
            <div className="whitebox">
                <form className="form_login">
                    <div className="form_title_login">
                        <h1>[Logo]</h1>
                        <h1> Register </h1>
                    </div>
                    <div className="form_content_login">
                        <div className="form_sex_register">
                            <b> Sex: </b>
                            <div className="form_sex_buttons_register">
                                <Button buttonStyle={"sex_button"}>Mr.</Button>
                                <Button buttonStyle={"sex_button"}>Mrs.</Button>
                            </div>
                        </div>
                        <b> Name </b>
                        <input className="input_registo" placeholder="Name"/>
                        <b> Email </b>
                        <input className="input_registo" placeholder="Email"/>
                        <b> NIF </b>
                        <input className="input_registo" placeholder="NIF"/>
                        <b> Password </b>
                        <input className="input_registo" placeholder="Password"/>
                        <b> Confirm Password </b>
                        <input className="input_registo" placeholder="Password"/>
                    </div>
                    <div className="register_label_login">
                        <label>By creating an account, you agree </label>
                        <div>
                            <label> to ParkFinder's </label><label className="Link">Conditions</label>
                        </div>
                        <div>
                            <label className="Link"> of Use</label>
                            <label> and </label><label className="Link">Privacy Notice.</label>
                        </div>
                    </div>
                    <div className="buttons_login">
                        <Button buttonStyle={"contrast"} link={"/"}>Submit</Button>
                    </div>
                </form>
                <div className="login_image">
                    <h3> Imagem </h3>
                </div>
            </div>
        </div>
    );
}

export default Register;