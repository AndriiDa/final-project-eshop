import React from "react";
import logo from "../../assets/images/logo.png";
import { HeaderWrapper } from "./header.styles";

const HeaderComponent = () => {
  return (
    <HeaderWrapper>
      <img src={logo} alt="" />
      <div>Het</div>
    </HeaderWrapper>
  );
};
export default HeaderComponent;
