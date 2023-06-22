import { useEffect, useState } from 'react';
import { Button } from '../interactive_items/Button';
import './EditPerfil.css';
import './EditPark.css';
import '../pages/condutor/Details.css';
import '../interactive_items/select.css';
import { ImageBlock } from '../interactive_items/ImageBlock';





var horas = {
    "periodos" : [
        {"dia": 2, "inicio" : 7.5, "fim" : 13},
        {"dia": 3, "inicio" : 14.5, "fim" : 21},
        {"dia": 1, "inicio" : 7.5, "fim" : 13},
        {"dia": 5, "inicio" : 14.5, "fim" : 23},
        {"dia": 5, "inicio" : 7.5, "fim" : 13},
        {"dia": 2, "inicio" : 14.5, "fim" : 21},
        {"dia": 4, "inicio" : 7.5, "fim" : 13},
        {"dia": 3, "inicio" : 7.5, "fim" : 13},
        {"dia": 6, "inicio" : 8, "fim" : 13},
        {"dia": 4, "inicio" : 14.5, "fim" : 21},
        {"dia": 1, "inicio" : 14.5, "fim" : 21},
    ]
}




function floatToTime(floatValue) {
    const hours = Math.floor(floatValue);
    const minutes = Math.floor((floatValue % 1) * 60);
  
    const formattedHours = String(hours).padStart(2, '0');
    const formattedMinutes = String(minutes).padStart(2, '0');
  
    return `${formattedHours}:${formattedMinutes}`;
}




const diasDaSemana = ['segunda','terça','quarta','quinta','sexta','sabado','domingo'];

