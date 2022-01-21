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
import NavBar from './components/NavBar/NavBar';
import Login from './views/Accounts/Login';
import Register from './views/Accounts/Register';
import Mainpage from './views/Mainpage/Mainpage';

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
    <NavBar></NavBar>
    <Route path='/login' component={Login}/>
    <Route path='/signup' component={Register}/>
    <Route path='/main' component={Mainpage}/>
    
    {/* <Switch>
    <Route path='/login' component={Login}/>
    <Route path='/main' component={Mainpage}/>
    
    </NavBar>
    </Switch> */}

    </Router>
    </div>
  );


}



export default App;
