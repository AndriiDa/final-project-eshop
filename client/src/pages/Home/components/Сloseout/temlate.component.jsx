import React from "react";
import PropTypes from "prop-types";

const ItemCloseoutComponent = props => {
  const { id, name, imageUrl, activeStyle } = props;
  return (
    <div style={activeStyle}>
      <div>{id}</div>
      <img src={imageUrl} alt={name} />
      <div>{name}</div>
    </div>
  );
};

ItemCloseoutComponent.defaultProps = {
  id: "",
  name: "",
  imageUrl: "",
  activeStyle: {}
};

ItemCloseoutComponent.propTypes = {
  id: PropTypes.string,
  name: PropTypes.string,
  imageUrl: PropTypes.string,
  // eslint-disable-next-line react/forbid-prop-types
  activeStyle: PropTypes.object
};

export default ItemCloseoutComponent;
