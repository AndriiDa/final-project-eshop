import React from "react";
import styled from "styled-components";


const Footer = (props) => {
  return (
    <FooterWrapper>
      <Text>Footer
      </Text>
    </FooterWrapper>
  )
}

export default Footer

const FooterWrapper = styled.div`
  height: 100 px;
  background-color: black;
`;
const Text = styled.p`
  color: white;
`;
