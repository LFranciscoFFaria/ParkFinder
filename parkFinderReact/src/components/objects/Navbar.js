import React, { useState, useEffect } from 'react';
import { Button } from '../interactive_items/Button';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar({
    setState
}) {
    const [click, setClick] = useState(false);
    const handleClick = () => setClick(!click);
    const closeMobileMenu = () => setClick(false);
    
    return (
        <>
            <header className={"header_default"}>
                <Button buttonStyle={"logo_image"} onClick={() => {setState("all"); closeMobileMenu()}}><img className={'button_image'} src={"images/preto_no_branco.png"} alt={""} /></Button>
                <Button buttonStyle={"perfil_image"} onClick={() => setState("perfil")}> <img className={'button_image'} src={"images/perfil.png"} alt={""} /> Pessoa </Button>
            </header>
            <div className={'navbar_default'}>
                <div className='navbar_disappearing_icon' onClick={handleClick}>
                    <i className={click ? 'fas fa-times' : 'fas fa-bars'}/>
                </div>
                <div className={click ? 'navbar_side_group active' : 'navbar_side_group'}>
                    <Button buttonStyle={"navbar_button side_menu login_side_menu"} link={"/login"} onClick={closeMobileMenu}> Login </Button>
                    <Button buttonStyle={"navbar_button side_menu"}  onClick={() => {setState("airports"); closeMobileMenu()}}> Airports </Button>
                    <Button buttonStyle={"navbar_button side_menu"}  onClick={() => {setState("cities"); closeMobileMenu()}}> Cities </Button>
                    <Button buttonStyle={"navbar_button side_menu"}  onClick={() => {setState("train _stations"); closeMobileMenu()}}> Train Stations </Button>
                </div>
                <div className={"navbar_group"}>
                    <div className='navbar_disappearing_icon' onClick={handleClick}>
                        <Button buttonStyle={"navbar_image_button"} onClick={closeMobileMenu}> <img className={'button_image'} src={"images/search_icon.png"} alt={""} /> </Button>
                    </div>
                    <Button buttonStyle={"navbar_image_button"} onClick={closeMobileMenu}> <img className={'button_image'} src={"images/bell_notification.png"} alt={""} /> </Button>
                    <Button buttonStyle={"navbar_button"} onClick={closeMobileMenu} link={"/login"}> Login </Button>
                </div>
            </div>
        </>
    );
}

export default Navbar;
