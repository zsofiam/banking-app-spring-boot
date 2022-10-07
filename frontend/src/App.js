import './App.css';
import {useContext, useEffect, useState} from "react";
import Header from "./header";
import Footer from "./footer";
import AccountList from "./accountList";
import {AccountsContext} from "./AccountsContext";
import {createContext} from "react";

// export const AccountsListContext = createContext();

function App() {

    const { accountsContext, setAccountsContext, getLatestAccounts } = useContext(AccountsContext);

  const [accounts, setAccounts] = useState([]);

    /*useEffect(() => {
      axios
          .get("/account")
          .then((res) =>
              setAccounts(res.data)
          );
    }, []);*/
    useEffect(() => {
        getLatestAccounts();
    }, []);


  return (
      // <AccountsListContext.Provider value={setAccounts}>
      <div className="container-fluid mt-3">
          <Header />

          <h2>Accounts</h2>
          <AccountList accounts={accountsContext}/>
          <Footer />
      </div>
          // </AccountsListContext.Provider>
  );

}

export default App;
