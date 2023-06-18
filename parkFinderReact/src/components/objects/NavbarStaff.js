import React, { useState, useEffect } from 'react';
import { Button } from '../interactive_items/Button';
import { Link } from 'react-router-dom';
import './Navbar.css';

function NavbarStaff({
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
                <Button link={'/login'}> Sair </Button>
            </header>
        </div>
    );
}

export default NavbarStaff;
