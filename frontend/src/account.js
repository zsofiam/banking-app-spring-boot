import React from 'react';
import {Link} from "react-router-dom";
import {useState} from "react";
import {Form} from "./Form.";


const Account = (props) => {

    const{id, number, balance} = props.account;
    const [componentOn, setComponentOn] = useState(false);

    function toggleComponent() {
        setComponentOn(!componentOn);
    }

    function displayForm(e) {
        e.preventDefault();
        toggleComponent();
    }

    return (
<>
        <div className="card mb-2" id={id}>
            <div className="card-body">
                <h4 className="card-title">{number}</h4>
                <p className="card-text">balance: {balance}</p>
                <Link to={`/accounts/${id}`} className="card-link">{number}</Link>
                <Link to={`/accounts/${id}`} onClick={displayForm} className="card-link">Start transaction</Link>
            </div>
        </div>

    {componentOn?
        <Form key={props.account.id}
              id={props.account.id}
              account={props.account}
              toggleComponent={toggleComponent}>
        </Form>
        :<></>
    }

</>
    )
}

export default Account;