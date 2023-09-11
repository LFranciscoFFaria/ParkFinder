import React from 'react';
import './Checkbox.css';



function Checkbox({
    children,
    checked,
    onChange
}) {
    return (
        <label className="checkbox_label">
            <input type="checkbox" className="checkbox" checked={checked} onChange={onChange}/>
            {children}
        </label>
    );
};


export default Checkbox;