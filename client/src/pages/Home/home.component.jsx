import React, { useEffect } from "react";
import PropTypes from "prop-types";

import { connect } from "react-redux";
import { fetchCategoryStart } from "./state/category/category.actions";
import SidebarComponent from "./components/Sidebar/sidebar.component";

const HomeComponent = ({ categoryItems, fetchCategoryStarted }) => {
  useEffect(() => {
    fetchCategoryStarted();
  }, []);

  return (
    <>
      <SidebarComponent categoryItems={categoryItems.length && categoryItems} />
    </>
  );
};

HomeComponent.defaultProps = {
  categoryItems: []
};

HomeComponent.propTypes = {
  fetchCategoryStarted: PropTypes.func.isRequired,
  categoryItems: PropTypes.instanceOf(Array)
};

const mapStateToProps = state => ({
  categoryItems: state.category.categoryItems
});

const mapDispatchToProps = dispatch => ({
  fetchCategoryStarted: () => dispatch(fetchCategoryStart())
});

export default connect(mapStateToProps, mapDispatchToProps)(HomeComponent);
