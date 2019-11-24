import styled from "styled-components";

// eslint-disable-next-line import/prefer-default-export
export const InnerContent = styled.div`
  color: red;
`;

export const MenuItem = styled.li`
  padding: 12px 12px 12px 22px;
  cursor: pointer;

  span {
    width: 70%;
    text-align: left;
  }

  img {
    margin-right: 10px;
    width: 16px;
  }
`;
