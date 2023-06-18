import React, { useState, useEffect } from 'react';
import { Button } from '../interactive_items/Button';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar({
    setState,
    setFilter
}) {
    const [click, setClick] = useState(false);
    const handleClick = () => setClick(!click);
    const closeMobileMenu = () => setClick(false);
    
    return (
        <div className='navbar_layer'>
            <header className={"header_default"}>
                <Button buttonStyle={"logo_image"} onClick={() => {setState("all"); closeMobileMenu()}} link={'/'}><img className={'button_image'} src={"images/preto.png"} alt={""} /></Button>
                <Button buttonStyle={"navbar_perfil_image"} link={'/perfil'}> <img className={'button_image'} src={"images/perfil.png"} alt={""} /> Pessoa </Button>
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
                    {setFilter===null?
                        null
                        :
                        <div className='navbar_disappearing_icon' onClick={closeMobileMenu}>
                            <Button buttonStyle={"navbar_image_button"} onClick={setFilter}> <img className={'button_image'} src={"images/search_icon.png"} alt={""} /> </Button>
                        </div>
                    }
                    <Button buttonStyle={"navbar_image_button"} onClick={closeMobileMenu}> <img className={'button_image'} src={"images/bell_notification.png"} alt={""} /> </Button>
                    <Button buttonStyle={"navbar_button"} onClick={closeMobileMenu} link={"/login"}> Login </Button>
                </div>
            </div>
        </div>
    );
}

export default Navbar;
