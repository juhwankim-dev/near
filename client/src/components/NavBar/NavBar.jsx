import React, {useState} from 'react';
import { Navbar, Container, Nav, NavDropdown} from 'react-bootstrap';
import { Router, Link, Route, Switch } from 'react-router-dom';
//페이지 
import Login from '../../views/Accounts/Login';
import Register from '../../views/Accounts/Register';
import Mainpage from '../../views/Mainpage/Mainpage';
//컴포넌트


function NavBar() {
  return(
    <div>
    <Navbar bg="light" expand="lg">
    <Container>   
    <Navbar.Brand><Link to='/main'>로고</Link></Navbar.Brand>
    <Navbar.Toggle aria-controls="basic-navbar-nav" />
    <Navbar.Collapse id="basic-navbar-nav">
      <Nav className="me-auto">
        {/* <Nav.Link href="#home">Home</Nav.Link> */}
        {/* <Nav.Link href="login">Link</Nav.Link> */}
        <NavDropdown title="학습컨텐츠" id="basic-nav-dropdown">
          <NavDropdown.Item><Link to='/content1'>지어</Link></NavDropdown.Item>
          <NavDropdown.Item><Link to='/content2'>수어</Link></NavDropdown.Item>
        </NavDropdown>
        <NavDropdown title="학습게임" id="basic-nav-dropdown">
          <NavDropdown.Item><Link to='/singleplay'>싱글플레이</Link></NavDropdown.Item>
          <NavDropdown.Item><Link to='/teamplay'>팀플레이</Link></NavDropdown.Item>
        </NavDropdown>
        <Nav.Link><Link to='/myvoca'>단어장</Link></Nav.Link>
        
        {/* 로그인 상태 */}
        <NavDropdown title="마이페이지(icon)" id="basic-nav-dropdown">
          <NavDropdown.Item href="#action/3.1">나의학습</NavDropdown.Item>
          <NavDropdown.Item href="#action/3.2">회원정보</NavDropdown.Item>
          <NavDropdown.Divider />
          <NavDropdown.Item href="#action/3.4">로그아웃</NavDropdown.Item>
        </NavDropdown>
        {/* 로그아웃 상태 */}
        <Nav.Link> <Link to='/login'>로그인</Link> </Nav.Link>
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
