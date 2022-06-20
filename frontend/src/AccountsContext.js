import axios from "axios";
import React, { useState, createContext, useEffect } from "react";

export const AccountsContext = createContext();

export const AccountsProvider = (props) => {
    const [accountsContext, setAccountsContext] = useState([]);

    useEffect(() => {
        axios
            .get("/account")
            .then((res) =>
                setAccountsContext(res.data)
            );
    }, []);

    function getLatestAccounts() {
        axios
            .get("/account")
            .then((res) =>
                setAccountsContext(res.data)
            );
    }

    return (
        <AccountsContext.Provider
            value={{
                accountsContext, setAccountsContext, getLatestAccounts
            }}
        >
            {props.children}
        </AccountsContext.Provider>
    );

};