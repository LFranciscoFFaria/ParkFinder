import React from 'react';
import './Button.css';
import { Link } from 'react-router-dom';



export const Button = ({
    children,
    type,
    onClick,
    link,
    buttonStyle,
    buttonSize
}) => {
    return (
        <button
            className={`button ${buttonStyle} ${buttonSize}`}
            onClick={onClick}
            type={type}
        >
            {link ?
                <Link to={link} className='button_link_default'>
                    {children}
                </Link>
                :
                children
            }
        </button>
    );
};
    