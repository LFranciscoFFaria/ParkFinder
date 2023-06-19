import React, { useState, useEffect } from 'react';
import { Button } from '../interactive_items/Button';
import { Link } from 'react-router-dom';
import './NavbarStaff.css';

function NavbarStaff({
    link_logo
}) {
    return (
        <div className='navbar_staff'>
            <Button buttonStyle={"logo_image"} link={link_logo}><img className={'button_image'} src={"images/preto.png"} alt={""} /></Button>
            <Button buttonStyle={'default navbar_staff_login_button'} link={'/login'}> Login </Button>
        </div>
    );
}

export default NavbarStaff;
