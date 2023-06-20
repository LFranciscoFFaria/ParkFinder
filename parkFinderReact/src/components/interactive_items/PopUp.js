import { Button } from "./Button";
import './PopUp.css'





function PopUp({
    closePopUp,
    text,
    element
}) {

    return (
        <div className='popup_container'>
            <button className='popup_closePopUp_button popup_closePopUp_Y' onClick={closePopUp}>.</button>
            <div className='popup_content_section'>
                <button className='popup_closePopUp_button' onClick={closePopUp}>.</button>
                <div className="popup_body">
                    <div className="popup_close_button">
                        <br></br>
                        <h1>{text}</h1>
                        <Button buttonStyle={'default close_button'} onClick={() => closePopUp()}> X </Button >
                    </div>
                    {element}
                </div>
                <button className='popup_closePopUp_button' onClick={closePopUp}>.</button>
            </div>
            <button className='popup_closePopUp_button popup_closePopUp_Y' onClick={closePopUp}>.</button>
        </div>
    );
}

export default PopUp;