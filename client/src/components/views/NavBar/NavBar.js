import React, {useState} from 'react';
import { Navbar, Container, Nav, NavDropdown} from 'react-bootstrap';
import { Router, Link, Route, Switch } from 'react-router-dom';

function NavBar() {
  return(
    <div>
    <Navbar bg="light" expand="lg">
    <Container>   
    <Navbar.Brand as={Link} to='/main'>로고</Navbar.Brand>
    <Navbar.Toggle aria-controls="basic-navbar-nav" />
    <Navbar.Collapse id="basic-navbar-nav">
      <Nav className="me-auto">
        {/* <Nav.Link href="#home">Home</Nav.Link> */}
        {/* <Nav.Link href="login">Link</Nav.Link> */}
        <NavDropdown title="학습컨텐츠" id="basic-nav-dropdown">
          <NavDropdown.Item as={Link} to='/finger/1'>지어</NavDropdown.Item>
          <NavDropdown.Item as={Link} to='/sign'>수어</NavDropdown.Item>
        </NavDropdown>
        <NavDropdown title="학습게임" id="basic-nav-dropdown">
          <NavDropdown.Item as={Link} to='/oxquiz'>OX퀴즈</NavDropdown.Item>
          <NavDropdown.Item as={Link} to='/teamplay'>팀플레이</NavDropdown.Item>
        </NavDropdown>
        <Nav.Link as={Link} to='/myvoca'>단어장</Nav.Link>
        
        {/* 로그인 상태 */}
        <NavDropdown title="마이페이지" id="basic-nav-dropdown">
          <NavDropdown.Item href="/mystudy">나의학습</NavDropdown.Item>
          <NavDropdown.Item href="/profile">회원정보</NavDropdown.Item>
          <NavDropdown.Divider />
          <NavDropdown.Item href="#action/3.4">로그아웃</NavDropdown.Item>
        </NavDropdown>
        {/* 로그아웃 상태 */}
        <Nav.Link as={Link} to='/login'>로그인</Nav.Link>
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