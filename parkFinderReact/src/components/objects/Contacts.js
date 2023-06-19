import './Contacts.css';

const administradores = [
    {
        'id': 0,
        'nome': "joao",
        'email': "joao@gmail.com",
        'telemovel': "936978575",
        'password': "joao",
        'parques': ["BRAGA PARQUE","B&B BRAGA LAMAÇÃES"],
    },
    {
        'id': 1,
        'nome': "miguel",
        'email': "miguel@gmail.com",
        'telemovel': "936978575",
        'password': "miguel",
        'parques': ["BRAGA PARQUE","B&B BRAGA LAMAÇÃES"],
    },
    {
        'id': 2,
        'nome': "antonio",
        'email': "antonio@gmail.com",
        'telemovel': "936978575",
        'password': "antonio",
        'parques': ["BRAGA PARQUE","B&B BRAGA LAMAÇÃES"],
    },
]
const gestores = [
    {
        'id': 0,
        'nome': "rui",
        'email': "rui@gmail.com",
        'telemovel': "936978575",
        'password': "rui",
        'parques': ["BRAGA PARQUE","B&B BRAGA LAMAÇÃES"],
        'administradores': ["joao","miguel","antonio"],
    },
    {
        'id': 1,
        'nome': "carlos",
        'email': "carlos@gmail.com",
        'telemovel': "936978575",
        'password': "carlos",
        'parques': ["Alex_Nao","Sabe_Conduzir"],
        'administradores': ["Sr. Qual","Sr. Alex","Sr. ???"],
    },
    {
        'id': 2,
        'nome': "pedro",
        'email': "pedro@gmail.com",
        'telemovel': "936978575",
        'password': "pedro",
        'parques': ["esquina",],
        'administradores': ["pedro2","rui2","antonio2"],
    },
]

function separateString(string) {
    let lines = string.split("\n");

    return(
        <ul>
            {lines.map((line, index) => (
                <li key={index}>{line}</li>
            ))}
        </ul>
    )
}


function Contacts({
    listUsers,
    createButton = null,
    editButton = null,
    removeButton = null,
    title
}) {
    return (
        <div className="contact_display">
            <div className="contact_header">
                <h1>{title}</h1>
                <div className='contact_button'>{createButton}</div>
            </div>
            {listUsers.map((user) => <>
                <div key={user['id']} className="contact_box">
                    <b className='name'>{"Rui Antunes Gil Gomes de Sá Ribeiro Martins"}</b>
                    <label className='email'>Email:</label>
                    <label className='tel'>Nº Telemovel:</label>
                    <b className='c1'>{user["email"]}</b>
                    <b className='c2'>{user["telemovel"]}</b>
                    <label className='park'>Parques:</label>
                    <div className='c3'>{user["parques"].map((parque) => <li> {parque} </li>)}</div>
                    <b className='id'> <div className='contact_id'>User ID: {user["id"]}</div></b>
                    <div className='c4'>
                        <div className='contact_button'>{editButton}</div>
                        <div className='contact_button'>{removeButton}</div>
                    </div>
                </div>
                </>
            )}
        </div>
    );
}

export default Contacts;