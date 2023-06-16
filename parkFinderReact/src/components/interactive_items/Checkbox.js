import React from 'react';
import './Checkbox.css';



function Checkbox({
    children
}) {
    return (
        <label className="checkbox_label">
            <input type="checkbox" className="checkbox"/>
            {children}
        </label>
    );
};


export default Checkbox;