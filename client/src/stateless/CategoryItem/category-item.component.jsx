import React from 'react';
import { ReactComponent as ArrowIcon } from "../../assets/images/icons/arrow.svg";

const CategoryItemComponent = ({ categoryName: { name, imgUrl } }) => {
  return (
    <li className="d-flex justify-content-between well">
      <img src={imgUrl}  alt={name} />
      <span className="list-item-align">{ name }</span>
      <ArrowIcon/>
    </li>
  )
};

export default CategoryItemComponent;
