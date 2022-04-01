import React from 'react';
import {Link} from "react-router-dom";


const Account = (props) => {

    const{id, number, balance} = props.account;
    return (

        <div className="card">
            <div className="properties-container">
                <p className="account-link">
                    <Link to={`/accounts/${id}`}>

                        {number}

                    </Link>
                </p>
                <p>balance: {balance}</p>
            </div>

        </div>
    )
}

export default Account;