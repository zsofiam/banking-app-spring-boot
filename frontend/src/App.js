import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";
import Header from "./header";
import Footer from "./footer";

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
      <div>
          <Header />
          <main style={{ padding: "1rem" }}>
              <h2>Accounts</h2>
              <div>
                  {accounts.map((account) => (
                      <div key={account.id}>
                          {account.number}, balance: {account.balance}
                      </div>
                  ))}
              </div>
          </main>

          <Footer />
      </div>

    /*<div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
          <h4>Accounts</h4>
          <div>
              {accounts.map((account) => (
                  <div key={account.id}>
                      {account.number}, balance: {account.balance}
                  </div>
              ))}
          </div>
      </header>



    </div>*/

  );

}

export default App;
