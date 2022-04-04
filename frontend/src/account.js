import React from 'react';
import {Link} from "react-router-dom";


const Account = (props) => {

    const{id, number, balance} = props.account;
    return (

        <div className="card mb-2">
            <div className="card-body">
                <h4 className="card-title">{number}</h4>
                <p className="card-text">balance: {balance}</p>
                <Link to={`/accounts/${id}`} className="card-link">{number}</Link>
                <Link to={`/accounts/${id}`} className="card-link">Start transaction</Link>

            </div>
        </div>
    )
}

export default Account;