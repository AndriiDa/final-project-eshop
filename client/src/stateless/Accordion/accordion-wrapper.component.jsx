/* eslint-disable react/prop-types */
import React from "react";
import PropTypes from "prop-types";
import AccordionItemComponent from "./accordion-item.component";

const AccordionWrapperComponent = props => {
  const { elements, defaultIndex } = props;

  const [bindIndex, setBindIndex] = React.useState(defaultIndex);

  const changeItem = itemIndex => {
    if (itemIndex !== bindIndex) setBindIndex(itemIndex);
  };

  return (
    <ul className="wrapper">
      {elements.length &&
        elements.map((item, index) => (
          <AccordionItemComponent
            key={`${item.name}${item.name}`}
            isCollapsed={bindIndex !== index}
            name={item.name}
            handleClick={() => changeItem(index)}
            imgUrl={item.imgUrl}
            // eslint-disable-next-line react/no-children-prop
            children="childrens"
          />
        ))}
    </ul>
  );
};

AccordionWrapperComponent.defaultProps = {
  elements: [],
  defaultIndex: 0
};

AccordionWrapperComponent.propTypes = {
  elements: PropTypes.instanceOf(Array),
  defaultIndex: PropTypes.number
};

export default AccordionWrapperComponent;
