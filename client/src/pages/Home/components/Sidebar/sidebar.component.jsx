import React from "react";
import PropTypes from "prop-types";
import AccordionWrapperComponent from "../../../../modules/Accordion/accordion-wrapper.component";
import { InnerContent, MenuItem } from "./sidebar.styles";
import "./sidebar.styles.scss";

const SidebarComponent = ({ categoryItems }) => {
  const handleClickInChild = item => {
    window.console.log(item);
  };

  return (
    <div className="nav-side-menu">
      <h2>Portal menu</h2>
      <AccordionWrapperComponent
        elements={categoryItems}
        defaultIndex={0}
        childContentKey="subcategory"
        innerStyledItem={InnerContent}
        menuItem={MenuItem}
        handleChild={handleClickInChild}
      />
    </div>
  );
};

SidebarComponent.defaultProps = {
  categoryItems: []
};

SidebarComponent.propTypes = {
  categoryItems: PropTypes.instanceOf(Array)
};

export default SidebarComponent;
