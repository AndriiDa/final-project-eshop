import React from "react";
import PropTypes from "prop-types";
import AccordionWrapperComponent from "../../../../modules/Accordion/accordion-wrapper.component";
import "./sidebar.styles.scss";

const SidebarComponent = ({ categoryItems }) => {
  return (
    <div className="nav-side-menu">
      <h2>Portal menu</h2>
      <AccordionWrapperComponent elements={categoryItems} />
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
