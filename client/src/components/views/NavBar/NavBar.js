import axios from 'axios';
import React, {useEffect, useState} from 'react';
import { Navbar, Container, Nav, NavDropdown} from 'react-bootstrap';
import { Router, Link, Route, Switch } from 'react-router-dom';

function NavBar(props) {

  // const onClickHandler = () => {
  //   axios.get('/api/sign/logout')
  //   .then(response => {
  //     if (response.data.success) {
  //       props.history.push('/login');
  //     } else {
  //       alert('로그아웃에 실패');
  //     }
  //   })
  // }

  // onClick={ ()=>{navigate(`/sign`)}}>
  return(
    <div>
    <Navbar bg="light" expand="lg" >
    <Container >   
    <Navbar.Brand as={Link} to='/main' style={{fontSize:'2.5vh'}}> </Navbar.Brand>
    <Navbar.Toggle aria-controls="basic-navbar-nav" />
    <Navbar.Collapse id="basic-navbar-nav" style={{  flexDirection:'column',alignContent:'flex-end',flexWrap: 'wrap'}}>
      <Nav className="me-auto">
        {/* <Nav.Link href="#home">Home</Nav.Link> */}
        {/* <Nav.Link href="login">Link</Nav.Link> */}
        <NavDropdown style={{fontSize:'1.5vh', fontWeight:'bold', marginRight:'5px',  }} title="학습컨텐츠" id="basic-nav-dropdown">
          <NavDropdown.Item as={Link} to='/finger/1'>지어</NavDropdown.Item>
          <NavDropdown.Item as={Link} to='/sign'>수어</NavDropdown.Item>
        </NavDropdown>
        <NavDropdown style={{fontSize:'1.5vh', fontWeight:'bold', marginRight:'5px',  }} title="학습게임" id="basic-nav-dropdown">
          <NavDropdown.Item as={Link} to='/oxquiz/start'>OX퀴즈</NavDropdown.Item>
          <NavDropdown.Item as={Link} to='/fingerquiz'>지문자퀴즈</NavDropdown.Item>
        </NavDropdown>
        <Nav.Link style={{fontSize:'1.5vh', fontWeight:'bold', marginRight:'5px',  }} as={Link} to='/myvoca'>단어장</Nav.Link>
        

        
        {/* 로그인 상태 */}
        <NavDropdown style={{fontSize:'1.5vh', fontWeight:'bold', marginRight:'5px',  }} title="마이페이지" id="basic-nav-dropdown">
          <NavDropdown.Item href="/mystudy">나의학습</NavDropdown.Item>
          <NavDropdown.Item as={Link} to='/login'>로그인</NavDropdown.Item>
          <NavDropdown.Item as={Link} to='/register'>회원가입</NavDropdown.Item>
          <NavDropdown.Divider />
          <NavDropdown.Item><button>로그아웃</button></NavDropdown.Item>
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