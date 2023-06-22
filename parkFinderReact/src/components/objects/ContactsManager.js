import { useState } from 'react';
import { Button } from '../interactive_items/Button';
import './Contacts.css';
import PopUp from '../interactive_items/PopUp';


const parques = [
    { id: 1,  park: "PARQUE DA PONTE"},
    { id: 2,  park: "PARQUE AVENIDA CENTRAL"},
    { id: 3,  park: "CENTRAL DE CAMIONAGEM"},
    { id: 4,  park: "ESTAÇÃO"},
    { id: 5,  park: "SANTISSIMA TRINDADE"},
    { id: 6,  park: "PARQUE PORTAS"},
    { id: 7,  park: "ESTACIONAMENTOS CAMPO DA VINHA"},
    { id: 8,  park: "SRA TAIMANA"},
    { id: 9,  park: "TAIMANA PEQUENA"},
]



function ContactsManager({
    administradores
}) {

    const [popUp, setPopUp] = useState(false);
    const [userId, setUserId] = useState(-1);
    const [op, setOp] = useState("");
    const [listParks, setListParks] = useState([]);

    const handleChange = (e) => {
        const { value, checked } = e.target;

        if (checked) {
            setListParks([...listParks, parseInt(value)]);
        } else {
            setListParks(listParks.filter((e) => e !== parseInt(value)));
        }
    };


    function makelist (parques) {
        return(
            parques.length > 0?
                <div className='contact_popup_list'>
                    {parques.map((parque,index)=>
                        <label key={index} className="checkbox_label">
                            <input type="checkbox" className="checkbox" value={parque.id} onChange={handleChange}/>
                            {parque.park}
                        </label>
                    )}
                    <div className='contact_popup_list_button'>
                        <Button type={'submit'} buttonStyle={"default compressed_park_staff_filter_button flex_button"} onClick={updateManagers}> Gravar Alterações</Button>
                    </div>
                </div>
                :
                <h1>Não existem parques para apresentar</h1>
        )
    };



    function updateManagers(){
        console.log(listParks);
        console.log(userId);
        console.log(op);
    }





    return (
        <div className="contact_display">
            <div className="contact_header">
                <h1>Administradores</h1>
                <Button buttonStyle={"default compressed_park_staff_filter_button"} onClick={() => console.log("createButton")} link={'/manager/admin/create'}>Criar Administrador</Button>
            </div>
            {administradores.map((user) =>
                <div key={user['id']} className="contact_box">
                    <b className='name'>{"Rui Antunes Gil Gomes de Sá Ribeiro Martins"}</b>
                    <label className='email'>Email:</label>
                    <label className='tel'>Nº Telemovel:</label>
                    <b className='c1'>{user["email"]}</b>
                    <b className='c2'>{user["telemovel"]}</b>
                    <div className='c4'>
                        <div className='contact_buttons'>
                            <Button buttonStyle={"default flex_button"} onClick={() => {setPopUp(true); setUserId(user['id']); setOp('R')}}>Remove Parque</Button>
                        </div>
                        <div className='contact_buttons'>
                            <Button buttonStyle={"default flex_button"} onClick={() => {setPopUp(true); setUserId(user['id']); setOp('A')}}>Adicionar Parque</Button>
                        </div>
                        <div className='contact_buttons'>
                            <Button buttonStyle={"default flex_button"} onClick={() => console.log("removeButton")}>Remover</Button>
                        </div>
                    </div>

                    {user["parques"]?
                        <>
                            <label className='park'>Parques:</label>
                            <div className='c3'>{user["parques"].map((parque) => <li key={parque}> {parque} </li>)}</div>
                        </>
                        :
                        null
                    }

                </div>
            )}
            {popUp && op === "R"?
                <PopUp text={'Remover Parques'} closePopUp={() => setPopUp(false)} element={makelist(parques)}/>
                :
                null
            }
            {popUp && op === "A"?
                <PopUp text={'Adicionar Parques'} closePopUp={() => setPopUp(false)} element={makelist(parques)}/>
                :
                null
            }
        </div>
    );
}

export default ContactsManager;