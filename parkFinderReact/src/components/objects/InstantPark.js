import { Button } from "../interactive_items/Button";


function separateString(string) {
    let lines = string.split("\n");

    return(
        <table>
              <tr>
                <th><b>Matrícula</b></th>
                <th></th>
            </tr>
            {lines.map((line, index) => (
            <tr>
                <td >{line}</td> 
                <td><Button className="default">Remover</Button></td> 
            </tr>
            ))}
        </table>
    )
}

function InstantPark({
    parque
}) {
    return (
        <div className="details_pages_display">
             <Button class="default">Adicionar</Button>
            {separateString(parque['instant'])}
            
        </div>
    );
};


export default InstantPark;