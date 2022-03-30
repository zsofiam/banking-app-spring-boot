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
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>

      <div>
        {accounts.map((account) => (
            <div key={account.id}>
              {account.number} ({account.balance})
            </div>
        ))}
      </div>

    </div>
  );
}

export default App;
