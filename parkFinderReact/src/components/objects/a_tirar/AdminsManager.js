import '../condutor/Parks.css'
import '../../interactive_items/select.css'
import ManagerCompressedAdminInfo from './ManagerCompressedAdminInfo';
import { useState } from 'react';
import { Button } from '../../interactive_items/Button';

function AdminsManager({
    admins,
    filter,
    setFilter,
    setState,
}) {
    const [popUp, setPopUp] = useState(false);
    
    return (
 
            <div className='parks_content_display'>
                <div className='parks_info_display'>
                    <Button className='default' link={'/manager/create_admin'}>Criar</Button>
                    {admins.map(admin => 
                        <ManagerCompressedAdminInfo key={admin['id']} admin={admin}/>
                    )}
                    <div className='pageNumb'>
                        <button className='page_button'> {'<<'} </button>
                        <button className='page_button'> 1 </button>
                        <button className='page_button'> 2 </button>
                        <button className='page_button'> 3  </button>
                        <button className='page_button'> {'>>'} </button>
                    </div>
                </div>
            </div>
    );
}

export default AdminsManager;