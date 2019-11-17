import React from "react";
import PropTypes from "prop-types";
import { Map } from "react-lodash";

import AccordionItemComponent from "./accordion-item.component";

const AccordionWrapperComponent = ({ elements }) => {
  return (
    <ul>
      <Map
        collection={elements}
        iteratee={elem => (
          <AccordionItemComponent key={elem.id} category={elem} />
        )}
      />
    </ul>
  );
};

AccordionWrapperComponent.defaultProps = {
  elements: []
};

AccordionWrapperComponent.propTypes = {
  elements: PropTypes.instanceOf(Array)
};

export default AccordionWrapperComponent;
