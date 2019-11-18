import React from "react";
import PropTypes from "prop-types";
import { ReactComponent as ArrowIcon } from "../../assets/images/icons/arrow.svg";
import "./accordion-item.style.scss";

const AccordionItemComponent = props => {
  const { name, imgUrl, children, isCollapsed, handleClick } = props;
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
    <li>
      <button
        type="button"
        className="d-flex justify-content-between well expand"
        onClick={() => handleClick()}
      >
        <img src={imgUrl} alt={name} />
        <span className="list-item-align">{name}</span>
        <ArrowIcon />
      </button>
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
