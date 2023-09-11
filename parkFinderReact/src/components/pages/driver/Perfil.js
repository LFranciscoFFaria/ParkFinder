import { ImageBlock } from '../../interactive_items/ImageBlock';
import { Navbar } from '../../objects/Navbar';
import { EditPerfil } from '../../objects/EditPerfil.js';
import Security from '../../objects/Security.js';
import PaymentHistory from '../../objects/PaymentHistory.js';
import { Reservation, ReservationHistory } from '../../objects/ReservationHistory.js';
import '../../interactive_items/select.css'
import './Perfil.css'
import { useEffect, useState } from 'react';


function Perfil({
    setParkTypeFilter,
    reservations,
}) {
    const [optionSelected, setOptionSelected] = useState(-1);
    const [optionDisplayed, setOptionDisplayed] = useState(null);
    const [user, setUser] = useState(JSON.parse(localStorage.getItem('user')));
    
    function displayPerfilOptions() {
        switch (optionSelected) {
            case 1:
                setOptionDisplayed(<EditPerfil user={user} setUser={setUser}/>);
                break;
            case 2:
                setOptionDisplayed(<Security user={user} setUser={setUser}/>);
            break;
            case 3:
                setOptionDisplayed(<Reservation reservations={reservations}/>);
                break;
            case 4:
                setOptionDisplayed(<ReservationHistory reservations={reservations}/>);
                break;
            case 5:
                setOptionDisplayed(<PaymentHistory reservations={reservations}/>);
                break;
            default:
                setOptionDisplayed(<></>);
        }
    }

    useEffect(() => {
        console.log("optionSelected = " + optionSelected);
        displayPerfilOptions();
        console.log(optionDisplayed)
    }, [optionSelected]);

    useEffect(() => {
        localStorage.setItem("user", JSON.stringify(user));
    }, [user]);


    if(user !== null)
        return (
            <div className='front_page'>
                <Navbar setParkTypeFilter={setParkTypeFilter} setFilter={null}/>
                <div className="front_page_content">
                    <div className="perfil_main">
                        <div className='perfil_block_image'>
                            <img className={'perfil_image'} src={'images/perfil_black.png'} alt={""} />
                        </div>
                        <div className="perfil_info">
                            <div className="perfil_block_info">
                                <h2>Informação Básica</h2>
                                <b className='field_name'>Nome:</b>
                                <label className='field_content'>{user['nome']}</label>
                                <b className='field_name'>Email:</b>
                                <label className='field_content'>{user['email']}</label>
                                <b className='field_name'>Numero de Telefone:</b>
                                <label className='field_content'>{user['nr_telemovel']}</label>
                            </div>
                        </div>
                        <div className={optionSelected===-1? "disabled_selected":"perfil_options perfil_options_open"}>
                            <div className={'perfil_options_box perfil_options_box_open' + (optionSelected===1? ' perfil_selected' : '')}  onClick={() => setOptionSelected(1)}>
                                <div className='perfil_options_image perfil_open'>
                                    <ImageBlock imageLink={'images/icon_1.png'} image_icon={true} />
                                </div>
                                <div className='perfil_options_text'>
                                    <b className='perfil_options_text_title perfil_options_text_title_open'>Editar Perfil</b>
                                </div>
                            </div>
                            <div className={'perfil_options_box perfil_options_box_open' + (optionSelected===2? ' perfil_selected' : '')}  onClick={() => setOptionSelected(2)}>
                                <div className='perfil_options_image perfil_open'>
                                    <ImageBlock imageLink={'images/icon_2.png'} image_icon={true} />
                                </div>
                                <div className='perfil_options_text'>
                                    <b className='perfil_options_text_title perfil_options_text_title_open'>Segurança</b>
                                </div>
                            </div>
                            <div className={'perfil_options_box perfil_options_box_open' + (optionSelected===3? ' perfil_selected' : '')}  onClick={() => setOptionSelected(3)}>
                                <div className='perfil_options_image perfil_open'>
                                    <ImageBlock imageLink={'images/icon_6.png'} image_icon={true} />
                                </div>
                                <div className='perfil_options_text'>
                                    <b className='perfil_options_text_title perfil_options_text_title_open'>Minhas Reservas</b>
                                </div>
                            </div>
                            <div className={'perfil_options_box perfil_options_box_open' + (optionSelected===4? ' perfil_selected' : '')}  onClick={() => setOptionSelected(4)}>
                                <div className='perfil_options_image perfil_open'>
                                    <ImageBlock imageLink={'images/icon_3.png'} image_icon={true} />
                                </div>
                                <div className='perfil_options_text'>
                                    <b className='perfil_options_text_title perfil_options_text_title_open'>Historico de Reservas</b>
                                </div>
                            </div>
                            <div className={'perfil_options_box perfil_options_box_open' + (optionSelected===5? ' perfil_selected' : '')}  onClick={() => setOptionSelected(5)}>
                                <div className='perfil_options_image perfil_open'>
                                    <ImageBlock imageLink={'images/icon_6.png'} image_icon={true} />
                                </div>
                                <div className='perfil_options_text'>
                                    <b className='perfil_options_text_title perfil_options_text_title_open'>Historico de Pagamentos</b>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className={optionSelected===-1? "perfil_options":"disabled_selected"}>
                        <div className='perfil_options_box' onClick={() => setOptionSelected(1)}>
                            <div className={'perfil_options_image'}>
                                <ImageBlock imageLink={'images/icon_1.png'} image_icon={true} />
                            </div>
                            <div className='perfil_options_text'>
                                <b className='perfil_options_text_title'>Editar Perfil</b>
                                <label className={'perfil_options_text_info'}>Atualize a sua informação e saiba como esta é utilizada.</label>
                            </div>
                        </div>
                        <div className='perfil_options_box' onClick={() => setOptionSelected(2)}>
                            <div className={'perfil_options_image'}>
                                <ImageBlock imageLink={'images/icon_2.png'} image_icon={true} />
                            </div>
                            <div className='perfil_options_text'>
                                <b className='perfil_options_text_title'>Segurança</b>
                                <label className={'perfil_options_text_info'}>Ajuste as definições de segurança e configure a autenticação de dois fatores.</label>
                            </div>
                        </div>
                        <div className='perfil_options_box' onClick={() => setOptionSelected(3)}>
                            <div className={'perfil_options_image'}>
                                <ImageBlock imageLink={'images/icon_6.png'} image_icon={true} />
                            </div>
                            <div className='perfil_options_text'>
                                <b className='perfil_options_text_title'>Minhas Reservas</b>
                                <label className={'perfil_options_text_info'}>Ver as minhas reservas agendadas.</label>
                            </div>
                        </div>
                        <div className='perfil_options_box' onClick={() => setOptionSelected(4)}>
                            <div className={'perfil_options_image'}>
                                <ImageBlock imageLink={'images/icon_3.png'} image_icon={true} />
                            </div>
                            <div className='perfil_options_text'>
                                <b className='perfil_options_text_title'>Historico de Reservas</b>
                                <label className={'perfil_options_text_info'}>Consulte a informação relativa ao seu histórico de reservas.</label>
                            </div>
                        </div>
                        <div className='perfil_options_box' onClick={() => setOptionSelected(5)}>
                            <div className={'perfil_options_image'}>
                                <ImageBlock imageLink={'images/icon_7.png'} image_icon={true} />
                            </div>
                            <div className='perfil_options_text'>
                                <b className='perfil_options_text_title'>Historico de Pagamentos</b>
                                <label className={'perfil_options_text_info'}>Consulte a informação relativa a métodos de pagamento de forma e facilite e segura as reservas.</label>
                            </div>
                        </div>
                    </div>
                    {optionDisplayed}
                </div>
            </div>
        );
    else return <></>
}

export default Perfil;