import React, { useState, useEffect } from 'react';
import { Button } from '../interactive_items/Button';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar({
    
}) {
    
    return (
        <div>
            <header className={"header_default"}>
                <Button buttonStyle={"logo_image"}><img className={'bell_image'} src={"../images/preto-no-branco.png"} alt={""} /></Button>
                <Button buttonStyle={"perfil_image"}> <img className={'bell_image'} src={"../images/perfil.png"} alt={""} /> </Button>
            </header>
            <nav className={"navbar_default"}>
                <div className={"navbar_group"}>
                    <Button buttonStyle={"navbar_button"}> Airports </Button>
                    <Button buttonStyle={"navbar_button"}> Cities </Button>
                    <Button buttonStyle={"navbar_button"}> Train Stations </Button>
                </div>
                <div className={"navbar_group"}>
                    <Button buttonStyle={"navbar_image_button"}> <img className={'bell_image'} src={"../images/search_icon.png"} alt={""} /> </Button>
                    <Button buttonStyle={"navbar_image_button"}> <img className={'bell_image'} src={"../images/bell_notification.png"} alt={""} /> </Button>
                    <Button buttonStyle={"navbar_button"}> Login </Button>
                </div>
            </nav>
        </div>
    );
}

export default Navbar;
