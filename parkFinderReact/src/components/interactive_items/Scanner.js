import "../objects/InstantPark.css";
import { Html5QrcodeScanner } from "html5-qrcode";
import { Button } from "./Button";



function Scanner() {

    function renderReader(){
        var html5QrcodeScanner = new Html5QrcodeScanner("reader", {
            fps: 10,
            qrbox: 250
        });

        function onScanSuccess(decodedText, decodedResult) {
            console.log(`Scan result: ${decodedText}`, decodedResult);
            html5QrcodeScanner.clear();
        }

        html5QrcodeScanner.render(onScanSuccess);
    }
    
    return(
        <>
            <div id="reader" className="scanner_box"></div>
            <div id="result"></div>
            <Button onClick={renderReader}> Usar Camara </Button>
        </>
    )
}

export default Scanner;