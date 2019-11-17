/* eslint-disable jsx-a11y/no-noninteractive-element-interactions */
import React from "react";
import PropTypes from "prop-types";
import { ReactComponent as ArrowIcon } from "../../assets/images/icons/arrow.svg";

const AccordionItemComponent = ({
  name,
  imgUrl,
  children,
  isCollapsed,
  handleClick
}) => {
  const style = {
    collapsed: {
      display: "none"
    },
    expanded: {
      display: "block"
    },
    buttonStyle: {
      display: "block",
      width: "100%"
    }
  };

  return (
    // eslint-disable-next-line jsx-a11y/click-events-have-key-events
    <li
      className="d-flex justify-content-between well"
      onClick={() => handleClick()}
    >
      <img src={imgUrl} alt={name} />
      <span className="list-item-align">{name}</span>
      <ArrowIcon />
      <div
        style={isCollapsed ? style.collapsed : style.expanded}
        aria-expanded={isCollapsed}
      >
        {children}
      </div>
    </li>
  );
};

AccordionItemComponent.defaultProps = {
  name: "",
  imgUrl: "",
  children: ""
};

AccordionItemComponent.propTypes = {
  name: PropTypes.string,
  imgUrl: PropTypes.string,
  children: PropTypes.string,
  isCollapsed: PropTypes.bool.isRequired,
  handleClick: PropTypes.func.isRequired
};

export default AccordionItemComponent;
