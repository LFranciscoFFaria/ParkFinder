import './Contacts.css';


function Contacts({
    listUsers,
    createButton = null,
    revoke = null,
    add = null,
    removeButton = null,
    showPark = false,
    title
}) {
    return (
        <div className="contact_display">
            <div className="contact_header">
                <h1>{title}</h1>
                {createButton}
            </div>
            {listUsers.map((user) =>
                <div key={user['id']} className="contact_box">
                    <b className='name'>{"Rui Antunes Gil Gomes de Sá Ribeiro Martins"}</b>
                    <label className='email'>Email:</label>
                    <label className='tel'>Nº Telemovel:</label>
                    <b className='c1'>{user["email"]}</b>
                    <b className='c2'>{user["telemovel"]}</b>
                    <div className='c4'>
                        <div className='contact_buttons'>{revoke}</div>
                        <div className='contact_buttons'>{add}</div>
                        <div className='contact_buttons'>{removeButton}</div>
                    </div>

                    {user["parques"] && showPark?
                        <>
                            <label className='park'>Parques:</label>
                            <div className='c3'>{user["parques"].map((parque) => <li key={parque}> {parque} </li>)}</div>
                        </>
                        :
                        null
                    }

                </div>
            )}
        </div>
    );
}

export default Contacts;