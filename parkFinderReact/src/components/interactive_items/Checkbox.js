import React from 'react';
import './Checkbox.css';



function Checkbox({
    children,
    value,
    onChange
}) {
    return (
        <label className="checkbox_label">
            <input type="checkbox" className="checkbox" value={value} onChange={onChange}/>
            {children}
        </label>
    );
};


export default Checkbox;