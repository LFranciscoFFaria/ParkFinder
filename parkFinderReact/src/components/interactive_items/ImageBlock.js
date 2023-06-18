import React from 'react';
import './ImageBlock.css';


export const ImageBlock = ({
    imageLink,
    no_shadow,
    no_scale,
    no_border_radius,
}) => {
    return (
        <div className={
            'block_image' +
            (no_border_radius? '' : ' border_radius') +
            (no_scale? '' : ' scale') +
            (no_shadow? '' : ' shadow')
        }>
            <img className={'image'} src={imageLink} alt={""} />
        </div>
    );
};