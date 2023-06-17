import { ImageBlock } from '../interactive_items/ImageBlock';
import './Perfil.css'


function Perfil({
    
}) {
    
    return (
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
            <div className="perfil_main">
                <div className='perfil_options'>
                    <div className='perfil_options_image'>
                        <ImageBlock imageLink={'images/icon_1.png'}/>
                    </div>
                    <div className='perfil_options_text'>

                    </div>
                    <div className=''></div>
                </div>
            </div>
        </div>
    );
}

export default Perfil;