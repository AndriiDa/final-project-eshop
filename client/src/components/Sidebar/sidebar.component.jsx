import React from "react";
import PropTypes from "prop-types";
import AccordionWrapperComponent from "../../modules/Accordion/accordion-wrapper.component";
import { InnerContent, MenuItem } from "./sidebar.styles";
import s from "./sidebar.module.scss";

const SidebarComponent = ({ categoryItems }) => {
  const handleClickInChild = item => {
    window.console.log(item);
  };

  return (
    <div className={s.menu}>
      <h2>Side Menu</h2>
      <AccordionWrapperComponent
        elements={categoryItems}
        defaultIndex={0}
        childContentKey="subcategory"
        menuItem={MenuItem}
        innerStyledItem={InnerContent}
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
