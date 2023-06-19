import './Manager.css'
import Filter from '../objects/Filter';
import Navbar from '../objects/Navbar';
import {ImageBlock} from '../interactive_items/ImageBlock';
import { Button } from '../interactive_items/Button';
import '../interactive_items/select.css'

function editPerfilField (
    name,
    type,
    placeholder,
    setFunc,
    saveProfile,
    ) {
    return(
        <form onSubmit={saveProfile}>
            <div className='edit_perfil_field'>
                <b> {name} </b>
                <div className='edit_perfil_input_button'>
                    <input
                        className='edit_perfil_input'
                        placeholder={placeholder}
                        type={type}
                        onChange={(e) => setFunc(e.target.value)}
                        required/>
                    <Button type='submit' buttonStyle='contrast'>Gravar</Button>
                </div>
            </div>
        </form>
    )
}

function EditParkManager({
    parque,
    useState,
    setState,
    setId
}) {
    const [description, setDescription] = useState('');
    const [name, setParkName] = useState('');


    const savePark = (event) => {
        event.preventDefault()
        console.log("Save Park");
        console.log("description = " + description);
        console.log("name = " + name);
    };

    return (
        <div className='edit_perfil_form_content'>
            {editPerfilField('Nome',null,'Nome',setParkName,(savePark))}
            {editPerfilField('Descrição','descricao','descricao',setDescription,savePark)}
        </div>
    );
}

export default EditParkManager;