import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
// import 'bootstrap/dist/css/bootstrap.min.css';
import {
    BrowserRouter,
    Routes,
    Route,
    Navigate
} from "react-router-dom";
import About from "./about";
import Contacts from "./contacts";
import Layout from "./layout";

ReactDOM.render(
    <BrowserRouter>
        <React.StrictMode>
            <Routes>
                {/*<Route path="/" exact element={<App />} />*/}
                <Route path="/" element={<Navigate replace to="/accounts" />} />
                <Route path="/accounts" element={<App />} />
                <Route path="about" element={<About />} />
                <Route path="contacts" element={<Contacts />} />
                <Route path="layout" element={<Layout />} />
            </Routes>
        </React.StrictMode>
    </BrowserRouter>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
