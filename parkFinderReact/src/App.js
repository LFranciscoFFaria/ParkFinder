import React, { useState, useEffect } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/pages/Login.js';
import Register from './components/pages/Register.js';




function App() {
    /*Possible userStates:
        loggedOff: no current logged user
        gambler: gambler logged in
        expert: expert logged in
        admin: admin logged in
    */



    return (
        <Router>
            <Routes>
                {/*Mutual pages*/}
                <Route path='/' element={<Login/>} />
                <Route path='/register' element={<Register/>} />
            </Routes>
        </Router>
    );
}

export default App
