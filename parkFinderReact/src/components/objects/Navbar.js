import { useState } from 'react';
import { Button } from '../interactive_items/Button';
import './Navbar.css';
import './NavbarStaff.css';
import PopUp from '../interactive_items/PopUp';
import { QRCodeSVG } from 'qrcode.react'




export function Navbar({
    setState,
    setFilter,
    userID
}) {
    const [click, setClick] = useState(false);
    const [popUp, setPopUp] = useState(false);
    const [element, setElement] = useState(
        <>
            <QRCodeSVG value={'userID:' + userID} className="navbar_qrcode"/>
            <h3>Código: {'USERID:' + userID}</h3>
        </>);


    const handleClick = () => setClick(!click);
    const closeMobileMenu = () => setClick(false);

    return (
        <div className='navbar_layer'>
            <header className={"header_default"}>
                <Button buttonStyle={"logo_image"} onClick={() => {setState("all"); closeMobileMenu()}} link={'/'}><img className={'button_image'} src={"images/preto.png"} alt={""} /></Button>
                <Button buttonStyle={"navbar_perfil_image"} link={'/perfil'}> <img className={'button_image'} src={"images/perfil_black.png"} alt={""} /> Pessoa </Button>
            </header>

            <div className={'navbar_default'}>
                <div className='navbar_disappearing_icon' onClick={handleClick}>
                    <i className={click ? 'fas fa-times' : 'fas fa-bars'}/>
                </div>
                <div className={click ? 'navbar_side_group active' : 'navbar_side_group'}>
                    <Button buttonStyle={"navbar_button side_menu login_side_menu"} link={"/login"} onClick={closeMobileMenu}> Login </Button>
                    <Button buttonStyle={"navbar_button side_menu"}  onClick={() => {setState("airports"); closeMobileMenu()}} link={'/'}> Aeroportos </Button>
                    <Button buttonStyle={"navbar_button side_menu"}  onClick={() => {setState("cities"); closeMobileMenu()}} link={'/'}> Cidades </Button>
                    <Button buttonStyle={"navbar_button side_menu"}  onClick={() => {setState("train _stations"); closeMobileMenu()}} link={'/'}> Estações Ferroviárias</Button>
                </div>
                <div className={"navbar_group"}>
                    {setFilter===null?
                        null
                        :
                        <div className='navbar_disappearing_icon' onClick={closeMobileMenu}>
                            <Button buttonStyle={"navbar_image_button"} onClick={setFilter}> <img className={'button_image'} src={"images/search_icon.png"} alt={""} /> </Button>
                        </div>
                    }
                    {userID?
                        <Button buttonStyle={"navbar_image_button"} onClick={() => {closeMobileMenu(); setPopUp(true)}}> <img className={'button_image'} src={"images/qrcode.png"} alt={""} /> </Button>
                        :
                        null
                    }
                    <Button buttonStyle={"navbar_image_button"} onClick={closeMobileMenu}> <img className={'button_image'} src={"images/bell_notification.png"} alt={""} /> </Button>
                    <Button buttonStyle={"navbar_button"} onClick={closeMobileMenu} link={"/login"}> Login </Button>
                </div>
            </div>

            {popUp && userID?
                <PopUp closePopUp={() => setPopUp(false)} text='QR Code' element={element} />
                :
                null
            }
        </div>
    );
}





export function NavbarStaff({
    link_logo
}) {

    function getPath() {
        return <img className='icon' src={'images/preto.png'} alt={''} />
    }

    return (
        <div className='navbar_staff'>
            <Button buttonStyle={"logo_image"} link={link_logo}><img className={'button_image'} src={getPath()} alt={""} /></Button>
            <Button buttonStyle={'default navbar_staff_login_button'} link={'/login'}> Logoff </Button>
        </div>
    );
}

