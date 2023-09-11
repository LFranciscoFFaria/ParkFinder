import { useState } from 'react';
import { Button } from '../interactive_items/Button';
import './Navbar.css';
import './NavbarStaff.css';
import PopUp from '../interactive_items/PopUp';
import { QRCodeSVG } from 'qrcode.react'




export function Navbar({
    setParkTypeFilter,
    setDisplayFilter
}) {
    var user = JSON.parse(localStorage.getItem('user'));
    const [userID, setUserID] = useState(user ? user["id"] : -1);

    console.log("user:")
    console.log(user)

    const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
    const [popUp, setPopUp] = useState(false);
    const element = 
        <>
            <QRCodeSVG value={'userID:' + userID} className="navbar_qrcode"/>
            <h3>Código: {'USERID:' + userID}</h3>
        </>;


    const openMobileMenu = () => setIsMobileMenuOpen(!isMobileMenuOpen);
    const closeMobileMenu = () => setIsMobileMenuOpen(false);

    function closeSession(){
        localStorage.removeItem('user');
        setUserID(-1);
    }

    return (
        <div className='navbar_layer'>
            <header className={"header_default"}>
                <Button buttonStyle={"logo_image"} onClick={() => {setParkTypeFilter("all"); closeMobileMenu()}} link={'/'}><img className={'button_image'} src={"./images/preto.png"} alt={""} /></Button>
                {user ? <Button buttonStyle={"navbar_perfil_image"} link={'/perfil'}> <img className={'button_image'} src={"images/perfil_black.png"} alt={""} /> {user["nome"]} </Button> : null}
            </header>

            <div className={'navbar_default'}>
                <div className='navbar_disappearing_icon' onClick={openMobileMenu}>
                    <i className={isMobileMenuOpen ? 'fas fa-times' : 'fas fa-bars'}/>
                </div>
                <div className={isMobileMenuOpen ? 'navbar_side_group active' : 'navbar_side_group'}>
                    <Button buttonStyle={"navbar_button side_menu login_side_menu"} link={"/login"} onClick={closeMobileMenu}> Login </Button>
                    <Button buttonStyle={"navbar_button side_menu"}  onClick={() => {setParkTypeFilter("airports"); closeMobileMenu()}} link={'/'}> Aeroportos </Button>
                    <Button buttonStyle={"navbar_button side_menu"}  onClick={() => {setParkTypeFilter("cities"); closeMobileMenu()}} link={'/'}> Cidades </Button>
                    <Button buttonStyle={"navbar_button side_menu"}  onClick={() => {setParkTypeFilter("train _stations"); closeMobileMenu()}} link={'/'}> Estações Ferroviárias</Button>
                </div>
                <div className={"navbar_group"}>
                    {setDisplayFilter===null?
                        null
                        :
                        <div className='navbar_disappearing_icon' onClick={closeMobileMenu}>
                            <Button buttonStyle={"navbar_image_button"} onClick={setDisplayFilter}> <img className={'button_image'} src={"images/search_icon.png"} alt={""} /> </Button>
                        </div>
                    }
                    {userID >= 0?
                        <>
                            <Button buttonStyle={"navbar_image_button"} onClick={() => {closeMobileMenu(); setPopUp(true)}}> <img className={'button_image'} src={"images/qrcode.png"} alt={""} /> </Button>
                            <Button buttonStyle={"navbar_image_button"} onClick={closeMobileMenu}> <img className={'button_image'} src={"images/bell_notification.png"} alt={""} /> </Button>
                            <Button buttonStyle={"navbar_button"} onClick={closeSession} link={"/"}> Sair </Button>
                        </>
                        :
                        <Button buttonStyle={"navbar_button"} onClick={closeMobileMenu} link={"/login"}> Login </Button>
                    }
                </div>
            </div>

            {popUp && userID >= 0?
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
        var path = window.location.href.split('/');
        let difDirectory = path.length;
    
        if (difDirectory === 6)
          return <img className='button_image' src={'../../images/preto.png'} alt={''} />
        else if (difDirectory === 5)
            return <img className='button_image' src={'../images/preto.png'} alt={''} />
        else
          return <img className='button_image' src={'./images/preto.png'} alt={''} />
    }

    return (
        <div className='navbar_staff'>
            <Button buttonStyle={"logo_image"} link={link_logo}>{getPath()}</Button>
            <Button buttonStyle={'default navbar_staff_login_button'} link={'/login'}> Logoff </Button>
        </div>
    );
}

