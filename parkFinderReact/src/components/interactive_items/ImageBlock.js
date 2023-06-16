import React from 'react';
import './ImageBlock.css';


export const ImageBlock = ({
    imageLink
}) => {
    return (
        <div className='block_image'>
            <img className={'image'} src={imageLink} alt={""} />
        </div>
    );
};