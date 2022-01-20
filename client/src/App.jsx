/* eslint-disable */
import './App.css';

import React, { useState, useEffect } from 'react';
import { Navbar, Container, Nav, NavDropdown, Button } from 'react-bootstrap';
import { Router, Link, Route, Switch } from 'react-router-dom';
import { history } from './utils/history';
import { PrivateRoute } from './hoc/PrivateRoute';
import { useDispatch } from 'react-redux';

// Page Load
// import LandingPage from './views/LandingPage/Account';
import Login from './views/Accounts/Login';
import Register from './views/Accounts/Register';






function App() {
  const [isLogin, setIslogin] = useState(false);

  const users = () => {
    const user = localStorage.getItem('user');
    if (user === null) {
      setIslogin(false);
    } else {
      setIslogin(true);
    }
  };

  return (
    <div>
      <Router history={history}>
        <Switch>
          {/* <Route path="/login" component={LandingPage} /> */}
          <Route path="/login" component={Login} />
          <Route path="/signup" component={Register} />
          <Route path="/">ㅎㅇ</Route>
        </Switch>
      </Router>
    </div>

  );


}



export default App;