function EditParkManager({
    parque,
    setParque
}) {
    const [nome, setNome] = useState(parque['nome']);
    const [descricao, setDescricao] = useState(parque['descricao']);
    const [imagem, setImagem] = useState(parque['link_imagem']);
    const [disponivel, setDisponivel] = useState(parque['disponivel']);

    const [schedule, setSchedule] = useState(horas);
    const [scheduleElement, setScheduleElement] = useState(null);
    const [horaInicio, setHoraInicio] = useState('');
    const [horaFim, setHoraFim] = useState('');
    const [day, setDay] = useState(diasDaSemana[0]);
    const [buttonPressed, setButtonPressed] = useState('');
    const [instantaneasLineares, setInstantaneasLineares] = useState(0);
    const [instantaneas, setInstantaneas] = useState({
        precoI: 0,
        precoMin: 0,
        precoMax: 0,
        intervalo: '00:00'
    });
    const [agendadasLineares, setAgendadasLineares] = useState(0);
    const [agendadas, setAgendadas] = useState({
        precoI: 0,
        precoMin: 0,
        precoMax: 0,
        intervalo: '00:00'
    });


    
    useEffect(() => {
        setNome(parque['nome']);
        setDescricao(parque['descricao']);
        setImagem(parque['link_imagem']);
        setDisponivel(parque['disponivel']);
    },[parque]);

    useEffect(() => {
        setScheduleElement(getOpeningHours(schedule));
    },[schedule]);

    function getOpeningHours() {

        let horas2 = [
            {"dia": 1, "horas" : []},
            {"dia": 2, "horas" : []},
            {"dia": 3, "horas" : []},
            {"dia": 4, "horas" : []},
            {"dia": 5, "horas" : []},
            {"dia": 6, "horas" : []},
            {"dia": 7, "horas" : []},
        ];

        
        horas['periodos'].forEach((horario) => {
            horas2[horario['dia'] - 1]['horas'].push(horario['inicio'], horario['fim']);
        });
        
        horas2.forEach((dia) => {
            dia['horas'].sort((a, b) => a - b);
        });
        
        return(
            <ul>
                {Object.entries(horas2).map(([dia, horario]) =>
                    <div className='caracteristics_horario_grid' key={dia}>
                        <b className='caracteristics_dia_grid'>{diasDaSemana[dia]}:</b>
                        {horario['horas'].length === 0? 
                            <Button
                                buttonStyle='sex_button compressed_park_staff_filter_button caracteristics_horas_1_grid'
                                onClick={()=>{setHoraInicio(''); setHoraFim(''); setDay(diasDaSemana[dia])}}
                            >
                                Closed
                            </Button>
                            :
                            null}
                        {horario['horas'].length >= 2? 
                            <Button
                                buttonStyle='sex_button compressed_park_staff_filter_button caracteristics_horas_1_grid'
                                onClick={()=>{setHoraInicio(floatToTime(horario['horas'][0])); setHoraFim(floatToTime(horario['horas'][1])); setDay(diasDaSemana[dia])}}
                            >
                                {floatToTime(horario['horas'][0])} - {floatToTime(horario['horas'][1])}
                            </Button>
                            :
                            null}
                        {horario['horas'].length === 4? 
                            <Button
                                buttonStyle='sex_button compressed_park_staff_filter_button caracteristics_horas_2_grid'
                                onClick={()=>{setHoraInicio(floatToTime(horario['horas'][2])); setHoraFim(floatToTime(horario['horas'][3])); setDay(diasDaSemana[dia])}}
                            >
                                {floatToTime(horario['horas'][2])} - {floatToTime(horario['horas'][3])}
                            </Button>
                            :
                            null}
                    </div>
                )}
            </ul>
        );
    }

    const handleChange = event => {
        const result = ("000" + event.target.value.replace(/\D/g, '')).match(/(000|00[123456789]|0[123456789]\d|[123456789]\d*)$/g).toString();
        return result.slice(0, -2) + '.' + result.slice(-2)
    }

    const changePrecarioAgendada = (event) => {
        event.preventDefault();
        console.log("Change Schedule");
        console.log(agendadas);
        console.log(instantaneas);
    }

    const changeSchedule = (event) => {
        event.preventDefault();
        console.log("Change Schedule");
        console.log(buttonPressed);
        console.log("dia = " + day);
        console.log("hora_inicio = " + horaInicio);
        console.log("hora_fim = " + horaFim);
    }

    const savePark = (event) => {
        event.preventDefault();
        console.log("Save Park");
        console.log("name = " + nome);
        console.log("open = " + disponivel);
        console.log("url = " + imagem);
        console.log("description = " + descricao);
    };

    return (
        <>
            <div className="contact_header">
                <h1>Editar Informação do Parque</h1>
            </div>
            <div className='edit_park_container'>
                <form onSubmit={savePark} className='edit_park_fields_container'>
                    <div className='security_field edit_park_open_close'>
                        <button type='button' className={'button contrast'} onClick={() => setDisponivel(!disponivel)} required>{disponivel? 'Fechar Parque':'Abrir Parque'}</button>
                        <b className={disponivel? 'edit_park_open' : 'edit_park_close'}> {'Parque ' + (disponivel? 'Aberto':'Fechado')} </b>
                    </div>
                    <div className='security_field'>
                        <b> {'Nome'} </b>
                        <input className='edit_perfil_input' placeholder={'Nome'} value={nome} onChange={(e) => setNome(e.target.value)} required/>
                    </div>
                    <div className='security_field'>
                        <b> {'URL da imagem'} </b>
                        <input className='edit_perfil_input' placeholder={'URL da imagem'} value={imagem} type={'url'} onChange={(e) => setImagem(e.target.value)} required/>
                    </div>
                    <div className='security_field'>
                        <b> {'Descrição'} </b>
                        <textarea className='edit_perfil_input' cols="40" rows="5" placeholder={'Descrição'} value={descricao} onChange={(e) => setDescricao(e.target.value)} required/>
                    </div>
                    <div className='security_input_button'>
                        <br/>
                        <Button type='submit' buttonStyle='default'>Gravar Alterações</Button>
                    </div>
                </form>
                <br/>
                <br/>
                <div className='security_field edit_park_fields_container'>
                    {scheduleElement}
                    <form className='edit_park_schedule_container' onSubmit={changeSchedule}>
                        <div className='edit_park_new_period'>
                            <select className='select' value={day} onChange={(e) => setDay(e.target.value)} required>
                                {diasDaSemana.map((dia, index) => <option key={index} value={dia}> {dia} </option>)}
                            </select>
                            <input type='time' value={horaInicio} onChange={(e) => setHoraInicio(e.target.value)} required/>
                            <input type='time' value={horaFim} onChange={(e) => setHoraFim(e.target.value)} required/>
                        </div>
                        <div className='edit_park_new_period'>
                            <Button type='submit' buttonStyle='contrast' onClick={() => setButtonPressed('adicionar')}>Adicionar</Button>
                            <Button type='submit' buttonStyle='contrast' onClick={() => setButtonPressed('remover')}>Remover</Button>
                        </div>
                    </form>
                    <div className='security_input_button'>
                        <br/>
                        <Button type='submit' buttonStyle='default'>Gravar Alterações</Button>
                    </div>
                </div>
                <br/>
                <br/>
                <div className='edit_park_container_precario'>
                    <form className='edit_park_precario' onSubmit={changePrecarioAgendada}>
                        <b className='edit_park_precario_title'>Preçário Reservas Instantâneas</b>
                        <div className='edit_park_percario_fields'>
                            <label>Valor Inicial: (€)</label>
                            <input value={instantaneas.precoI} onChange={(e) => setInstantaneas(p => ({...p, precoI: handleChange(e)}))} required/>
                            {instantaneasLineares?
                                <>
                                    <label>Preço por min: (€)</label>
                                    <input value={instantaneas.precoMax} onChange={(e) => setInstantaneas(p => ({...p, precoMax: handleChange(e)}))} required/>
                                </>
                                :
                                <>
                                    <label>Preço por min (maximo): (€)</label>
                                    <input value={instantaneas.precoMax} onChange={(e) => setInstantaneas(p => ({...p, precoMax: handleChange(e)}))} required/>
                                    <label>Preço por min (minimo): (€)</label>
                                    <input value={instantaneas.precoMin} onChange={(e) => setInstantaneas(p => ({...p, precoMin: handleChange(e)}))} required/>
                                    <label>Intrevalo entre limites:</label>
                                    <input type='time' value={instantaneas.intervalo} onChange={(e) => setInstantaneas(p => ({...p, intervalo: e.target.value}))} required/>
                                </>
                            }
                        </div>
                        <div className='security_input_button'>
                            <br/>
                            <Button type='submit' buttonStyle='default'>Gravar Alterações</Button>
                        </div>
                    </form>
                    <form className='edit_park_precario' onSubmit={changePrecarioAgendada}>
                        <b className='edit_park_precario_title'>Preçário Reservas Agendadas</b>
                        <div className='edit_park_percario_fields'>
                            <label>Valor Inicial: (€)</label>
                            <input value={agendadas.precoI} onChange={(e) => setAgendadas(p => ({...p, precoI: handleChange(e)}))} required/>
                            {agendadasLineares?
                                <>
                                    <label>Preço por min: (€)</label>
                                    <input value={agendadas.precoMax} onChange={(e) => setAgendadas(p => ({...p, precoMax: handleChange(e)}))} required/>
                                </>
                                :
                                <>
                                    <label>Preço por min (maximo): (€)</label>
                                    <input value={agendadas.precoMax} onChange={(e) => setAgendadas(p => ({...p, precoMax: handleChange(e)}))} required/>
                                    <label>Preço por min (minimo): (€)</label>
                                    <input value={agendadas.precoMin} onChange={(e) => setAgendadas(p => ({...p, precoMin: handleChange(e)}))} required/>
                                    <label>Intrevalo entre limites:</label>
                                    <input type='time' value={agendadas.intervalo} onChange={(e) => setAgendadas(p => ({...p, intervalo: e.target.value}))} required/>
                                </>
                            }
                        </div>
                        <div className='security_input_button'>
                            <br/>
                            <Button type='submit' buttonStyle='default'>Gravar Alterações</Button>
                        </div>
                    </form>
                </div>


            </div>
        </>
    );
};


export default EditParkManager;