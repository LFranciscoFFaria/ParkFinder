import { Button } from "../interactive_items/Button";
import "./InstantPark.css";
import "../pages/condutor/Details.css";
import PopUp from "../interactive_items/PopUp";
import { useEffect, useState } from "react";
import { Html5QrcodeScanner } from "html5-qrcode";


function separateString(string) {

    let lines = string.split("\n");
    return(
        <table>
              <tr>
                <th><b>Matrícula</b></th>
                <th></th>
            </tr>
            {lines.map((line, index) => (
            <tr>
                <td >{line}</td> 
                <td><Button className="default">Remover</Button></td> 
            </tr>
            ))}
        </table>
    )
}

function InstantPark({
    parque
}) {

    
    const [popUp, setPopUp] = useState(false);
    const [entry, setEntry] = useState(false);

    
    var scanner = null;
    useEffect (() => {
        if(popUp){
            scanner = new Html5QrcodeScanner("reader", {
                fps: 10,
                qrbox: 250
            });

            function onScanSuccess(decodedText, decodedResult) {
                console.log(`Scan result: ${decodedText}`, decodedResult);
                scanner.clear();
                setPopUp(false);
            }

            scanner.render(onScanSuccess);
        }
    }, [popUp]);

    function refreshPage() {
        window.location.reload();
    }

    return (
        <>
            <h1>Entradas e Saidas</h1>
            <div className="details_pages_display instant_park_display">

                <Button buttonStyle="contrast navbar_staff_login_button flex_button" onClick={() => {setPopUp(true); setEntry(true)}}>Registar Entrada</Button>
                <Button buttonStyle="contrast navbar_staff_login_button flex_button" onClick={() => {setPopUp(true); setEntry(false)}}>Registar Saida</Button>
                
                {popUp?
                    <PopUp text={"Leitor QR Code"} closePopUp={refreshPage} element={
                        <>
                            <div id="reader" className="scanner_box"></div>
                        </>
                    }/>
                    :
                    null
                }
            </div>
        </>
    );
};


export default InstantPark;