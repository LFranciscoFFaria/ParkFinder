import './Filter.css'
import Checkbox from "../interactive_items/Checkbox"


function Filter({
    
}) {
    
    return (
        <>
            <div class="filter_box">
                <div class="filter_header">
                    <h2>Filter</h2>
                    <button class="contrast">Remove all filters</button>
                </div>
                <div class="filter_block">
                    <b> Type of parking</b>
                    <Checkbox>Covered </Checkbox>
                    <Checkbox>Outside </Checkbox>
                </div>
                <div class="filter_block">
                    <b> Services</b>
                    <Checkbox>Electric recharge service </Checkbox>
                    <Checkbox>Multiple entries and exits  </Checkbox>
                    <Checkbox>Quick access </Checkbox>
                    <Checkbox>Elevator </Checkbox>
                </div>
                <div class="filter_block">
                    <b> Reservations</b>
                    <Checkbox>Schedule reservation </Checkbox>
                    <Checkbox>On arrival </Checkbox>
                </div>
                <button class="default">Apply filters</button>
            </div>
        </>
    );
}

export default Filter;