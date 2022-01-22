/* eslint-disable */
import './App.css';
import React, { useState, useEffect } from 'react';
import {Navbar, Container, Nav, NavDropdown, Button } from 'react-bootstrap';
import { Router, Link, Route, Switch } from 'react-router-dom';
// import { history } from './utils/history';


// import { PrivateRoute } from './hoc/PrivateRoute';
// import { useDispatch } from 'react-redux';

// Page Load
// import LandingPage from './views/LandingPage/Account';








function App() {
  return (
    <div className="App">
      
      <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand><Link to="/">로고 자리</Link></Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link> <Link to="/contents">학습컨텐츠</Link> </Nav.Link>
            <Nav.Link> <Link to="/games">학습게임</Link> </Nav.Link>
            <Nav.Link> <Link to="/login">로그인</Link> </Nav.Link>
            <Nav.Link> <Link to="/Signup">회원가입</Link> </Nav.Link>

            <NavDropdown title="나의학습" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">마이페이지</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">아무기능1</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">로그아웃</NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>


    <Route exact path='/'>
    <div>메인페이지에요</div>
    </Route>
    <Route path='/login'>
    <div></div>
    </Route>
    <Route path='/signup'>
    <div></div>
    </Route>
    <Route path='/contents'>
    <div>학습컨텐츠</div>
    </Route>
    <Route path='/games'>
    <div>학습게임</div>
    </Route>

    </div>
  );
}

export default App;
