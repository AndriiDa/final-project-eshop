import React from "react";
import {NavLink} from "react-router-dom";

import s from "./productItem.module.scss";

const ProductItem = (props) => {
    let path = "/product/" + props.id + "/comments";
  return (
      <div className={s.product}>
          <NavLink to={path}>{props.name}</NavLink>
      </div>
  );
};

export default ProductItem;
