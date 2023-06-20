import NavbarStaff from './NavbarStaff';
import {ImageBlock} from '../interactive_items/ImageBlock';
import { Button } from '../interactive_items/Button';
import '../interactive_items/select.css'
import { useEffect, useState } from 'react';

const parques = [
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

function parkTable(parques, setFunc,saveProfile) {

    let parks = [];
    return(
        <table>
              <tr>
                <th><b>Parque</b></th>
                <th>Ação</th>
            </tr>
            <tr>
                <td>
                    <select className='select' name='Criterion' id='criterion' defaultValue={"default"}>
                        <option className='disabled_selected' value="default" disabled>Park</option>
                        {parques.map((parque, index) => (
                            <option value={parque['id']}>{parque['nome']}</option>
                         ))}
                    </select>
                </td>
                <td><Button className="submit">Adicionar</Button></td> 
            </tr>
                        
            {parks.map((id) => (
            <tr>
                <td >{parques[id]['nome']}</td> 
                <td><Button className="default">Remover</Button></td> 
            </tr>
            ))}
        </table>
    )
}



function CreateAdmin({
}) {
    const [email, setAdminEmail] = useState('');
    const [name, setAdminName] = useState('');
    const [contact, setAdminContact] = useState('');
    const [parks, setAdminParks] = useState('');
    const [password, setAdminPassword] = useState('');

    const [parques,setParques] = useState(parques);

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
                <div className='edit_perfil_form_content'>
                    {editPerfilField('Nome',null,'Nome',setAdminName,saveAdmin)}
                    {editPerfilField('Email','email','email',setAdminEmail,saveAdmin)}
                    {editPerfilField('Contact','contact','contact',setAdminContact,saveAdmin)}
                    {editPerfilField('Password','password','password',setAdminPassword,saveAdmin)}
                    {parkTable(parques, setAdminParks,saveAdmin)}
                </div>
            </div>
        </div>
    );
}

export default CreateAdmin;