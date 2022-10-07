import React, {useContext} from "react";
import {useState} from "react";
import axios from "axios";
import {AccountsContext} from "./AccountsContext";
import {AccountsListContext} from "./App";

const Form = (props) => {

    const {accountsContext, getLatestAccounts, setAccountsContext} = useContext(AccountsContext);
    // const setAccounts = useContext(AccountsListContext);
    const [amount, setAmount] = useState(0);
    const [destination, setDestination] = useState('');

    function transferMoney() {
        console.log('amount: ' + amount);
        console.log('destination: ' + destination);
        const transfer = {"amount": amount, "destinationAccountNumber": destination};
        return axios
            .put(`/account/${props.id}/transfer`,
                transfer
            );
    }


    const closeForm = function (e) {
        e.preventDefault();
        transferMoney().then(getLatestAccounts);
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
                <input type="submit" value={'Send'}/>
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

export {Form};