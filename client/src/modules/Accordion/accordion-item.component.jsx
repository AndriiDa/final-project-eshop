import React from "react";
import PropTypes from "prop-types";
import { ReactComponent as ArrowIcon } from "../../assets/images/icons/arrow.svg";
import "./accordion-item.style.scss";

const AccordionItemComponent = props => {
  const {
    name,
    imgUrl,
    children,
    isCollapsed,
    innerComponent,
    handleClick,
    handleChild
  } = props;

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

  const StyledItem = innerComponent;

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
        {children.length > 0 &&
          children.map(item => {
            return (
              <StyledItem key={item.id} onClick={() => handleChild(item)}>
                {item.name}
              </StyledItem>
            );
          })}
      </div>
    </li>
  );
};

AccordionItemComponent.defaultProps = {
  name: "",
  imgUrl: "",
  children: "",
  innerComponent: null
};

AccordionItemComponent.propTypes = {
  name: PropTypes.string,
  imgUrl: PropTypes.string,
  children: PropTypes.string,
  isCollapsed: PropTypes.bool.isRequired,
  handleClick: PropTypes.func.isRequired,
  handleChild: PropTypes.func.isRequired,
  // eslint-disable-next-line react/forbid-prop-types
  innerComponent: PropTypes.any
};

export default AccordionItemComponent;
