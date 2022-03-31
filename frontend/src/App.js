import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";

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
    <div className="App">
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



    </div>
  );
}

export default App;
