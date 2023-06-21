
export function getDifInDateTime (start, end) {
    let s_date = new Date(start);
    let e_date = new Date(end);
    return (e_date - s_date) / (1000 * 60);
}


export function writeDifInDate (start, end) {
    var aux = (getDifInDateTime (start, end)) / (60 * 24);
    let difInDays = Math.floor(aux);
    var aux = (aux - difInDays) * 24;
    let difInHours = Math.floor(aux);
    var aux = (aux - difInHours) * 60;
    let difInMin = Math.floor(aux);

    if (difInDays > 0) 
        var r = `${difInDays}d ${difInHours}h ${difInMin}min`;
    else if (difInHours > 0) 
        var r = `${difInHours}h ${difInMin}min`;
    else 
        var r = `${difInMin}min`;
    
    return r;
}