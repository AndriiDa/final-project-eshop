import React from "react";
import { NavLink } from "react-router-dom";
import { NavbarWrapper } from "./navbar.styles";

const Navbar = () => {
  return (
    <NavbarWrapper>
      <div>
        <NavLink to="/login">Login</NavLink>
      </div>
    </NavbarWrapper>
  );
};
export default Navbar;
