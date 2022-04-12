import React from "react";
import {useState} from "react";
import axios from "axios";

const Form = (props) => {


    const [amount, setAmount] = useState(0);
    const [destination, setDestination] = useState('');

    function transferMoney() {
        console.log('amount: ' + amount);
        console.log('destination: ' + destination);
        const transfer = {"amount": amount, "destinationAccountNumber" : destination};
        axios
            .put(`/account/${props.id}/transfer`,
        transfer
        )
            .then((response) => {
                console.log(response.data);
            });
    }

    const closeForm = function (e) {
        e.preventDefault();
        transferMoney()
        props.toggleComponent();
    };

    return (
        <div className="Form">
            <h1>Transfer Money</h1>
            <form onSubmit={closeForm}>
                <input id="id" type="hidden"
                       name="id"
                       value={props.id}/>
                <span>From: {props.account.number}</span>
                <br/>
                <label htmlFor="amount">Amount: </label>
                <input id="amount"
                       name="amount"
                       placeholder="enter amount"
                       value={amount}
                       onChange={event => setAmount(parseFloat(event.target.value))}
                       type="number"/>
                <input type="submit" onClick={closeForm} value={'Send'}/>
                <br/>
                <label htmlFor="destination-number">Destination Account: </label>
                <input id="destination-number"
                       name="destinationAccountNumber"
                       placeholder="enter destination account"
                       value={destination}
                       onChange={event => setDestination(event.target.value)}
                       type="text"/>

            </form>

        </div>
    );
};

export { Form };