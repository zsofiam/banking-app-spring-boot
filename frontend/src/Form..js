import React from "react";

const Form = (props) => {
    const closeForm = function (e) {
        e.preventDefault();
        console.log('Clicked.');
        props.toggleComponent();
    };
    return (
        <div className="Form">
            <h1>Transfer Money</h1>
            <form action="#">
                <input id="id" type="hidden" value={props.id}/>
                <span>From: {props.account.number}</span>
                <br/>
                <label htmlFor="destination-number">Destination Account: </label>
                <input id="destination-number" type="text"/>
                <br/>
                <label htmlFor="amount">Amount: </label>
                <input id="amount" type="number"/>
                <input type="submit" onClick={closeForm} value={'Send'}/>
            </form>

        </div>
    );
};

export { Form };