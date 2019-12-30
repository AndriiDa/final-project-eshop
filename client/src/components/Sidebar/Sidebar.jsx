import React from "react";
import PropTypes from "prop-types";
import AccordionWrapperComponent from "../../modules/Accordion/accordion-wrapper.component";
import { InnerContent, MenuItem } from "./Sidebar.styles";
import s from "./Sidebar.module.scss";

const Sidebar = ({ categoryItems }) => {
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

Sidebar.defaultProps = {
  categoryItems: []
};

Sidebar.propTypes = {
  categoryItems: PropTypes.instanceOf(Array)
};

export default Sidebar;
