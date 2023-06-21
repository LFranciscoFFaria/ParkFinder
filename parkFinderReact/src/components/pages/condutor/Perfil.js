import { ImageBlock } from '../../interactive_items/ImageBlock';
import { Navbar } from '../../objects/Navbar';
import { EditPerfil } from '../../objects/EditPerfil.js';
import Security from '../../objects/Security.js';
import PaymentHistory from '../../objects/PaymentHistory.js';
import ReservationHistory from '../../objects/ReservationHistory.js';
import '../../interactive_items/select.css'
import './Perfil.css'
import { useEffect, useState } from 'react';


function Perfil({
    setState,
    reservations,
}) {
    const [optionSelected, setOptionSelected] = useState(-1);
    const [optionDisplayed, setOptionDisplayed] = useState(null);

    function displayPerfilOptions() {
        switch (optionSelected) {
            case 1:
                setOptionDisplayed(<EditPerfil/>);
                break;
            case 2:
                setOptionDisplayed(<Security/>);
                break;
            case 3:
                setOptionDisplayed(<PaymentHistory reservations={reservations}/>);
                break;
            case 4:
                setOptionDisplayed(<ReservationHistory reservations={reservations}/>);
                break;
            default:
                setOptionDisplayed(<></>);
        }
    }

    useEffect(() => {
        console.log("optionSelected = " + optionSelected);
        displayPerfilOptions();
    }, [optionSelected]);
    
    return (
        <div className='front_page'>
            <Navbar setState={setState} setFilter={null}/>
            <div className="front_page_content">
                <div className="perfil_main">
                    <div className='perfil_block_image'>
                        <img className={'perfil_image'} src={'images/perfil_black.png'} alt={""} />
                    </div>
                    <div className="perfil_info">
                        <div className="perfil_block_info">
                            <h2>Informação Básica</h2>
                            <b className='field_name'>Nome:</b>
                            <label className='field_content'>Alexandre Silva Martins</label>
                            <b className='field_name'>Email:</b>
                            <label className='field_content'>se_o_alex_vir_isto_e_gay@gmail.com</label>
                            <b className='field_name'>Numero de Telefone:</b>
                            <label className='field_content'>987654321</label>
                        </div>
                    </div>
                    <div className={optionSelected===-1? "disabled_selected":"perfil_options perfil_options_open"}>
                        <div className={'perfil_options_box perfil_options_box_open' + (optionSelected===1? ' perfil_selected' : '')}  onClick={() => setOptionSelected(1)}>
                            <div className='perfil_options_image perfil_open'>
                                <ImageBlock imageLink={'images/icon_1.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                            </div>
                            <div className='perfil_options_text'>
                                <b className='perfil_options_text_title perfil_options_text_title_open'>Editar Perfil</b>
                            </div>
                        </div>
                        <div className={'perfil_options_box perfil_options_box_open' + (optionSelected===2? ' perfil_selected' : '')}  onClick={() => setOptionSelected(2)}>
                            <div className='perfil_options_image perfil_open'>
                                <ImageBlock imageLink={'images/icon_2.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                            </div>
                            <div className='perfil_options_text'>
                                <b className='perfil_options_text_title perfil_options_text_title_open'>Segurança</b>
                            </div>
                        </div>
                        <div className={'perfil_options_box perfil_options_box_open' + (optionSelected===3? ' perfil_selected' : '')}  onClick={() => setOptionSelected(3)}>
                            <div className='perfil_options_image perfil_open'>
                                <ImageBlock imageLink={'images/icon_6.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                            </div>
                            <div className='perfil_options_text'>
                                <b className='perfil_options_text_title perfil_options_text_title_open'>Historico de Pagamentos</b>
                            </div>
                        </div> 
                        <div className={'perfil_options_box perfil_options_box_open' + (optionSelected===4? ' perfil_selected' : '')}  onClick={() => setOptionSelected(4)}>
                            <div className='perfil_options_image perfil_open'>
                                <ImageBlock imageLink={'images/icon_3.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                            </div>
                            <div className='perfil_options_text'>
                                <b className='perfil_options_text_title perfil_options_text_title_open'>Historico de Reservas</b>
                            </div>
                        </div> 
                    </div>
                </div>
                <div className={optionSelected===-1? "perfil_options":"disabled_selected"}>
                    <div className='perfil_options_box' onClick={() => setOptionSelected(1)}>
                        <div className={'perfil_options_image'}>
                            <ImageBlock imageLink={'images/icon_1.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                        </div>
                        <div className='perfil_options_text'>
                            <b className='perfil_options_text_title'>Editar Perfil</b>
                            <label className={'perfil_options_text_info'}>Atualize a sua informação e saiba como esta é utilizada.</label>
                        </div>
                    </div>
                    <div className='perfil_options_box' onClick={() => setOptionSelected(2)}>
                        <div className={'perfil_options_image'}>
                            <ImageBlock imageLink={'images/icon_2.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                        </div>
                        <div className='perfil_options_text'>
                            <b className='perfil_options_text_title'>Segurança</b>
                            <label className={'perfil_options_text_info'}>Ajuste as definições de segurança e configure a autenticação de dois fatores.</label>
                        </div>
                    </div>
                    <div className='perfil_options_box' onClick={() => setOptionSelected(3)}>
                        <div className={'perfil_options_image'}>
                            <ImageBlock imageLink={'images/icon_6.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                        </div>
                        <div className='perfil_options_text'>
                            <b className='perfil_options_text_title'>Historico de Pagamentos</b>
                            <label className={'perfil_options_text_info'}>Consulte a informação relativa a métodos de pagamento de forma e facilite e segura as reservas.</label>
                        </div>
                    </div> 
                    <div className='perfil_options_box' onClick={() => setOptionSelected(4)}>
                        <div className={'perfil_options_image'}>
                            <ImageBlock imageLink={'images/icon_3.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                        </div>
                        <div className='perfil_options_text'>
                            <b className='perfil_options_text_title'>Historico de Reservas</b>
                            <label className={'perfil_options_text_info'}>Consulte a informação relativa ao seu histórico de reservas.</label>
                        </div>
                    </div> 
                </div>
                {optionDisplayed}
            </div>
        </div>
    );
}

export default Perfil;