import React from 'react';
import './Checkbox.css';



export const Checkbox = ({
    children,
}) => {
    return (
        <label className="checkbox_label">
            <input type="checkbox" className="checkbox"/>
            {children}
        </label>
    );
};
    