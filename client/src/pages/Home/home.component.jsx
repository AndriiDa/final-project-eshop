import React, { useEffect } from "react";
import PropTypes from "prop-types";

import { connect } from "react-redux";
import { fetchCategoryStart } from "./state/category/category.actions";
import SidebarComponent from "./components/Sidebar/sidebar.component";
import CloseoutComponent from "./components/Сloseout/closeout.component";

const HomeComponent = ({ categoryItems, fetchCategoryStarted }) => {
  useEffect(() => {
    fetchCategoryStarted();
    // eslint-disable-line
  }, []);

  return (
    <div className="d-flex">
      <SidebarComponent categoryItems={categoryItems.length && categoryItems} />
      <div className="flex-grow-1">
        <CloseoutComponent />
      </div>
    </div>
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
