import React, { Component } from 'react';
import './Login.css'
import axios from 'axios';
class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      token: '',
      error: '',
    };
  }

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value, error: '' });
  };

  handleLogin = (event) => {
    event.preventDefault();
    const { username, password } = this.state;

    if (!username || !password) {
      this.setState({ error: 'Introdu datele de autentificare', message: '' });
    } else if (username === "maria.marin" && password === "20august") {
      //n am reusit sa fac legatura cu python/java sa pot lua token-ul
      this.setState({  token: token, message: 'Autentificat cu succes!'  });
      console.log('Autentificat cu succes!');
    } else {
      this.setState({ error: 'Datele de autentificare sunt incorecte.', message: '' });
    }
  };

  render() {
    const { username, password, error } = this.state;

    return (
      <div className="Login">
        <header className="Login-header">
          <form onSubmit={this.handleLogin}>
            <label>
              Username:
              <input
                type="text"
                name="username"
                value={username}
                onChange={this.handleInputChange}
              />
            </label>
            <br />
            <label>
              Password:
              <input
                type="password"
                name="password"
                value={password}
                onChange={this.handleInputChange}
              />
            </label>
            <br />
            <button type="button" onClick={this.handleLogin}>Login</button>
          </form>
          {error && <p style={{ color: 'red' }}>{error}</p>}
        </header>
      </div>
    );
  }
}

export default Login;
