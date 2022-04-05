import React from 'react';
import Header from "./header";
import Footer from "./footer";


const AccountDetail = (props) => {

    const{id, number, balance} = props.account;
    return (
        <div>
            <Header />
            <main style={{ padding: "1rem" }}>
                <h2>Accounts</h2>
                <div className="wrapper">
                    <div className="card">
                        <p>id: {id}</p>
                        <p>number: {number}</p>
                        <p>balance: {balance}</p>
                    </div>
                </div>
            </main>
            <Footer />
        </div>

    )
}

export default AccountDetail;