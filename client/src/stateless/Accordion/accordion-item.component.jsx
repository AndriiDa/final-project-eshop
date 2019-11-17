import React from "react";
import PropTypes from "prop-types";
import { ReactComponent as ArrowIcon } from "../../assets/images/icons/arrow.svg";

const AccordionItemComponent = ({ category: { name, imgUrl } }) => {
  return (
    <li className="d-flex justify-content-between well">
      <img src={imgUrl} alt={name} />
      <span className="list-item-align">{name}</span>
      <ArrowIcon />
    </li>
  );
};

AccordionItemComponent.defaultProps = {
  category: {}
};

AccordionItemComponent.propTypes = {
  category: PropTypes.instanceOf(Object)
};

export default AccordionItemComponent;
