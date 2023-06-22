import { useEffect, useState } from 'react';
import { Button } from '../interactive_items/Button';
import './EditPerfil.css';
import './EditPark.css';
import '../pages/condutor/Details.css';
import '../interactive_items/select.css';
import { ImageBlock } from '../interactive_items/ImageBlock';


function EditParkAdmin({
    parque,
    setParque
}) {
    const [instTotais, setInstTotais] = useState(parque['instantaneos_total']);
    const [resrTotais, setResrTotais] = useState(parque['lugares_totais']);


    const savePark = (event) => {
        event.preventDefault();
        console.log("Save Park");
        console.log("instTotais = " + instTotais);
        console.log("resrTotais = " + resrTotais);
    };

    useEffect(() => {
        setInstTotais(parque['instantaneos_total']);
        setResrTotais(parque['lugares_totais']);
    },[parque])

    return (
        <>
            <div className="contact_header">
                <h1>Editar Informação do Parque</h1>
            </div>
            <div className='edit_park_container'>
                <form onSubmit={savePark} className='edit_park_fields_container'>
                    <div className='security_field'>
                        <b className='edit_park_title'> {'Nr lugares Instantaneos: '} </b>
                        <div className='edit_park_schedule_container'>
                            <div className='edit_park_parking_spaces'>
                                <label> {'Valor atual: '} </label>
                                <b> {parque['instantaneos_total']} </b>
                            </div>
                            <div className='edit_park_parking_spaces'>
                                <label> {'Atualizar:'} </label>
                                <input className='edit_park_input' type='number' placeholder={'Instantaneos'} value={instTotais} onChange={(e) => setInstTotais(e.target.value)} required/>
                            </div>
                        </div>
                    </div>
                    <div className='security_field'>
                        <b className='edit_park_title'> {'Nr lugares Reservaveis:'} </b>
                        <div className='edit_park_schedule_container'>
                            <div className='edit_park_parking_spaces'>
                                <label> {'Valor atual:'} </label>
                                <b> {parque['lugares_totais']} </b>
                            </div>
                            <div className='edit_park_parking_spaces'>
                                <label> {'Atualizar:'} </label>
                                <input className='edit_park_input' type='number' placeholder={'Reservaveis'} value={resrTotais} onChange={(e) => setResrTotais(e.target.value)} required/>
                            </div>
                        </div>
                    </div>
                    <div className='security_input_button'>
                        <br/>
                        <Button type='submit' buttonStyle='default'>Gravar Alterações</Button>
                    </div>
                </form>
            </div>
        </>
    );
};


export default EditParkAdmin;