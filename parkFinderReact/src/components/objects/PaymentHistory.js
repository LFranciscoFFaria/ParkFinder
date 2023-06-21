import { writeDifInDate } from '../interactive_items/DT';
import './PaymentHistory.css';

function PaymentHistory({
    reservations
}) {
    return (
        <div className='edit_perfil_form_content'>

            <h1>Histórico de Pagamentos</h1>
            {reservations.map((reservation, index) => (
                reservation['pago']?
                    <div key={index} className='edit_perfil_field'>
                        <div className='payment_history_grid_content'>
                            <label className='payment_history_align_bot'>Nome: </label>
                            <div className='payment_history_name_value'>
                                <b className='payment_history_align_bot'>{reservation['nome_utilizador']}</b>
                                <div className='payment_history_value'>
                                    <b>-{reservation['custo']} €</b>
                                </div>
                            </div>
                            <label>Emitido em: </label>
                            <b>{reservation['tipo_lugar'] === 'Instantaneo' ? reservation['data_fim'] : reservation['data_inicio']}</b>
                            <label>Descrição: </label>
                            <div className='payment_history_description'>
                                <b>Pagamento automático</b>
                                <b>'{reservation['nome_parque']}'</b>
                            </div>
                        </div>
                    </div>
                    :
                    null
            ))}
        </div>
    );
};


export default PaymentHistory;