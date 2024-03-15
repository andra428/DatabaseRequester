// index.js sau alt fișier principal
import React from 'react';
import ReactDOM from 'react-dom';
import Login from './Login'
import './index.css';
import reportWebVitals from './reportWebVitals';

ReactDOM.render(
  <React.StrictMode>
    <Login />
  </React.StrictMode>,
  document.getElementById('root')
);

reportWebVitals();
