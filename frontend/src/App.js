import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";
import Header from "./header";
import Footer from "./footer";
import AccountList from "./accountList";

function App() {

  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
    axios
        .get("/account")
        .then((res) =>
            setAccounts(res.data)
        );
  }, []);

  return (

      <div className="container-fluid mt-3">
          <Header />

          <h2>Accounts</h2>
          <AccountList accounts={accounts}/>
          <Footer />
      </div>

      /*<div>
          <Header />
          <main style={{ padding: "1rem" }}>
              <h2>Accounts</h2>
              <div className="wrapper">
              <AccountList accounts={accounts}/>
              </div>
          </main>
          <Footer />
      </div>*/

  );

}

export default App;
