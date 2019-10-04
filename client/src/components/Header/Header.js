import React from "react";
import logo from "../../assets/images/logo.png";
import styled from "styled-components";

// import {NavLink} from "react-router-dom";
const Header = (props) => {
  return (
    <HeaderWrapper>
      <img src={logo} alt=""/>
      <div>Het
      </div>
    </HeaderWrapper>
  )
}

export default Header

const HeaderWrapper = styled.div`
  padding: 10 px;
  background-color: red;
`;
