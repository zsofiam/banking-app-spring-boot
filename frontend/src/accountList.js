import React from "react";
import Account from "./account";

const AccountList = (props) => {
    return (

        <div>
            {props.accounts.map((account) => (
                <Account
                    key={account.id}
                    id={account.id}
                    account={account}
                />
            ))}
        </div>
    );
};

export default AccountList;