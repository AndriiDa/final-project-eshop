import React from 'react'
import { NavLink } from 'react-router-dom'
import styled from "styled-components";

const Navbar = (props) => {
  return (
    <NavbarWrapper>
      <div><NavLink to="/login" >Login</NavLink></div>
    </NavbarWrapper>
  )
}

export default Navbar

const NavbarWrapper = styled.div`
  padding: 10 px;
  background-color: green;
`;