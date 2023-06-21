import React from 'react';
import './ImageBlock.css';


export const ImageBlock = ({
    imageLink,
    no_shadow,
    no_scale,
    no_border_radius,
    image_icon,
    imageSize = "image"
}) => {
    return (
        <div className={
            'block_image' +
            (no_border_radius || image_icon? '' : ' border_radius') +
            (no_scale || image_icon? '' : ' scale') +
            (no_shadow || image_icon? '' : ' shadow')
        }>
            <img className={`${imageSize}` + (image_icon? ' image_icon' : '')} src={imageLink} alt={""} />
        </div>
    );
};