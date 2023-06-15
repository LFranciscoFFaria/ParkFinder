import React, { useState, useEffect } from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/pages/Login.js';
import Register from './components/pages/Register.js';
import FrontPage from './components/pages/FrontPage.js';




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
                <Route path='/login' element={<Login/>} />
                <Route path='/register' element={<Register/>} />
                <Route path='/' element={<FrontPage/>} />
            </Routes>
        </Router>
    );
}

export default App
