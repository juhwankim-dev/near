import axios from 'axios';
import React, {useEffect, useState} from 'react';
import { Navbar, Container, Nav, NavDropdown} from 'react-bootstrap';
import { Router, Link, Route, Switch } from 'react-router-dom';

function NavBar(props) {

  return(
    <div>
    <Navbar bg="light" expand="lg" >
    <Container>   
    <Navbar.Toggle aria-controls="basic-navbar-nav" />
    <Navbar.Collapse id="basic-navbar-nav" style={{flexDirection:'column',alignContent:'flex-end',flexWrap: 'wrap'}}>
      <Nav className="me-auto">
        <NavDropdown title="학습컨텐츠" id="basic-nav-dropdown">
          <NavDropdown.Item as={Link} to='/finger/1'>지어</NavDropdown.Item>
          <NavDropdown.Item as={Link} to='/sign'>수어</NavDropdown.Item>
        </NavDropdown>
        <NavDropdown title="학습게임" id="basic-nav-dropdown">
          <NavDropdown.Item as={Link} to='/oxquiz/start'>OX퀴즈</NavDropdown.Item>
          <NavDropdown.Item as={Link} to='/fingerquiz'>지문자퀴즈</NavDropdown.Item>
        </NavDropdown>
        <Nav.Link as={Link} to='/myvoca'>단어장</Nav.Link>
        
        {/* 로그인 상태 */}
        <NavDropdown title="마이페이지" id="basic-nav-dropdown">
          <NavDropdown.Item as={Link} to='/login'>로그인</NavDropdown.Item>
          <NavDropdown.Item as={Link} to='/register'>회원가입</NavDropdown.Item>
          <NavDropdown.Divider />
          <NavDropdown.Item as={Link} to='/main'>로그아웃</NavDropdown.Item>
        </NavDropdown>
      </Nav>
    </Navbar.Collapse>
    </Container>
    </Navbar>  

    {/* <Switch>
    <Route path='/login' component={Login}>
    </Route>
    <Route path='/main' component={Mainpage}>
    </Route>
    </Switch> */}
    </div>
  )
}

export default NavBar;