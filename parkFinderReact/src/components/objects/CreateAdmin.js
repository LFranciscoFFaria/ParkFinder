import { NavbarStaff } from './Navbar';
import {ImageBlock} from '../interactive_items/ImageBlock';
import { Button } from '../interactive_items/Button';
import '../interactive_items/select.css'
import { useEffect, useState } from 'react';
import './CreateAdmin.css';
import Checkbox from '../interactive_items/Checkbox';




const pk = [
    {
        "id" : 0,
        "nome" : "PARQUE VISCONDE DO RAIO",
        "morada" : "rua dos reis",
        "distancia" : "(797 m)",
        "lugares_vagos" : 45,
        "lugares_totais" : 96,
        "link_imagem" : "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        "custo" : 1.30,
        "hora_init" :"8:00",
        "hora_end" :"19:00",
        "descricao" : "Public covered parking\n7 min. walk from the heart of the city\nAccessible from Monday to Friday from 8:00 am to 8:00 pm and Saturdays from 10:00 am to 8:00 pm.",
    },
    {
        "id" : 1,
        "nome" : "B&B BRAGA LAMAÇÃES",
        "morada" : "rua dos reis",
        "distancia" : "(2.7 km)",
        "lugares_vagos" : 22,
        "lugares_totais" : 51,
        "link_imagem" : "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        "custo" : 7.00,
        "hora_init" :"8:00",
        "hora_end" :"19:00",
        "descricao" : "Covered Hotel Parking\n10 min. from University of Minho\ntaxi service Accessible 24/7",
    },
    {
        "id" : 2,
        "nome" : "BRAGA PARQUE",
        "morada" : "rua dos reis",
        "distancia" : "(1.1km)",
        "lugares_vagos" : 186,
        "lugares_totais" : 268,
        "link_imagem" : "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        "custo" : 2.15,
        "hora_init" :"8:00",
        "hora_end" :"19:00",
        "descricao" : "Public covered Parking\nUnder the citizen's house from Braga\nAccessible 24/7",
    },
    {
        "id" : 3,
        "nome" : "BRAGA PARQUE",
        "morada" : "rua dos reis",
        "distancia" : "(1.1km)",
        "lugares_vagos" : 186,
        "lugares_totais" : 268,
        "link_imagem" : "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        "custo" : 2.15,
        "hora_init" :"8:00",
        "hora_end" :"19:00",
        "descricao" : "Public covered Parking\nUnder the citizen's house from Braga\nAccessible 24/7",
    },
]

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


function CreateAdmin({
}) {
    const [email, setAdminEmail] = useState('');
    const [name, setAdminName] = useState('');
    const [contact, setAdminContact] = useState('');
    const [parks, setAdminParks] = useState('');
    const [password, setAdminPassword] = useState('');

    const [parques,setParques] = useState(pk);

    const [parkbuff, setAdminParkbuff] = useState('');

    let parklist = [];

    const change_on_list = (event) => {
        let index = parklist.indexOf(parkbuff);
        if(parklist.includes(parkbuff)==true){
            parklist = parklist.slice(0,index).concat(parklist.slice(index+1));
        }
        else{
            parklist.concat([parkbuff]);
        }
        setAdminParks(parklist);
        console.log(parkbuff);
    };

    const saveAdmin = (event) => {
        event.preventDefault()
        console.log("Save Admin");
        console.log("name = " + name);
        console.log("email = " + email);
        console.log("contact = " + contact);
        console.log("parks = " + parks);
        console.log("password = " + password);
    };
    
    return(
        <div className='staff_bg'>
            <div className='staff_whitebox'>
                <NavbarStaff link_logo={'/manager'}/>

                {editPerfilField('Nome',null,'Nome',setAdminName,saveAdmin)}
                {editPerfilField('Email','email','email',setAdminEmail,saveAdmin)}
                {editPerfilField('Contact','contact','contact',setAdminContact,saveAdmin)}
                {editPerfilField('Password','password','password',setAdminPassword,saveAdmin)}

                <form onSubmit={saveAdmin}>
                    <div className='edit_perfil_field'>
                        <b> {name} </b>
                        <div className='edit_perfil_input_button'>
                            <div className='admin_grid_container' name='Criterion' id='criterion' defaultValue={"default"}>
                                {parques.map((parque, index) => (
                                    <label className="checkbox_label" key={index}>
                                        <input type="checkbox" className="checkbox"  value={parque['id']} 
                                            onChange={(e) => {setAdminParkbuff(e.target.value);change_on_list(e.target.value)}}/>
                                        {parque['nome']}
                                    </label>
                                ))}
                            </div>
                        </div>
                            <Button type='submit' buttonStyle='contrast'>Gravar</Button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default CreateAdmin;