import React, { useState, useEffect } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/pages/Login.js';
import Register from './components/pages/Register.js';
import Perfil from './components/pages/Perfil';
import Details from './components/pages/Details';
import Parks from './components/pages/Parks';
import Admin from './components/pages/Admin';
import EditParkAdmin from './components/pages/EditParkAdmin';
import Manager from './components/pages/Manager';
import EditParkManager from './components/pages/EditParkManager';
import CreateAdmin from './components/pages/CreateAdmin';
import EditAdmin from './components/pages/EditAdmin';
import Programmer from './components/pages/Programmer';
import CreatePark from './components/pages/CreatePark';
import EditParkProgrammer from './components/pages/EditParkProgrammer';
import CreateManager from './components/pages/CreateManager';
import EditManager from './components/pages/EditManager';

const parques = [
    {
        'id': 0,
        'nome': "PARQUE VISCONDE DO RAIO",
        'morada': "rua dos reis",
        'distancia': "(797 m)",
        'lugares_vagos': 45,
        'lugares_totais': 96,
        'link_imagem': "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        'custo': 1.30,
        'hora_init':"8:00",
        'hora_end':"19:00",
        'descricao': "Public covered parking\n7 min. walk from the heart of the city\nAccessible from Monday to Friday from 8:00 am to 8:00 pm and Saturdays from 10:00 am to 8:00 pm.",
    },
    {
        'id': 1,
        'nome': "B&B BRAGA LAMAÇÃES",
        'morada': "rua dos reis",
        'distancia': "(2.7 km)",
        'lugares_vagos': 22,
        'lugares_totais': 51,
        'link_imagem': "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        'custo': 7.00,
        'hora_init':"8:00",
        'hora_end':"19:00",
        'descricao': "Covered Hotel Parking\n10 min. from University of Minho\ntaxi service Accessible 24/7",
    },
    {
        'id': 2,
        'nome': "BRAGA PARQUE",
        'morada': "rua dos reis",
        'distancia': "(1.1km)",
        'lugares_vagos': 186,
        'lugares_totais': 268,
        'link_imagem': "https://assets.onepark.fr/media/W1siZiIsIjIwMTkvMDYvMTcvMTEvMTIvMjAvMjBhOGIxYTgtYjAyMS00NDIzLThmZWItYjQ3MWU1YTRlOGFiL3JhaW8uanBnIl0sWyJwIiwidGh1bWIiLCI3MzZ4NDE0XHUwMDNlIl0sWyJwIiwiYWRkX3doaXRlX2NhbnZhcyJdXQ/Estacionamento%20Público%20PARQUE%20VISCONDE%20DO%20RAIO%20%28Coberto%29?sha=5b791144f5d2971c",
        'custo': 2.15,
        'hora_init':"8:00",
        'hora_end':"19:00",
        'descricao': "Public covered Parking\nUnder the citizen's house from Braga\nAccessible 24/7",
    },
]

function App() {
    /*Possible userStates:
        loggedOff: no current logged user
        gambler: gambler logged in
        expert: expert logged in
        admin: admin logged in
    */

    const [state, setState] = useState('');
    const [filter, setFilter] = useState(false);
    const [idParque, setIdParque] = useState(-1);

    useEffect(() => {
        console.log("state = " + state);
        console.log("filter = " + filter);
        console.log("idParque = " + idParque);
    }, [state,filter]);

    return (
        <Router>
            <Routes>
                {/*Common user*/}
                <Route path='/login' element={<Login/>} />
                <Route path='/register' element={<Register/>} />
                <Route path='/' element={<Parks parques={parques} filter={filter} setFilter={setFilter} setState={setState} state={state}/>} />
                <Route path='/details' element={<Details filter={filter} setState={setState}/>} />
                <Route path='/perfil' element={<Perfil setState={setState}/>} />

                {/*Admin*/}
                <Route path='/admin' element={<Admin setIdParque={setIdParque}/>}/>
                <Route path='/admin/edit_park' element={<EditParkAdmin/>}/>

                {/*Manager*/}
                <Route path='/manager' element={<Manager/>}/>
                <Route path='/manager/edit_park' element={<EditParkManager/>}/>
                <Route path='/manager/create_admin' element={<CreateAdmin/>}/>
                <Route path='/manager/edit_admin' element={<EditAdmin/>}/>

                {/*Programmer*/}
                <Route path='/programmer' element={<Programmer/>}/>
                <Route path='/programmer/create_park' element={<CreatePark/>}/>
                <Route path='/programmer/edit_park' element={<EditParkProgrammer/>}/>
                <Route path='/programmer/create_manager' element={<CreateManager/>}/>
                <Route path='/programmer/edit_manager' element={<EditManager/>}/>
            </Routes>
        </Router>
    );
}

export default App