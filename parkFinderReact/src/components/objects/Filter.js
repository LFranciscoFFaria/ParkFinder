import './Filter.css'
import Checkbox from "../interactive_items/Checkbox"
import { Button } from '../interactive_items/Button';

const filter = {
    "Type of parking" : ["Covered", "Outside"],
    "Services" : ["Electric recharge service", "Multiple entries and exits", "Quick access", "Elevator"],
    "Reservations" : ["Schedule reservation", "On arrival"]
};

function Filter({
    
}) {
    
    return (
        <div className="filter_box">
            <div className="filter_header">
                <h2>Filter</h2>
                <div className='filter_header_buttons'>
                    <Button buttonStyle="contrast">Remove all filters</Button>
                    <div className='activate'> <Button buttonStyle="default">Apply filters</Button> </div>
                </div>
            </div>
            <div className='filter_block_display'>
                {Object.entries(filter).map(([title, list]) =>
                <div className="filter_block" key={title}>
                    <b>{title}</b>
                    {list.map(element => 
                        <Checkbox key={element}>{element} </Checkbox>
                    )}
                </div>
            )}
            </div>
            <div className='deactivate'> <Button buttonStyle="default">Apply filters</Button> </div>
        </div>
    );
}

export default Filter;