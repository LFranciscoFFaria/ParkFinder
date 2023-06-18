import { ImageBlock } from '../interactive_items/ImageBlock';
import Navbar from '../objects/Navbar';
import './Perfil.css'


function Perfil({
    setState
}) {
    
    return (
        <div className='front_page'>
            <Navbar setState={setState} setFilter={null}/>
            <div className="content_front_page">
                <div className="perfil_main">
                    <div className='perfil_block_image'>
                        <img className={'perfil_image'} src={'images/perfil.png'} alt={""} />
                    </div>
                    <div className="perfil_info">
                        <div className="perfil_block_info">
                            <h2>Basic Information</h2>
                            <b className='field_name'>Name:</b>
                            <label className='field_content'>Alexandre Silva Martins</label>
                            <b className='field_name'>Email:</b>
                            <label className='field_content'>se_o_alex_vir_isto_e_gay@gmail.com</label>
                            <b className='field_name'>Phone Number:</b>
                            <label className='field_content'>987654321</label>
                        </div>
                    </div>
                </div>
                <div className="perfil_options">
                    <div className='perfil_options_box'>
                        <div className='perfil_options_image'>
                            <ImageBlock imageLink={'images/icon_1.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                        </div>
                        <div className='perfil_options_text'>
                            <b className='perfil_options_text_title'>Editar Perfil</b>
                            <label>Atualize a sua informação e saiba como esta é utilizada.</label>
                        </div>
                    </div>
                    <div className='perfil_options_box'>
                        <div className='perfil_options_image'>
                            <ImageBlock imageLink={'images/icon_2.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                        </div>
                        <div className='perfil_options_text'>
                            <b className='perfil_options_text_title'>Segurança</b>
                            <label>Ajuste as definições de segurança e configure a autenticação de dois fatores.</label>
                        </div>
                    </div>
                    <div className='perfil_options_box'>
                        <div className='perfil_options_image'>
                            <ImageBlock imageLink={'images/icon_6.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                        </div>
                        <div className='perfil_options_text'>
                            <b className='perfil_options_text_title'>Consultar historico de pagamentos</b>
                            <label>Consulte a informação relativa a métodos de pagamento de forma e facilite e segura as reservas.</label>
                        </div>
                    </div> 
                    <div className='perfil_options_box'>
                        <div className='perfil_options_image'>
                            <ImageBlock imageLink={'images/icon_3.png'} no_shadow={true} no_scale={true} no_border_radius={true}/>
                        </div>
                        <div className='perfil_options_text'>
                            <b className='perfil_options_text_title'>Consultar historico de Reservas</b>
                            <label>Consulte a informação relativa ao seu histórico de reservas.</label>
                        </div>
                    </div> 
                </div>
            </div>
        </div>
    );
}

export default Perfil;